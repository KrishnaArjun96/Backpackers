package webapp;

import Classes.ExecQuery;
import JavaBeans.*;
import JavaBeans.Connection;

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
import java.util.Date;

import static Classes.Data.*;

@WebServlet(name = "Search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_origin = request.getParameter("source");
        String user_destination = request.getParameter("destination");
        String startDate = request.getParameter("departing");
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
            for(ArrayList<Leg> set: result) {
                Option newOption = new Option();
                for(Leg leg: set) newOption.getLegs().add(leg);
                try {
                    if(validateOption(newOption, startDate))
                        options.add(newOption);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if(options.size() > 0) {
            int index = 1;
            for (Option option : options) {
                System.out.println("Option: " + index);
                for (Leg leg : option.getLegs()) {
                    System.out.println("\t" + leg.getOrigin().getId() + ": " + leg.getDeparture() + "\t" + leg.getDestination().getId() + ": " + leg.getArrival());
                }
            }
        }
        else System.out.println("NO FLIGHTS FOUND ON THE SELECTED DATE!");

        request.setAttribute("paths", allPaths);
        request.setAttribute("pathCount", allPaths.size());
        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public static boolean validateOption(Option option, String date) throws ParseException {
        if(!isFlightOperational(option.getLegs().get(0).getFlight(), date)) return false;
        String current_date = date;
        for(int i=1; i<option.getLegs().size(); i++) {
            if(!isFlightOperational(option.getLegs().get(i-1).getFlight(), date)) return false;
            Leg trip1 = option.getLegs().get(i-1);
            Leg trip2 = option.getLegs().get(i);
            if(!timeCheck(trip1.getArrival(), trip2.getDeparture())) return false;
        }
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

    public static boolean timeCheck(String time1, String time2) throws ParseException {
        String startTimeParse[] = time1.split(":");
        String endTimeParse[] = time2.split(":");
        int firstHour = Integer.parseInt(startTimeParse[0]);
        int firstMinute = Integer.parseInt(startTimeParse[1]);
        int secondHour = Integer.parseInt(endTimeParse[0]);
        int secondMinute = Integer.parseInt(endTimeParse[1]);
        int durationHour = secondHour - firstHour;
        int durationMinutes = secondMinute - firstMinute;
        if(durationHour > 1) return true;
        else if(durationHour==1 && durationMinutes >=45) return true;
        return false;
    }
}
