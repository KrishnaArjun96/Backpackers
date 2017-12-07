package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;

@WebServlet(name = "Login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String username = data.get("username").getAsString();
        String password = data.get("password").getAsString();
        boolean containsError=false;
        String errorValue = null;
        Map<String,Object> map = new HashMap<>();
        String firstName = null;
        //Error Checking
        if(username==null||username.length()==0||username==null||username.length()==0){
            containsError=true;
            errorValue = "One or more fields are missing.";
        }else{
            try{
                createConnection();
                String exec = "SELECT Id FROM Person WHERE Username=? and Password=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int personId = rs.getInt(1);
                    String isEmployee = "SELECT FirstName FROM Employee WHERE Id=?";
                    PreparedStatement pstmt2 = ExecQuery.updateTable(exec);
                    pstmt2.setInt(1, personId);
                    ResultSet rs2 = pstmt.executeQuery();
                    if(rs2.next())
                        map.put("isManager",true);
                    else
                        map.put("isManager",false);
                }else{
                    containsError = true;
                    errorValue = "Username or Password incorrect";
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
            map.put("isValid",false);
            map.put("errorValue",errorValue);
        }else{
            map.put("username",username);
            map.put("isValid",true);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(new Gson().toJson(map));
        closeConnection();



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

}
