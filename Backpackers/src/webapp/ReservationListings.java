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

import static Classes.ExecQuery.createView;
import static Classes.ExecQuery.dropView;
import static Classes.ExecQuery.viewExists;

/**
 * Created by Rahul on 12/01/17.
 */
@WebServlet(name = "reservationListing")
public class ReservationListings extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String option = request.getParameter("type");
        if(option.equals("flightNo")) {
            try {
                String flight = request.getParameter("flightNo");
                String date = request.getParameter("date");
                System.out.println(flight + " " + date);
                String[] dates = date.split("-");
                if(viewExists("reservations_flight")) {
                    dropView("reservations_flight");
                    createView("reservations_flight", "SELECT concat(F.AirlineID, ' ', F.FlightNo) AS 'Flight', R.ResrNo AS 'Reservation', B.Id AS 'Booking', R.BookingDate AS 'BookingDate', B.TravelDate AS 'TravelDate', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Employee E WHERE P.id = E.PersonId AND E.SSN = R.EmployeeSSN) AS 'Representative' FROM Reservation R, Flight F, Booking B WHERE F.AirlineID = B.AirlineID AND F.FlightNo = B.FlightNo AND B.ResrNo = R.ResrNo ORDER BY Reservation, Booking;");
                }
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_flight WHERE Flight = '" + flight + "' AND TravelDate='" + date + "'");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resr = rs.getString(2);
                    String bookingId = rs.getString(3);
                    String bookingDate = rs.getString(4);
                    String travelDate = rs.getString(5);
                    String rep = rs.getString(6);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resr", resr);
                    resultSet.addProperty("bookingId", bookingId);
                    resultSet.addProperty("bookingDate", bookingDate);
                    resultSet.addProperty("travelDate", travelDate);
                    resultSet.addProperty("rep", rep);
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
        }
        else if(option.equals("customerName")) {
            try {
                if(viewExists("reservations_customer")) {
                    dropView("reservations_customer");
                    createView("reservations_customer", "SELECT DISTINCT (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Customer C WHERE P.Id = C.PersonId AND R.UserId = C.UserId AND C.PersonId = R.CustomerId) AS 'Customer', concat(F.AirlineID, ' ', F.FlightNo) AS 'Flight', R.ResrNo AS 'Reservation', I.Id AS 'Leg', R.BookingDate AS 'Booking Date', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Employee E WHERE P.Id = E.PersonId AND E.SSN = R.EmployeeSSN) AS 'Representative' FROM Flight F, Booking I, Reservation R, Person P, Customer C WHERE R.CustomerId = C.PersonId AND R.UserId = C.UserId AND I.AirlineId = F.AirlineId AND I.FlightNo = F.FlightNo AND I.ResrNo = R.ResrNo ORDER BY I.ResrNo, I.LegId;");
                }
                String customer = request.getParameter("name");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_customer WHERE Customer='" + customer + "' ORDER BY Reservation, Leg");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String customerName = rs.getString(1);
                    String flight = rs.getString(2);
                    String resrNo = rs.getString(3);
                    String bookingDate = rs.getString(5);
                    String rep = rs.getString(6);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("customerName", customerName);
                    resultSet.addProperty("flight", flight);
                    resultSet.addProperty("resrNo", resrNo);
                    resultSet.addProperty("bookingDate", bookingDate);
                    resultSet.addProperty("rep", rep);
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
        }

        else if(option.equals("customerOnFlight")) {
            try {
                if(viewExists("customer_reservations")) {
                    dropView("customer_reservations");
                    createView("customer_reservations", "SELECT DISTINCT (SELECT Name FROM Airline WHERE Id = I.AirlineId) AS 'Airline', concat(I.AirlineId, ' ', I.FlightNo) AS 'Flight', I.ResrNo AS 'Reservation', I.Id AS 'BookingId', I.TravelDate, R.BookingDate AS 'BookingDate', (SELECT DISTINCT concat(P.FirstName, ' ', P.LastName) FROM Person P, Customer C WHERE C.PersonId = P.Id AND C.UserId = R.UserId) AS 'Customer', RP.Name AS 'Passenger', (SELECT C.Name FROM Class C WHERE C.Id = I.ClassId) AS 'Class', RP.MealPref  FROM Booking I JOIN Reservation R ON R.ResrNo = I.ResrNo JOIN Passenger RP ON RP.ResrNo = I.ResrNo JOIN Flight F ON F.AirlineId = I.AirlineId AND F.FlightNo = I.FlightNo JOIN Leg L ON I.LegId = L.Legid AND L.AirlineId = I.AirlineId AND L.FlightNo = I.FlightNo ORDER BY I.ResrNo, I.Id, RP.Name;");
                }
                String flight = request.getParameter("flightNo");
                String date = request.getParameter("date");
                String exec = "SELECT DISTINCT Reservation, Passenger, TravelDate, BookingDate FROM customer_reservations WHERE Flight='" + flight + "' AND TravelDate='"+ date + "' ORDER BY BookingId, Passenger;";
                System.out.println(exec);
                ResultSet rs = ExecQuery.execQuery(exec);
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resrNo = rs.getString(1);
                    String travelDate = rs.getString(3);
                    String passenger = rs.getString(4);
                    String travelClass = rs.getString(2);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resrNo", resrNo);
                    resultSet.addProperty("travelDate", travelDate);
                    resultSet.addProperty("travelClass", travelClass);
                    resultSet.addProperty("passenger", passenger);
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
        }

    }
}
