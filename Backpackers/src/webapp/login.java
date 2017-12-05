package webapp;

import Classes.ExecQuery;
import JavaBeans.UserAccount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();
        UserAccount user;
        boolean containsError=false;
        String errorValue = null;
        Map<String,Object> map = new HashMap<>();
        String username = null;

        //Error Checking
        if(email==null||email.length()==0||password==null||password.length()==0){
            containsError=true;
            errorValue = "One or more fields are missing.";
        }else{
            try{
                String exec = "SELECT FirstName FROM Accounts WHERE email=? and password=?";
                PreparedStatement pstmt = ExecQuery.insertIntoTable(exec);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    username = rs.getString(1);
                }else{
                    containsError = true;
                    errorValue = "Email or Password incorrect";
                }

            }catch (SQLException e) {
                containsError = true;
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                containsError = true;
                e.printStackTrace();
            }
        }
        if(containsError==true){
            map.put("FirstName",username);
            map.put("isValid",false);
            map.put("errorValue",errorValue);
        }else{
            HttpSession session = request.getSession();
            map.put("isValid",true);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(new Gson().toJson(map));



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

}
