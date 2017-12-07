package webapp;

import Classes.Data;
import Classes.ExecQuery;
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

import static Classes.Data.findAirport;
import static Classes.Data.findLegs;
import static Classes.ExecQuery.*;

/**
 * Created by Rahul on 12/04/17.
 */
@WebServlet(name = "itinerary")
public class Itinerary extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resr = request.getParameter("resrNo");
        try {
            createConnection();
            Data.Refresh();
            if(viewExists("itinerary")) {
                dropView("itinerary");
            }
            createView("itinerary", "SELECT DISTINCT I.ResrNo AS 'Reservation', I.Id AS 'Booking', concat(I.AirlineId, ' ', I.FlightNo) AS 'Flight', I.TravelDate, R.BookingDate AS 'BookingDate', (R.BookingFee + R.Fare) AS 'Fare', (SELECT DISTINCT concat(P.FirstName, ' ', P.LastName) FROM Person P, Customer C WHERE C.PersonId = P.Id AND C.UserId = R.UserId) AS 'Customer', R.UserId, RP.Name AS 'Passenger', RP.SeatPref, RP.MealPref, (SELECT Name FROM Class WHERE Id = I.ClassId) AS 'Class', (SELECT DISTINCT A.Id FROM Airport A WHERE A.Id = L.Origin) AS 'Departs', (SELECT DISTINCT A.Id FROM Airport A WHERE A.Id = L.Destination) AS 'Arrives' FROM Booking I JOIN Reservation R ON R.ResrNo = I.ResrNo JOIN Passenger RP ON RP.ResrNo = I.ResrNo JOIN Flight F ON F.AirlineId = I.AirlineId AND F.FlightNo = I.FlightNo JOIN Leg L ON I.LegId = L.LegId AND L.AirlineId = I.AirlineId AND L.FlightNo = I.FlightNo ORDER BY R.ResrNo, I.Id;");
            ResultSet rs = ExecQuery.execQuery("SELECT * FROM itinerary WHERE Reservation='" + resr + "' ORDER BY Booking");
            JsonObject result = new JsonObject();
            String customer = "";
            String userId = "";
            String classTravel = "";
            String bookingDate = "";
            double fare = 0.0;

            JsonArray jsonLegs = new JsonArray();
            while(rs.next()) {
                String flight = rs.getString(3);
                String origin = rs.getString(13);
                String destination = rs.getString(14);
                ArrayList<Leg> legs = findLegs(origin, destination, flight);
                for(Leg leg: legs) {
                    JsonObject newJsonLeg = new JsonObject();
                    newJsonLeg.addProperty("flight", flight);
                    newJsonLeg.addProperty("origin", leg.getOrigin().getId().concat(": ").concat(leg.getOrigin().getCity().concat(", ").concat(leg.getOrigin().getCountry())));
                    newJsonLeg.addProperty("destination", leg.getDestination().getId().concat(": ").concat(leg.getDestination().getCity().concat(", ").concat(leg.getDestination().getCountry())));
                    newJsonLeg.addProperty("departure", leg.getDeparture());
                    newJsonLeg.addProperty("arrival", leg.getArrival());
                    newJsonLeg.addProperty("duration", leg.getDuration());

                    jsonLegs.add(newJsonLeg);
                    System.out.println(newJsonLeg.toString());
                }
                customer = rs.getString(7);
                userId = rs.getString(8);
                classTravel = rs.getString(12);
                bookingDate = rs.getString(5);
                fare = rs.getDouble(6);
            }
            rs = ExecQuery.execQuery("SELECT * FROM Itinerary WHERE Reservation='" + resr + "' GROUP BY Passenger ORDER BY Passenger;");
            JsonArray jsonPassengers = new JsonArray();
            while(rs.next()) {
                JsonObject newJsonPass = new JsonObject();
                String name = rs.getString(9);
                String seatPref = rs.getString(10);
                String mealPref = rs.getString(11);
                newJsonPass.addProperty("name", name);
                newJsonPass.addProperty("seatPref", seatPref);
                newJsonPass.addProperty("mealPref", mealPref);
                jsonPassengers.add(newJsonPass);
            }
            result.addProperty("customer", customer);
            result.addProperty("userId", userId);
            result.addProperty("classTravel", classTravel);
            result.addProperty("bookingDate", bookingDate);
            result.addProperty("fare", fare);
            result.addProperty("legs", jsonLegs.toString());
            result.addProperty("passengers", jsonPassengers.toString());
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
