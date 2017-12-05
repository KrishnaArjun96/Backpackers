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

@WebServlet(name = "Revenue")
public class Revenue extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This is a get request, so we will just get values from query params(from the URL)
        String type = request.getParameter("type");
        String value = request.getParameter("value");

        if(type.equals("flightNo")) {
            try {
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
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }
        }
        else if(type.equals("city")) {
            try {
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
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }
        }
        else if(type.equals("customer")) {
            try {
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
                /*ResultSet rs_max = ExecQuery.execQuery("SELECT Customer, MAX(Revenue) FROM revenue_customer");
                double max_revenue = 0;
                String customer_max = "";
                while(rs_max.next()) {
                    customer_max = rs_max.getString(1);
                    max_revenue = rs_max.getDouble(2);
                }
                JsonObject max_customer = new JsonObject();
                max_customer.addProperty("max_customer_name", customer_max);
                max_customer.addProperty("max_customer_revenue", max_revenue);
                JsonObject result = new JsonObject();

                result.addProperty("results", jarray.toString());
                result.addProperty("max", max_customer.toString());
                response.getWriter().write(result.toString());*/
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }
        }
        else if(type.equals("customerRep")) {
            try {
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM Rep_Revenue");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String rep = rs.getString(1);
                    double revenue = rs.getDouble(2);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("rep", rep);
                    resultSet.addProperty("revenue", revenue);
                    jarray.add(resultSet);
                }
                /*ResultSet rs_max = ExecQuery.execQuery("SELECT Customer, MAX(Revenue) FROM Rep_Revenue");
                double max_revenue = 0;
                String rep_max = "";
                while(rs_max.next()) {
                    rep_max = rs_max.getString(1);
                    max_revenue = rs_max.getDouble(2);
                }
                JsonObject max_customer = new JsonObject();
                max_customer.addProperty("max_rep_name", rep_max);
                max_customer.addProperty("max_rep_revenue", max_revenue);
                JsonObject result = new JsonObject();

                result.addProperty("results", jarray.toString());
                result.addProperty("max", max_customer.toString());
                response.getWriter().write(result.toString());*/
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }
        }
    }
}
