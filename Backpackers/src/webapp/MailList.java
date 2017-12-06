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
@WebServlet(name = "maillist")
public class MailList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ResultSet rs = ExecQuery.execQuery("SELECT (SELECT concat(FirstName, ' ', LastName) FROM Person WHERE Id = C.PersonId) AS 'Customer', C.UserId FROM Customer C");
            JsonArray jarray = new JsonArray();
            while(rs.next()) {
                JsonObject resultSet = new JsonObject();
                String customerName = rs.getString(1);
                String email = rs.getString(2);

                resultSet.addProperty("customerName", customerName);
                resultSet.addProperty("email", email);
                jarray.add(resultSet);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(jarray));
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.getWriter().write(new Gson().toJson(resultSet));
        }
    }
}
