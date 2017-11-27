package webapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

        String username = data.get("username").getAsString();
        String password = data.get("password").getAsString();

        System.out.println(username+"----"+password);

        Map<String,Object> map = new HashMap<>();
        map.put("isValid",true);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(new Gson().toJson(map));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

}
