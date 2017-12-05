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
 * Created by Rahul on 12/05/17.
 */
@WebServlet(name = "currRes")
public class bestSeller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ResultSet rs = ExecQuery.execQuery("SELECT * FROM best_seller");
            JsonArray jarray = new JsonArray();
            while(rs.next()) {
                JsonObject resultSet = new JsonObject();
                resultSet.addProperty("flight", rs.getString(1));
                resultSet.addProperty("count", rs.getInt(2));
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
