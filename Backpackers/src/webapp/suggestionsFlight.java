package webapp;

import Classes.Data;
import Classes.ExecQuery;
import JavaBeans.Airport;
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
import java.util.ArrayList;

import static Classes.Data.getDestinations;

/**
 * Created by Rahul on 12/04/17.
 */
@WebServlet(name = "employee")
public class suggestionsFlight extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        try {
            Data.Refresh();
            ResultSet rs = ExecQuery.execQuery("SELECT * from personalised WHERE UserId='" + userId + "'");
            JsonArray array = new JsonArray();
            while(rs.next()) {
                JsonObject object = new JsonObject();
                String flight = rs.getString(3);
                ArrayList<Airport> destnations = getDestinations(flight);
                object.addProperty("flight", flight);
                JsonArray jsonDestinations = new JsonArray();
                for(Airport destination: destnations) {
                    JsonObject jsonDest = new JsonObject();
                    jsonDest.addProperty("airport", destination.getId());
                    jsonDest.addProperty("city", destination.getCity());
                    jsonDest.addProperty("country", destination.getCountry());
                    jsonDestinations.add(jsonDest);
                }
                object.addProperty("destinations", jsonDestinations.toString());
                array.add(object);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(String.valueOf(array));

        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(resultSet));
        }
    }
}
