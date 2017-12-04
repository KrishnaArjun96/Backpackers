package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String firstname = data.get("firstname").getAsString();
        String lastname = data.get("lastname").getAsString();
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();


        try{
            String exec = "INSERT INTO Accounts (Email, FirstName, LastName, Password) VALUES (?,?,?,?)";
            PreparedStatement pstmt = ExecQuery.insertIntoTable(exec);
            pstmt.setString(1, email);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("isValid",true);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(new Gson().toJson(map));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

}
