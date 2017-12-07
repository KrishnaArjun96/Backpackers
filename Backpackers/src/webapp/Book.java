package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;
import static webapp.Search.convertTimeFormat;

/**
 * Created by Rahul on 12/06/17.
 */
@WebServlet(name = "book")
public class Book extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject requestData = new Gson().fromJson(request.getReader(), JsonObject.class);

        JsonArray data = requestData.get("options").getAsJsonArray();
        JsonArray passengers = requestData.get("passengers").getAsJsonArray();
        boolean isAuction = requestData.get("isAuction").getAsBoolean();
        String userName = requestData.get("userName").getAsString();
        try {
            createConnection();
            ResultSet rs_user = ExecQuery.execQuery("SELECT Id FROM Person WHERE UserName='" + userName + "'");
            int personId = 0;
            while(rs_user.next()) {
                personId = rs_user.getInt(1);
            }
            rs_user = ExecQuery.execQuery("SELECT UserId FROM Customer WHERE PersonId=" + personId);
            String userId = "";
            while(rs_user.next()) {
                userId = rs_user.getString(1);
            }
            double totalFare = 0;
            for(JsonElement object: data) {
                JsonObject obj = object.getAsJsonObject();
                double fare = obj.get("totalFare").getAsDouble();
                totalFare += fare;
            }
            int employeeId = 0;
            String employeeSSN = "";
            ResultSet rs_employee = ExecQuery.execQuery("SELECT * FROM Employee WHERE Role='employee' ORDER BY RAND() LIMIT 1");
            if(rs_employee.next()) {
                employeeId = rs_employee.getInt(1);
                employeeSSN = rs_employee.getString(2);
            }
            int resrNo = (int)((Math.random()*9000) + 1000);
            double fee = totalFare/10;

            String exec = "INSERT INTO Reservation (ResrNo, BookingDate, Fare, BookingFee, EmployeeId, EmployeeSSN, CustomerId, UserId, Status) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setInt(1, resrNo);
            pstmt.setDate(2, java.sql.Date.valueOf(getToday()));
            pstmt.setDouble(3, totalFare);
            pstmt.setDouble(4, fee);
            pstmt.setInt(5, employeeId);
            pstmt.setString(6, employeeSSN);
            pstmt.setInt(7, personId);
            pstmt.setString(8, userId);
            pstmt.setString(9, (isAuction)?"auctioned":"booked");
            pstmt.executeUpdate();

            int bookingIndex = 1;
            for(int i=0; i<data.size(); i++) {
                JsonObject optionObj = data.get(i).getAsJsonObject();
                JsonArray legsData = optionObj.get("legs").getAsJsonArray();
                JsonArray dates = optionObj.get("dates").getAsJsonArray();
                JsonArray classes = optionObj.get("classes").getAsJsonArray();
                JsonArray layovers = optionObj.get("layovers").getAsJsonArray();
                for(int j=0; j<legsData.size(); j++) {
                    JsonObject legObj = legsData.get(j).getAsJsonObject();
                    int legId = legObj.get("legId").getAsInt();
                    JsonObject flight = legObj.get("flight").getAsJsonObject();
                    JsonObject airline = flight.get("airline").getAsJsonObject();
                    String airlineId = airline.get("id").getAsString();
                    int flightNo = flight.get("flightNo").getAsInt();
                    String travelDate = dates.get(j).getAsString();
                    int classId = classes.get(j).getAsInt();
                    String layover = "00:00";
                    if(j != legsData.size() - 1)
                        layover = convertTimeFormat(layovers.get(j).getAsInt());
                    exec = "INSERT INTO Booking (Id, ResrNo, LegId, FlightNo, AirlineId, TravelDate, ClassId) VALUES (?,?,?,?,?,?,?)";
                    pstmt = ExecQuery.updateTable(exec);
                    pstmt.setInt(1, bookingIndex);
                    pstmt.setInt(2, resrNo);
                    pstmt.setInt(3, legId);
                    pstmt.setInt(4, flightNo);
                    pstmt.setString(5, airlineId);
                    pstmt.setDate(6, java.sql.Date.valueOf(travelDate));
                    pstmt.setInt(7, classId);
                    pstmt.executeUpdate();
                    bookingIndex++;
                }

                if(isAuction) {

                }
            }

            for(int i=0; i<passengers.size(); i++) {
                JsonObject passenger = passengers.get(i).getAsJsonObject();
                String name = passenger.get("name").getAsString();
                String seat = passenger.get("seatPref").getAsString();
                String meal = passenger.get("mealPref").getAsString();
                exec = "INSERT INTO Passenger (PaxId, ResrNo, Name, SeatPref, MealPref) VALUES (?,?,?,?,?)";
                pstmt = ExecQuery.updateTable(exec);
                pstmt.setInt(1, i);
                pstmt.setInt(2, resrNo);
                pstmt.setString(3, name);
                pstmt.setString(4, seat);
                pstmt.setString(5, meal);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public static String getToday() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
