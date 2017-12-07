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
import java.text.SimpleDateFormat;
import java.util.Date;

import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;

/**
 * Created by Rahul on 12/05/17.
 */
@WebServlet(name = "customer")
public class customer extends HttpServlet {
    public static String getUserName(String fName, String lName) {
        String uName = "";
        uName = "" + ((char)fName.charAt(0)) + ((char)Character.toUpperCase(lName.charAt(0)));
        uName = uName.concat(lName.substring(1));
        int count = 0;
        try {
            ResultSet rs = ExecQuery.execQuery("SELECT COUNT(*) FROM Person WHERE Username='" + uName + "'");
            if (rs.next()) count = rs.getInt(1);
            if(count > 0) uName = uName.concat(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return uName;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String userId = data.get("email").getAsString();
        String firstName = data.get("firstName").getAsString();
        String lastName = data.get("lastName").getAsString();
        String address = data.get("address").getAsString();
        String city = data.get("city").getAsString();
        String state = data.get("state").getAsString();
        String country = data.get("country").getAsString();
        String zipCode = data.get("zip").getAsString();
        String phone = data.get("phone").getAsString();
        String card = data.get("card").getAsString();
        String seatPref = data.get("seat").getAsString();
        String mealPref = data.get("meal").getAsString();
        int rate = data.get("rate").getAsInt();
        boolean success = true;
        String error = "";
        int personId = 0;

        try {
            createConnection();
            String exec = "INSERT INTO Person (FirstName, LastName, Address, City, State, Country, ZipCode, Phone, Username, Password) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, city);
            pstmt.setString(5, state);
            pstmt.setString(6, country);
            pstmt.setString(7, zipCode);
            pstmt.setString(8, phone);
            pstmt.setString(9, getUserName(firstName.toLowerCase(), lastName));
            pstmt.setString(10, firstName.concat(lastName).toLowerCase());
            pstmt.executeUpdate();
            exec = "SELECT Id FROM Person WHERE FirstName='" + firstName + "' AND LastName='" + lastName + "' AND Address='" + address + "' AND City='" + city + "' AND State='" + state + "' AND Country='" + country + "' AND ZipCode='" + zipCode + "' AND Phone='" + phone + "'";
            ResultSet rs_set = ExecQuery.execQuery(exec);
            while (rs_set.next()) personId = rs_set.getInt(1);

            exec = "INSERT INTO Customer (PersonId, UserId, JoinDate, CardNo, SeatPref, MealPref, Rating) VALUES (?,?,?,?,?,?,?)";
            pstmt = ExecQuery.updateTable(exec);

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String joinDate = sdfDate.format(now);
            pstmt.setInt(1, personId);
            pstmt.setString(2, userId);
            pstmt.setString(3, joinDate);
            pstmt.setString(4, card);
            pstmt.setString(5, seatPref);
            pstmt.setString(6, mealPref);
            pstmt.setInt(7, rate);
            pstmt.executeUpdate();

        } catch (Exception e) {
            error = e.toString();
            try {
                if (personId != 0)
                    ExecQuery.execQuery("DELETE FROM Person WHERE Id=" + personId);
            } catch (Exception e1) {
            }
            success = false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", success);
        if (!success) {
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String firstName = data.get("firstName").getAsString();
        String lastName = data.get("lastName").getAsString();
        String address = data.get("address").getAsString();
        String city = data.get("city").getAsString();
        String state = data.get("state").getAsString();
        String country = data.get("country").getAsString();
        String zipCode = data.get("zip").getAsString();
        String phone = data.get("phone").getAsString();
        String user = data.get("user").getAsString();
        String cardNo = data.get("card").getAsString();
        String seatPrf = data.get("seat").getAsString();
        String mealPref = data.get("meal").getAsString();
        int rate = data.get("rate").getAsInt();
        boolean success = true;
        String error = "";
        int personId = 0;
        try {
            createConnection();
            ResultSet rs_customer = ExecQuery.execQuery("SELECT * FROM Customer WHERE UserId='" + user + "'");
            while (rs_customer.next()) {
                personId = rs_customer.getInt(1);
            }

            if (firstName != null) {
                String exec = "UPDATE Person SET FirstName=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, firstName);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (lastName != null) {
                String exec = "UPDATE Person SET LastName=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, lastName);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (address != null) {
                String exec = "UPDATE Person SET Address=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, address);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (city != null) {
                String exec = "UPDATE Person SET City=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, city);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (state != null) {
                String exec = "UPDATE Person SET State=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, state);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (country != null) {
                String exec = "UPDATE Person SET Country=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, country);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (zipCode != null) {
                String exec = "UPDATE Person SET ZipCode=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, zipCode);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (phone != null) {
                String exec = "UPDATE Person SET Phone=? WHERE Id=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, phone);
                pstmt.setInt(2, personId);
                pstmt.executeUpdate();
            }
            if (cardNo != "") {
                String exec = "UPDATE Customer SET CardNo=? WHERE UserId=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, cardNo);
                pstmt.setString(2, user);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }
            if (seatPrf != "") {
                String exec = "UPDATE Customer SET SeatPref=? WHERE UserId=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, seatPrf);
                pstmt.setString(2, user);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }
            if (mealPref != "") {
                String exec = "UPDATE Customer SET MealPref=? WHERE UserId=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setString(1, mealPref);
                pstmt.setString(2, user);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }
            if (rate != 0) {
                String exec = "UPDATE Customer SET Rating=? WHERE UserId=? AND PersonId=?";
                PreparedStatement pstmt = ExecQuery.updateTable(exec);
                pstmt.setInt(1, rate);
                pstmt.setString(2, user);
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
        if (!success) {
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String user = data.get("user").getAsString();
        boolean success = true;
        String error = "";
        int personId = 0;

        try {
            createConnection();
            ResultSet rs_customer = ExecQuery.execQuery("SELECT PersonId FROM Customer WHERE UserId='" + user + "'");
            while (rs_customer.next()) {
                personId = rs_customer.getInt(1);
            }
            String exec = "DELETE FROM Customer WHERE UserId=? AND PersonId=?";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setString(1, user);
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
        if (!success) {
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            createConnection();
            ResultSet rs_employee = ExecQuery.execQuery("SELECT * FROM Customer");
            JsonArray jsonArray = new JsonArray();
            while (rs_employee.next()) {
                JsonObject jsonObject = new JsonObject();
                String personId = rs_employee.getString(1);
                ResultSet rs_person = ExecQuery.execQuery("SELECT * FROM Person WHERE Id=" + personId);
                String lName = "", fName = "", address = "", city = "", state = "", country = "";
                String phone = "", zip = "", userId = "", joinDate = "", cardNo = "", seatPref = "", mealPref = "", rating = "";
                while (rs_person.next()) {
                    fName = rs_person.getString(2);
                    lName = rs_person.getString(3);
                    address = rs_person.getString(4);
                    city = rs_person.getString(5);
                    state = rs_person.getString(6);
                    phone = rs_person.getString(7);
                    zip = rs_person.getString(8);
                    country = rs_person.getString(9);
                }
                userId = rs_employee.getString(2);
                joinDate = rs_employee.getString(3);
                cardNo = rs_employee.getString(4);
                seatPref = rs_employee.getString(5);
                mealPref = rs_employee.getString(6);
                rating = rs_employee.getString(7);

                jsonObject.addProperty("fName", fName);
                jsonObject.addProperty("lName", lName);
                jsonObject.addProperty("address", address);
                jsonObject.addProperty("city", city);
                jsonObject.addProperty("state", state);
                jsonObject.addProperty("country", country);
                jsonObject.addProperty("phone", phone);
                jsonObject.addProperty("zip", zip);
                jsonObject.addProperty("userId", userId);
                jsonObject.addProperty("joinDate", joinDate);
                jsonObject.addProperty("cardNo", cardNo);
                jsonObject.addProperty("seatPref", seatPref);
                jsonObject.addProperty("mealPref", mealPref);
                jsonObject.addProperty("rating", rating);
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
