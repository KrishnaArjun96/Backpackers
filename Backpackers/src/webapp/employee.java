package webapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "employee")
public class employee extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

        System.out.println("In servlet");
        String ssn = data.get("ssn").getAsString();
        String name = data.get("name").getAsString();
        String startDate = data.get("startDate").getAsString();
        String role = data.get("role").getAsString();
        int wage = data.get("wage").getAsInt();

        System.out.println(ssn);

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update an employee record
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // delete an employee record
        // data param is only the ssn of the employee

    }

}
