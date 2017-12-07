package webapp;

import Classes.Data;
import Classes.ExecQuery;
import JavaBeans.*;
import JavaBeans.Connection;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Classes.Data.*;
import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;

@WebServlet(name = "Search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createConnection();
            Data.Refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

        ArrayList<ArrayList<Option>> allOptions = new ArrayList<>();

        if(data.get("type").getAsString().equals("one")) {
            String user_origin = data.get("source").getAsString();
            String user_destination = data.get("destination").getAsString();
            String startDate = data.get("depatureDate").getAsString();
            String prefClass = data.get("prefClass").getAsString();
            int passengers = data.get("noOfPass").getAsInt();
            boolean flexible = data.get("flexible").getAsBoolean();

            ArrayList<Option> options = search(user_origin, user_destination, startDate, prefClass, passengers);
            allOptions.add(options);
        }
        else if(data.get("type").getAsString().equals("round")) {
            String user_origin = data.get("source").getAsString();
            String user_destination = data.get("destination").getAsString();
            String startDate = data.get("depatureDate").getAsString();
            String returnDate = data.get("returnDate").getAsString();
            String prefClass = data.get("prefClass").getAsString();
            int passengers = data.get("noOfPass").getAsInt();
            boolean flexible = data.get("flexible").getAsBoolean();

            ArrayList<Option> optionsOutBound = search(user_origin, user_destination, startDate, prefClass, passengers);
            ArrayList<Option> optionsInBound = search(user_destination, user_origin, returnDate, prefClass, passengers);

            allOptions.add(optionsOutBound);
            allOptions.add(optionsInBound);
        }
        else if(data.get("type").getAsString().equals("multi")) {
            String prefClass = data.get("prefClass").getAsString();
            int passengers = data.get("noOfPass").getAsInt();
            JsonArray trips = data.get("trips").getAsJsonArray();
            for(JsonElement trip: trips) {
                JsonObject tripData = new Gson().fromJson(trip, JsonObject.class);
                String origin = tripData.get("source").getAsString();
                String destination = tripData.get("destination").getAsString();
                String date = tripData.get("date").getAsString();

                ArrayList<Option> options = search(origin, destination, date, prefClass, passengers);
                allOptions.add(options);
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(new Gson().toJson(allOptions));
        response.getWriter().write(new Gson().toJson(allOptions));
        System.out.println("WRITTEN...");
        closeConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public static ArrayList<Option> search(String user_origin, String user_destination, String date, String prefClass, int passengers) {
        Airport origin = findAirport(user_origin);
        Airport destination = findAirport(user_destination);
        ArrayList<ArrayList<Airport>> allPaths = CONNECTIONS.getAllPaths(origin, destination);
        ArrayList<Path> paths = new ArrayList<>();
        for(ArrayList<Airport> path: allPaths) {
            Path newPath = new Path();
            for(int i=1; i<path.size(); i++) {
                Airport origin_apt = path.get(i-1);
                Airport destination_apt = path.get(i);
                Connection newConnection = new Connection();
                newConnection.setLegs(findLegs(origin_apt.getId(), destination_apt.getId()));
                newPath.getConnections().add(newConnection);
            }
            paths.add(newPath);
        }

        ArrayList<Option> options = new ArrayList<>();
        for(Path path: paths) {
            ArrayList<ArrayList<Leg>> result = generateAllLegs(path);
            for (ArrayList<Leg> set : result) {
                Option newOption = new Option();
                int totalTime = 0;
                for (Leg leg : set) {
                    newOption.getLegs().add(leg);
                    totalTime += getTimeDuration(leg.getDuration());
                }
                newOption.setTotalDuration(totalTime);
                try {
                    if (validateOption(newOption, date, passengers, prefClass)) {
                        for (int i = 0; i < newOption.getLayovers().length; i++)
                            newOption.setTotalDuration(newOption.getTotalDuration() + newOption.getLayovers()[i]);
                        newOption.setPassengers(passengers);
                        newOption.updateTotalFare(prefClass);
                        newOption.updateAirlines();
                        newOption.updateTime();
                        newOption.updateStops();
                    }
                    options.add(newOption);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return options;
    }

    public static String convertTimeFormat(int time) {
        String hours = String.valueOf(time/60);
        String minutes = String.valueOf(time%60);
        while(hours.length() < 2) {
            hours = "0".concat(hours);
        }
        while(minutes.length() < 2) {
            minutes = "0".concat(minutes);
        }
        return hours.concat(":").concat(minutes);
    }

    public static int getTimeDuration(String time) {
        String timeParse[] = time.split(":");
        int hour = Integer.parseInt(timeParse[0]);
        int minute = Integer.parseInt(timeParse[1]);
        return (hour*60) + minute;
    }

    public static ArrayList<ArrayList<Leg>> generateAllLegs(Path path) {
        ArrayList<ArrayList<Leg>> result = new ArrayList<>();
        int solutions = 1;
        for(int i=0; i<path.getConnections().size(); solutions *= path.getConnections().get(i).getLegs().size(), i++);
        for(int i=0; i<solutions; i++) {
            int j=1;
            ArrayList<Leg> toAdd = new ArrayList<>();
            for(Connection connection: path.getConnections()) {
                toAdd.add(connection.getLegs().get((i/j)%connection.getLegs().size()));
                j *= connection.getLegs().size();
            }
            result.add(toAdd);
        }
        return result;
    }

    public static boolean isLeap(String yearStr) {
        int year = Integer.parseInt(yearStr);
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public static String getDateString(int[] components) {
        String year = String.valueOf(components[0]);
        while(year.length() < 4){
            year = "0".concat(year);
        }
        String month = String.valueOf(components[1]);
        while(month.length() < 2) {
            month = "0".concat(month);
        }
        String date = String.valueOf(components[2]);
        while(date.length() < 2) {
            date = "0".concat(date);
        }

        return year.concat("-").concat(month).concat("-").concat(date);
    }

    public static String getNextDay(String date) {
        String[] components = date.split("-");
        int[] newDateComponents = new int[3];
        switch(Integer.parseInt(components[1])){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
                if(Integer.parseInt(components[2]) == 31) {
                    newDateComponents[0] = Integer.parseInt(components[0]);
                    newDateComponents[1] = Integer.parseInt(components[1]) + 1;
                    newDateComponents[2] = 1;
                }
                else {
                    newDateComponents[0] = Integer.parseInt(components[0]);
                    newDateComponents[1] = Integer.parseInt(components[1]);
                    newDateComponents[2] = Integer.parseInt(components[2]) + 1;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(Integer.parseInt(components[2]) == 30) {
                    newDateComponents[0] = Integer.parseInt(components[0]);
                    newDateComponents[1] = Integer.parseInt(components[1]) + 1;
                    newDateComponents[2] = 1;
                }
                else {
                    newDateComponents[0] = Integer.parseInt(components[0]);
                    newDateComponents[1] = Integer.parseInt(components[1]);
                    newDateComponents[2] = Integer.parseInt(components[2]) + 1;
                }
                break;
            case 2:
                if(isLeap(components[0])) {
                    if(Integer.parseInt(components[2]) == 29) {
                        newDateComponents[0] = Integer.parseInt(components[0]);
                        newDateComponents[1] = Integer.parseInt(components[1]) + 1;
                        newDateComponents[2] = 1;
                    }
                    else {
                        newDateComponents[0] = Integer.parseInt(components[0]);
                        newDateComponents[1] = Integer.parseInt(components[1]);
                        newDateComponents[2] = Integer.parseInt(components[2]) + 1;
                    }
                }
                else {
                    if(Integer.parseInt(components[2]) == 28) {
                        newDateComponents[0] = Integer.parseInt(components[0]);
                        newDateComponents[1] = Integer.parseInt(components[1]) + 1;
                        newDateComponents[2] = 1;
                    }
                    else {
                        newDateComponents[0] = Integer.parseInt(components[0]);
                        newDateComponents[1] = Integer.parseInt(components[1]);
                        newDateComponents[2] = Integer.parseInt(components[2]) + 1;
                    }
                }
                break;
            case 12:
                if(Integer.parseInt(components[2]) == 31) {
                    newDateComponents[0] = Integer.parseInt(components[0]) + 1;
                    newDateComponents[1] = 1;
                    newDateComponents[2] = 1;
                }
                else {
                    newDateComponents[0] = Integer.parseInt(components[0]);
                    newDateComponents[1] = Integer.parseInt(components[1]);
                    newDateComponents[2] = Integer.parseInt(components[2]) + 1;
                }
                break;
            default:
                break;
        }
        return getDateString(newDateComponents);
    }

    public static String getPrevDay(String date) {
        String[] components = date.split("-");
        int[] prevDateComponents = new int[3];
        switch(Integer.parseInt(components[1])){
            case 2:
            case 4:
            case 6:
            case 8:
            case 9:
            case 11:
                if(Integer.parseInt(components[2]) == 1) {
                    prevDateComponents[0] = Integer.parseInt(components[0]);
                    prevDateComponents[1] = Integer.parseInt(components[1]) - 1;
                    prevDateComponents[2] = 31;
                }
                else {
                    prevDateComponents[0] = Integer.parseInt(components[0]);
                    prevDateComponents[1] = Integer.parseInt(components[1]);
                    prevDateComponents[2] = Integer.parseInt(components[2]) - 1;
                }
                break;
            case 5:
            case 7:
            case 10:
            case 12:
                if(Integer.parseInt(components[2]) == 30) {
                    prevDateComponents[0] = Integer.parseInt(components[0]);
                    prevDateComponents[1] = Integer.parseInt(components[1]) - 1;
                    prevDateComponents[2] = 31;
                }
                else {
                    prevDateComponents[0] = Integer.parseInt(components[0]);
                    prevDateComponents[1] = Integer.parseInt(components[1]);
                    prevDateComponents[2] = Integer.parseInt(components[2]) - 1;
                }
                break;
            case 3:
                if(isLeap(components[0])) {
                    if(Integer.parseInt(components[2]) == 1) {
                        prevDateComponents[0] = Integer.parseInt(components[0]);
                        prevDateComponents[1] = Integer.parseInt(components[1]) - 1;
                        prevDateComponents[2] = 29;
                    }
                    else {
                        prevDateComponents[0] = Integer.parseInt(components[0]);
                        prevDateComponents[1] = Integer.parseInt(components[1]);
                        prevDateComponents[2] = Integer.parseInt(components[2]) - 1;
                    }
                }
                else {
                    if(Integer.parseInt(components[2]) == 28) {
                        prevDateComponents[0] = Integer.parseInt(components[0]);
                        prevDateComponents[1] = Integer.parseInt(components[1]) - 1;
                        prevDateComponents[2] = 28;
                    }
                    else {
                        prevDateComponents[0] = Integer.parseInt(components[0]);
                        prevDateComponents[1] = Integer.parseInt(components[1]);
                        prevDateComponents[2] = Integer.parseInt(components[2]) - 1;
                    }
                }
                break;
            case 1:
                if(Integer.parseInt(components[2]) == 1) {
                    prevDateComponents[0] = Integer.parseInt(components[0]) + 1;
                    prevDateComponents[1] = 12;
                    prevDateComponents[2] = 31;
                }
                else {
                    prevDateComponents[0] = Integer.parseInt(components[0]);
                    prevDateComponents[1] = Integer.parseInt(components[1]);
                    prevDateComponents[2] = Integer.parseInt(components[2]) - 1;
                }
                break;
            default:
                break;
        }
        return getDateString(prevDateComponents);
    }

    public static boolean validateOption(Option option, String date, int passengers, String prefClass) throws ParseException, SQLException, ClassNotFoundException {
        if(!isFlightOperational(option.getLegs().get(0).getFlight(), date)) return false;
        String travelDate = date;
        String[] dates = new String[option.getLegs().size()];
        int[] layovers = new int[option.getLegs().size()-1];
        dates[0] = travelDate;
        for(int i=1; i<option.getLegs().size(); i++) {
            if(!isFlightOperational(option.getLegs().get(i-1).getFlight(), date)) return false;
            Leg trip1 = option.getLegs().get(i-1);
            Leg trip2 = option.getLegs().get(i);
            if(timeCheck(trip1.getDeparture(), trip1.getArrival()) < 0)
                travelDate = getNextDay(travelDate);
            int layover = timeCheck(trip1.getArrival(), trip2.getDeparture());
            if(layover < 60) {
                layover = Math.abs(timeCheck("24:00:00", trip1.getArrival()));
                int days = 0;
                for(;;) {
                    if(isFlightOperational(trip2.getFlight(), travelDate) && (!date.equals(travelDate)) && checkIfSeatsPresent(trip1, passengers, prefClass, travelDate)) {
                        layover += Math.abs(timeCheck(trip2.getDeparture(), "00:00:00"));
                        layovers[i-1] = layover;
                        dates[i] = travelDate;
                        break;
                    }
                    else {
                        travelDate = getNextDay(travelDate);
                        days++;
                    }
                }
                if(days > 2) layovers[i-1] = layovers[i-1]*((days-2)*24*60);
            }
            else {
                layovers[i-1] = Math.abs(layover);
                dates[i] = travelDate;
            }
        }
        option.setLayovers(layovers);
        option.setDates(dates);
        return true;
    }

    public static boolean isFlightOperational(Flight flight, String date) throws ParseException {
        ArrayList<String> days = new ArrayList<>();
        Collections.addAll(days, new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"});
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt1 = format1.parse(date);
        DateFormat format2 = new SimpleDateFormat("E");
        String finalDay = format2.format(dt1);
        int index = days.indexOf(finalDay);
        if(flight.getDays()[index] == '1') return true;
        return false;
    }

    public static int timeCheck(String time1, String time2) throws ParseException {
        String startTimeParse[] = time1.split(":");
        String endTimeParse[] = time2.split(":");
        int firstHour = Integer.parseInt(startTimeParse[0]);
        int firstMinute = Integer.parseInt(startTimeParse[1]);
        int secondHour = Integer.parseInt(endTimeParse[0]);
        int secondMinute = Integer.parseInt(endTimeParse[1]);
        int durationHour = secondHour - firstHour;
        int durationMinutes = secondMinute - firstMinute;
        return durationHour*60 + durationMinutes;
    }

    public static boolean checkIfSeatsPresent(Leg leg, int passengers, String prefClass, String travelDate) throws SQLException, ClassNotFoundException {
        String[] classArray = {"eco", "bus", "fc"};
        ArrayList<String> classArrayList = new ArrayList<String>(Arrays.asList(classArray));
        int classIndex = classArrayList.indexOf(prefClass) + 1;
        ResultSet rs = ExecQuery.execQuery("SELECT COUNT(*) FROM Class WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "'");
        int maxRank = 0;
        while (rs.next()) {
            maxRank = rs.getInt(1);
        }
        if (maxRank < classIndex) classIndex = maxRank;
        ResultSet rs_class = ExecQuery.execQuery("SELECT Seats FROM Class WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "' AND ClassRank=" + classIndex + " AND IsVisible=1");
        int totalSeats = 0;
        while (rs_class.next()) {
            totalSeats = rs_class.getInt(1);
        }
        ResultSet rs_booked = ExecQuery.execQuery("SELECT COUNT(*) FROM Booking WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "' AND LegId=" + getLegId(leg) + " AND TravelDate='" + travelDate + "'");
        int totalBooked = 0;
        while (rs_booked.next()) {
            totalBooked = rs_booked.getInt(1);
        }
        if (passengers > (totalSeats - totalBooked)) return false;
        return true;
    }
}
