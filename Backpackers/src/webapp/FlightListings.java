package webapp;

import Classes.Data;
import JavaBeans.Leg;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.jdbc.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static Classes.Data.LEGS;

/**
 * Created by Rahul on 12/01/17.
 */

@WebServlet(name = "flightListing")
public class FlightListings extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Data.Refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray flights = new JsonArray();
        for (Leg leg : LEGS) {
            JsonObject jsonFlight = new JsonObject();
            jsonFlight.addProperty("flightNo", leg.getFlight().getAirline().getId().concat(" ").concat(leg.getFlight().getFlightNo()));
            jsonFlight.addProperty("stops", leg.getFlight().getStopsNo());
            jsonFlight.addProperty("origin", leg.getOrigin().getId());
            jsonFlight.addProperty("destination", leg.getDestination().getId());
            jsonFlight.addProperty("departure", leg.getDeparture());
            jsonFlight.addProperty("arrival", leg.getArrival());
            jsonFlight.addProperty("duration", leg.getDuration());
            jsonFlight.addProperty("days", String.valueOf(leg.getFlight().getDays()));
            jsonFlight.addProperty("noOfDays", countOccurrencesOf(String.valueOf(leg.getFlight().getDays()), '1'));

            flights.add(jsonFlight);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(new Gson().toJson(flights));
    }

    public static int countOccurrencesOf(String str, char key) {
        int length = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == key) length++;
        }
        return length;
    }
}
