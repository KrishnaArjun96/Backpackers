package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "employee")
public class employee extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        System.out.println("In servlet");
        String ssn = data.get("ssn").getAsString();
        String firstName = data.get("firstName").getAsString();
        String lastName = data.get("lastName").getAsString();
        String address = data.get("address").getAsString();
        String city = data.get("city").getAsString();
        String state = data.get("state").getAsString();
        String country = data.get("country").getAsString();
        String zipCode = data.get("zip").getAsString();
        String phone = data.get("phone").getAsString();
        String startDate = data.get("startDate").getAsString();
        String role = data.get("role").getAsString();
        int wage = data.get("wage").getAsInt();
        System.out.println(city);

        try {
            ExecQuery.execQuery("INSERT INTO Person (FirstName, LastName, Address, City, State, Country, ZipCode, Phone) VALUES (" + firstName + "," + lastName + "," + address + "," + city + "," + state + "," + country + "," + zipCode + "," + phone + ")");
            String personId = ExecQuery.execQuery("SELECT PersonId FROM Person WHERE FirstName=\"" + firstName + "\" AND LastName=\"" + lastName + "\" AND Address=\"" + address + "\" AND City=\"" + phone + "\" AND State=\"" + state + "\" AND Country=\"" + country + "\" AND ZipCode=\"" + zipCode + "\" AND Phone=\"" + phone + "\"").getString(1);
            ExecQuery.execQuery("INSERT INTO Employee (PersonId, SSN, StateDate, Role, WageHourly) VALUES (" + personId + "," + ssn + "," + startDate + "," + role + "," + wage + ")");
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.getWriter().write(new Gson().toJson(resultSet));
        }
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", true);
        response.getWriter().write(new Gson().toJson(resultSet));
        System.out.println(ssn);

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update an employee record
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // delete an employee record
        // data param is only the ssn of the employee
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String ssn = data.get("ssn").getAsString();

        try {
            String personId = ExecQuery.execQuery("SELECT PersonId FROM Employee WHERE SSN=\"" + ssn + "\"").getString(1);
            ExecQuery.execQuery("DELETE FROM Employee WHERE SSN=" + ssn + "\"");
            ExecQuery.execQuery("DELETE FROM Person WHERE Id=" + personId);
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.getWriter().write(new Gson().toJson(resultSet));
        }
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", true);
        response.getWriter().write(new Gson().toJson(resultSet));

    }

}
