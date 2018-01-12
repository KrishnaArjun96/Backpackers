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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;

@WebServlet(name = "employee")
public class employee extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
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
        double wage = data.get("wage").getAsDouble();
        boolean success = true;
        String error = "";
        int personId = 0;
        try {
            createConnection();
            String exec = "INSERT INTO Person (FirstName, LastName, Address, City, State, Country, ZipCode, Phone) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, city);
            pstmt.setString(5, state);
            pstmt.setString(6, country);
            pstmt.setString(7, zipCode);
            pstmt.setString(8, phone);
            pstmt.executeUpdate();
            exec = "SELECT Id FROM Person WHERE FirstName='" + firstName + "' AND LastName='" + lastName + "' AND Address='" + address + "' AND City='" + city + "' AND State='" + state + "' AND Country='" + country + "' AND ZipCode='" + zipCode + "' AND Phone='" + phone + "'";
            ResultSet rs_set = ExecQuery.execQuery(exec);
            while(rs_set.next()) personId = rs_set.getInt(1);
            exec = "INSERT INTO Employee (PersonId, SSN, StartDate, Role, WageHourly) VALUES (?,?,?,?,?)";
            pstmt = ExecQuery.updateTable(exec);
            pstmt.setInt(1, personId);
            pstmt.setString(2, ssn);
            pstmt.setString(3, startDate);
            pstmt.setString(4, role.toLowerCase());
            pstmt.setDouble(5, wage);
            pstmt.executeUpdate();

        } catch (Exception e) {
            error = e.toString();
            try {
                if(personId != 0)
                    ExecQuery.execQuery("DELETE FROM Person WHERE Id=" + personId);
            } catch (Exception e1) {}
            success = false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", success);
        if(!success){
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String ssn = data.get("ssn").getAsString();
        String firstName = data.get("firstName").getAsString();
        String lastName = data.get("lastName").getAsString();
        String address = data.get("address").getAsString();
        String city = data.get("city").getAsString();
        String state = data.get("state").getAsString();
        String country = data.get("country").getAsString();
        String zipCode = data.get("zip").getAsString();
        String phone = data.get("phone").getAsString();
        String role = data.get("role").getAsString();
        double wage = data.get("wage").getAsDouble();
        boolean success = true;
        String error = "";
        int personId = 0;
        try {
            createConnection();
            ResultSet rs_employee = ExecQuery.execQuery("SELECT * FROM Employee WHERE SSN='" + ssn + "'");
            while (rs_employee.next()) {
                personId = rs_employee.getInt(1);
            }
            if(!firstName.equals("")) {
                String exec = "UPDATE Person SET FirstName=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, firstName);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!lastName.equals("")) {
                String exec = "UPDATE Person SET LastName=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, lastName);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!address.equals("")) {
                String exec = "UPDATE Person SET Address=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, address);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!city.equals("")) {
                String exec = "UPDATE Person SET City=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, city);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!state.equals("")) {
                String exec = "UPDATE Person SET State=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, state);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!country.equals("")) {
                String exec = "UPDATE Person SET Country=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, country);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!zipCode.equals("")) {
                String exec = "UPDATE Person SET ZipCode=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, zipCode);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!phone.equals("")) {
                String exec = "UPDATE Person SET Phone=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, phone);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if(!role.equals("")) {
                String exec = "UPDATE Employee SET Role=? WHERE SSN=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, role);
                pstmt.setString(2, ssn);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }
            if(wage != 0) {
                String exec = "UPDATE Employee SET WageHourly=? WHERE SSN=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setDouble(1, wage);
                pstmt.setString(2, ssn);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            error = e.toString();
            success = false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", success);
        if(!success){
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // delete an employee record
        // data param is only the ssn of the employee
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String ssn = data.get("ssn").getAsString();
        boolean success = true;
        String error = "";
        int personId = 0;

        try {
            createConnection();
            ResultSet rs_employee = ExecQuery.execQuery("SELECT PersonId FROM Employee WHERE SSN='" + ssn + "'");
            while (rs_employee.next()) {
                personId = rs_employee.getInt(1);
            }
            String exec = "DELETE FROM Employee WHERE SSN=? AND PersonId=?";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setString(1, ssn);
            pstmt.setInt(2, personId);
            pstmt.executeUpdate();
            exec = "DELETE FROM Person WHERE Id=?";
            pstmt = ExecQuery.updateTable(exec);
            pstmt.setInt(1, personId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            error = e.toString();
            success = false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", success);
        if(!success){
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            createConnection();
            ResultSet rs_customer = ExecQuery.execQuery("SELECT * FROM Employee WHERE role='employee'");
            JsonArray jsonArray = new JsonArray();
            while (rs_customer.next()) {
                JsonObject jsonObject = new JsonObject();
                String personId = rs_customer.getString(1);
                ResultSet rs_person = ExecQuery.execQuery("SELECT * FROM Person WHERE Id="+personId);
                String lName = "", fName = "", address = "", city = "", state = "", country = "";
                String phone = "", zip = "", ssn = "", startDate = "", wage = "";
                while(rs_person.next()) {
                    fName = rs_person.getString(2);
                    lName = rs_person.getString(3);
                    address = rs_person.getString(4);
                    city = rs_person.getString(5);
                    state = rs_person.getString(6);
                    phone = rs_person.getString(7);
                    zip = rs_person.getString(8);
                    country = rs_person.getString(9);
                }
                ssn = rs_customer.getString(2);
                startDate = rs_customer.getString(3);
                wage = rs_customer.getString(5);
                jsonObject.addProperty("fName", fName);
                jsonObject.addProperty("lName", lName);
                jsonObject.addProperty("address", address);
                jsonObject.addProperty("city", city);
                jsonObject.addProperty("state", state);
                jsonObject.addProperty("country", country);
                jsonObject.addProperty("phone", phone);
                jsonObject.addProperty("zip", zip);
                jsonObject.addProperty("ssn", ssn);
                jsonObject.addProperty("startDate", startDate);
                jsonObject.addProperty("wage", wage);
                jsonArray.add(jsonObject);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(jsonArray));
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(resultSet));
        }
        closeConnection();
    }
}
