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

/**
 * Created by Rahul on 12/01/17.
 */

@WebServlet(name = "SalesReport")
public class SalesReport  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String month = data.get("month").getAsString();
        String year = data.get("year").getAsString();
        String date = month.concat(" ").concat(year);
        try {
            ExecQuery.execQuery("IF EXISTS(DROP VIEW sales_report)");
            ExecQuery.execQuery("CREATE VIEW sales_report AS SELECT ResrNo AS 'Reservation #', concat(MONTH(BookingDate), \' \', YEAR(BookingDate)) AS 'Date', (BookingFee + Fare) AS 'Sale', (SELECT concat(P.FirstName, ' ', P.LastName) FROM Person P, Employee E WHERE P.id = E.PersonId) AS 'Representative' FROM Reservation;");
            ResultSet rs_sales = ExecQuery.execQuery("SELECT * FROM sales_report WHERE Date=\"" + date + "\"");
            JsonArray resultSet = new JsonArray();
            while(rs_sales.next()) {
                String bookingDate = rs_sales.getString(1);
                String sale = rs_sales.getString(2);
                String rep = rs_sales.getString(3);

                JsonObject json = new JsonObject();
                json.addProperty("bookingDate", bookingDate);
                json.addProperty("sale", sale);
                json.addProperty("rep", rep);
                resultSet.add(json);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println(resultSet.toString());
            response.getWriter().write(new Gson().toJson(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update a record
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
