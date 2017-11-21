package webapp;

import Classes.Data;
import JavaBeans.Airline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        ArrayList<Airline> airlines = new ArrayList<>();
        request.setAttribute("username", request.getParameter("username"));

        try {
            Data.Refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

}
