package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.ExecQuery.*;

@WebServlet(name = "Revenue")
public class Revenue extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This is a get request, so we will just get values from query params(from the URL)
        String type = request.getParameter("type");

        if(type.equals("flightNo")) {
            try {
                createConnection();
                if(viewExists("revenue_flight")) {
                    dropView("revenue_flight");
                }
                createView("revenue_flight", "SELECT DISTINCT concat(I.AirlineId, ' ', I.FlightNo) AS 'Flight', ((SELECT COUNT(*) FROM Booking I WHERE I.AirlineId = F.AirlineId AND I.FlightNo = F.FlightNo GROUP BY I.AirlineId AND I.FlightNo)*(R.BookingFee + R.Fare)) AS 'Flight Revenue' FROM Flight F, Booking I, Reservation R WHERE F.FlightNo = I.FlightNo AND F.AirlineId = I.AirlineId AND I.ResrNo = R.ResrNo;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM revenue_flight");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String flight = rs.getString(1);
                    double revenue = rs.getDouble(2);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("flight", flight);
                    resultSet.addProperty("revenue", revenue);
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
        else if(type.equals("city")) {
            try {
                createConnection();
                if(viewExists("revenue_city")) {
                    dropView("revenue_city");
                }
                createView("revenue_city", "SELECT A.City, SUM(R.BookingFee + R.Fare) AS 'Total revenue' FROM Airport A, Reservation R, Booking I , Leg L WHERE R.ResrNo = I.ResrNo AND I.AirlineId = L.AirlineId AND I.FlightNo = L.FlightNo and A.Id = L.Destination GROUP BY A.City;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM revenue_city");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String city = rs.getString(1);
                    double revenue = rs.getDouble(2);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("city", city);
                    resultSet.addProperty("revenue", revenue);
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
        else if(type.equals("customer")) {
            try {
                createConnection();
                if(viewExists("revenue_customer")) {
                    dropView("revenue_customer");
                }
                createView("revenue_customer", "SELECT (SELECT concat(FirstName, ' ', LastName) FROM Person where Id = C.PersonId) AS 'Customer', C.UserId, SUM(R.BookingFee+R.Fare) AS 'Revenue' from Customer C, Reservation R WHERE C.PersonId = R.CustomerId GROUP BY R.CustomerId;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM revenue_customer");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String customerName = rs.getString(1);
                    String customerUserId = rs.getString(2);
                    double revenue = rs.getDouble(3);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("customerName", customerName);
                    resultSet.addProperty("customerUserId", customerUserId);
                    resultSet.addProperty("revenue", revenue);
                    jarray.add(resultSet);
                }
                ResultSet rs_max = ExecQuery.execQuery("SELECT Customer, MAX(Revenue) FROM revenue_customer");
                double max_revenue = 0;
                String customer_max = "";
                while(rs_max.next()) {
                    customer_max = rs_max.getString(1);
                    max_revenue = rs_max.getDouble(2);
                }
                JsonObject result = new JsonObject();
                result.addProperty("name", customer_max);
                result.addProperty("value", max_revenue);
                result.addProperty("table", jarray.toString());
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(result));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(resultSet));
            }
            closeConnection();
        }
        else if(type.equals("customerRep")) {
            try {
                createConnection();
                if(viewExists("Rep_Revenue")) {
                    dropView("Rep_Revenue");
                }
                createView("Rep_Revenue", "SELECT (SELECT concat(FirstName, ' ', LastName) FROM Person where Id = E.PersonId) AS 'Representative', E.SSN, SUM(R.BookingFee+R.Fare) AS 'Revenue' from Employee E, Reservation R WHERE E.PersonId = R.EmployeeId AND E.Role='employee' GROUP BY R.EmployeeId;");
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM Rep_Revenue");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String rep = rs.getString(1);
                    double revenue = rs.getDouble(3);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("rep", rep);
                    resultSet.addProperty("revenue", revenue);
                    jarray.add(resultSet);
                }
                ResultSet rs_max = ExecQuery.execQuery("SELECT Representative, SSN, MAX(Revenue) FROM Rep_Revenue");
                double max_revenue = 0;
                String rep_max = "";
                while(rs_max.next()) {
                    rep_max = rs_max.getString(1);
                    max_revenue = rs_max.getDouble(3);
                }

                JsonObject result = new JsonObject();
                result.addProperty("name", rep_max);
                result.addProperty("value", max_revenue);
                result.addProperty("table", jarray.toString());
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new Gson().toJson(result));

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
