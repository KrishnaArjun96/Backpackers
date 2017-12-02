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
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String option = data.get("option").getAsString();
        if(option.equals("flight")) {
            try {
                ExecQuery.execQuery("IF EXISTS(DROP VIEW reservations_flight)");
                ExecQuery.execQuery("CREATE VIEW reservations_flight AS SELECT DISTINCT concat(F.AirlineID, ' ', F.FlightNo) AS 'Flight', R.ResrNo AS 'Reservation #', R.BookingDate AS 'Date', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Employee E WHERE P.id = E.PersonId AND E.SSN = R.RepSSN) AS 'Representative' FROM Reservation R, Flight F, Leg I WHERE F.AirlineID = I.AirlineID AND F.FlightNo = I.FlightNo AND I.ResrNo = R.ResrNo;");
                //SELECT * FROM reservations_flight WHERE Flight = 'AA 111';
                String flight = data.get("flight").getAsString();
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_flight WHERE Flight = '" + flight + "'");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resr = rs.getString(2);
                    String date = rs.getString(3);
                    String rep = rs.getString(4);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resr", resr);
                    resultSet.addProperty("date", date);
                    resultSet.addProperty("rep", rep);
                    jarray.add(resultSet);
                }
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }
        }
        else if(option.equals("customer")) {
            //@todo
            /*try {
                ExecQuery.execQuery("IF EXISTS(DROP VIEW reservations_customer)");
                ExecQuery.execQuery("CREATE VIEW reservations_customer AS SELECT DISTINCT C.AccountNo AS 'AccountNo', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Customer C WHERE P.Id = C.Id AND R.AccountNo = C.AccountNo) AS 'Customer', concat(F.AirlineID, ' ', F.FlightNo) AS 'Flight', R.ResrNo AS 'Reservation #', R.ResrDate AS 'Date', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Employee E WHERE P.id = E.id AND E.SSN = R.RepSSN) AS 'Representative', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, ReservationPassenger RP WHERE RP.Id = P.Id AND RP.AccountNo = C.AccountNo) AS 'Passenger' FROM Flight F, Includes I, Reservation R, ReservationPassenger RP, Person P, Passenger Pax, Customer C WHERE R.AccountNo = C.AccountNo AND I.AirlineId = F.AirlineId AND I.FlightNo = F.FlightNo AND I.ResrNo = R.ResrNo ORDER BY R.ResrNo;");
                //SELECT * FROM reservations_flight WHERE Flight = 'AA 111';
                String flight = data.get("flight").getAsString();
                ResultSet rs = ExecQuery.execQuery("SELECT * FROM reservations_customer WHERE Flight = '" + flight + "'");
                JsonArray jarray = new JsonArray();
                while(rs.next()) {
                    String resr = rs.getString(2);
                    String date = rs.getString(3);
                    String rep = rs.getString(4);
                    JsonObject resultSet = new JsonObject();
                    resultSet.addProperty("resr", resr);
                    resultSet.addProperty("date", date);
                    resultSet.addProperty("rep", rep);
                    jarray.add(resultSet);
                }
                response.getWriter().write(new Gson().toJson(jarray));
            } catch (Exception e) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("success", false);
                response.getWriter().write(new Gson().toJson(resultSet));
            }*/
        }

    }
}
