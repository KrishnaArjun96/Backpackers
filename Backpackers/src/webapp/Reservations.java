package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.ExecQuery.*;

/**
 * Created by Rahul on 12/04/17.
 */
@WebServlet(name = "reservations")
public class Reservations extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customer = request.getParameter("userId");
        String type = request.getParameter("type");
        if(type.equals("history")) {
            try {
                createConnection();
                if (viewExists("history")) {
                    dropView("history");
                }
                createView("history", "SELECT DISTINCT (SELECT concat(FirstName,' ',LastName) FROM Person where c.PersonId=Id) AS 'Customer', C.UserId, R.ResrNo, I.Id AS 'Booking', concat(I.AirlineId, ' ', I.FlightNo) AS 'Flight', I.TravelDate, R.BookingDate AS 'Date of Booking', SUM(R.Fare + R.BookingFee) AS 'Fare', R.Status AS 'Status' FROM Booking I, Reservation R, Customer C WHERE I.TravelDate < CURDATE() AND I.ResrNo=R.ResrNo AND C.UserId = R.UserId GROUP BY R.ResrNo;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM history WHERE UserId='" + customer + "' GROUP BY ResrNo");
                JsonArray jarray = new JsonArray();
                while (rs.next()) {
                    JsonObject resultSet = new JsonObject();
                    String resr = rs.getString(3);
                    String booking = rs.getString(4);
                    String flight = rs.getString(5);
                    String travelDate = rs.getString(6);
                    String bookingDate = rs.getString(7);
                    String status = rs.getString(9);
                    resultSet.addProperty("resr", resr);
                    resultSet.addProperty("booking", booking);
                    resultSet.addProperty("flight", flight);
                    resultSet.addProperty("travelDate", travelDate);
                    resultSet.addProperty("bookingDate", bookingDate);
                    resultSet.addProperty("status", status);
                    jarray.add(resultSet);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(resultSet));
            }
            closeConnection();
        }
        else if(type.equals("current")) {
            try {
                createConnection();
                if (viewExists("current_reservations_customer")) {
                    dropView("current_reservations_customer");
                }
                createView("current_reservations_customer", "SELECT DISTINCT (SELECT concat(FirstName,' ',LastName) FROM Person where c.PersonId=Id) AS 'Customer', C.UserId, R.ResrNo, I.Id AS 'Booking', concat(I.AirlineId, ' ', I.FlightNo) AS 'Flight', I.TravelDate, R.BookingDate AS 'Date of Booking', SUM(R.Fare + R.BookingFee) AS 'Fare', R.Status AS 'Status' FROM Booking I, Reservation R, Customer C WHERE I.TravelDate > CURDATE() AND I.ResrNo=R.ResrNo AND C.UserId = R.UserId GROUP BY R.ResrNo;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM current_reservations_customer WHERE UserId='" + customer + "' GROUP BY ResrNo");
                JsonArray jarray = new JsonArray();
                while (rs.next()) {
                    JsonObject resultSet = new JsonObject();
                    String resr = rs.getString(3);
                    String booking = rs.getString(4);
                    String flight = rs.getString(5);
                    String travelDate = rs.getString(6);
                    String bookingDate = rs.getString(7);
                    String status = rs.getString(9);
                    resultSet.addProperty("resr", resr);
                    resultSet.addProperty("booking", booking);
                    resultSet.addProperty("flight", flight);
                    resultSet.addProperty("travelDate", travelDate);
                    resultSet.addProperty("bookingDate", bookingDate);
                    resultSet.addProperty("status", status);
                    jarray.add(resultSet);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(resultSet));
            }
            closeConnection();
        }
    }
}
