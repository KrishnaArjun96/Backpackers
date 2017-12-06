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

/**
 * Created by Rahul on 12/04/17.
 */
@WebServlet(name = "currResrs")
public class currResrs extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customer = request.getParameter("userId");
        try {
            ResultSet rs = ExecQuery.execQuery("SELECT * FROM current_reservations_customer WHERE UserId='" + customer + "' GROUP BY Reservation");
            JsonArray jarray = new JsonArray();
            while(rs.next()) {
                JsonObject resultSet = new JsonObject();
                String resr = rs.getString(3);
                String booking = rs.getString(4);
                String flight = rs.getString(5);
                String travelDate = rs.getString(6);
                String bookingDate = rs.getString(7);
                resultSet.addProperty("resr", resr);
                resultSet.addProperty("booking", booking);
                resultSet.addProperty("flight", flight);
                resultSet.addProperty("travelDate", travelDate);
                resultSet.addProperty("bookingDate", bookingDate);
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
