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
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String date = month.concat(" ").concat(year);
        try {
            ResultSet rs_sales = ExecQuery.execQuery("SELECT * FROM sales_report WHERE Month=" + month + " AND Year=" + year);
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
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.getWriter().write(new Gson().toJson(resultSet));
        }
    }
}
