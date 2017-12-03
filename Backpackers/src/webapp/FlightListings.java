package webapp;

import Classes.Data;
import Classes.ExecQuery;
import JavaBeans.Flight;
import JavaBeans.Leg;
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

import static Classes.Data.findAirline;
import static Classes.Data.getDays;
import static Classes.Data.getLegs;

/**
 * Created by Rahul on 12/01/17.
 */

@WebServlet(name = "flightListing")
public class FlightListings extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonArray flights = new JsonArray();
        try {
            ResultSet rs_flights = ExecQuery.execQuery("SELECT * FROM Flight");
            while(rs_flights.next()) {
                Flight flight = new Flight(findAirline(rs_flights.getString(2)), rs_flights.getString(1), rs_flights.getInt(3), getDays(rs_flights.getString(4)));
                ArrayList<Leg> legs = getLegs(flight);
                JsonObject jsonFlight = new JsonObject();
                jsonFlight.addProperty("airline", flight.getAirline().getId());
                jsonFlight.addProperty("flightNo", flight.getFlightNo());
                jsonFlight.addProperty("stops", flight.getStopsNo());
                jsonFlight.addProperty("days", flight.getDays().toString());
                JsonArray jsonLegs = new JsonArray();
                for(Leg leg: legs) {
                    JsonObject jsonLeg = new JsonObject();
                    jsonLeg.addProperty("origin", leg.getOrigin().getId());
                    jsonLeg.addProperty("destination", leg.getDestination().getId());
                    jsonLeg.addProperty("departure", leg.getDeparture());
                    jsonLeg.addProperty("arrival", leg.getArrival());
                    jsonLeg.addProperty("duration", leg.getDuration());
                    jsonLegs.add(jsonLeg);
                }
                jsonFlight.addProperty("legs", String.valueOf(jsonLegs));
                flights.add(jsonFlight);
            }
            System.out.println(flights);
            response.getWriter().write(new Gson().toJson(flights));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
