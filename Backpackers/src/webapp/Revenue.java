package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Revenue")
public class Revenue extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This is a get request, so we will just get values from query params(from the URL)
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        System.out.println(type+" "+value);
    }
}
