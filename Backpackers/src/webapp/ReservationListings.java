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
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_flight WHERE Flight = '" + flight + "' AND TravelDate='" + date + "'");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resr = rs.getString(2);
                    String bookingId = rs.getString(3);
                    String bookingDate = rs.getString(4);
                    String travelDate = rs.getString(5);
                    String rep = rs.getString(6);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resr", bookingId);
                    resultSet.addProperty("bookingId", resr);
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
                String customer = request.getParameter("name");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_customer WHERE Customer='" + customer + "'");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String customerName = rs.getString(1);
                    String flight = rs.getString(2);
                    String resrNo = rs.getString(3);
                    String bookingDate = rs.getString(4);
                    String rep = rs.getString(5);
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
            //SELECT * FROM customer_reservations;
            try {
                String flight = request.getParameter("flightNo");
                String date = request.getParameter("date");
                String exec = "SELECT * FROM customer_reservations WHERE Flight='" + flight + "' AND TravelDate='"+ date + "' ORDER BY BookingId, Passenger;";
                System.out.println(exec);
                ResultSet rs = ExecQuery.execQuery(exec);
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resrNo = rs.getString(3);
                    String legId = rs.getString(4);
                    String travelDate = rs.getString(5);
                    String passenger = rs.getString(8);
                    String travelClass = rs.getString(9);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resrNo", resrNo);
                    resultSet.addProperty("booking", legId);
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
