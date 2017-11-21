package webapp;

import Classes.ExecQuery;
import JavaBeans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static Classes.Data.*;

@WebServlet(name = "Search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_origin = request.getParameter("source");
        String user_destination = request.getParameter("destination");
        String startDate = request.getParameter("departing");
        Airport origin = findAirport(user_origin);
        Airport destination = findAirport(user_destination);

        ArrayList<ArrayList<Airport>> allPaths = CONNECTIONS.getAllPaths(origin, destination);
        /*ArrayList<PathDisplay> displays = new ArrayList<>();
        for(ArrayList<Airport> path: allPaths) {
            PathDisplay newDisplay = new PathDisplay(path);
            try {
                newDisplay.addLegDisplays();
                displays.add(newDisplay);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        int index = 1;
        for(PathDisplay path: displays) {
            System.out.println("PATH " + index++);
            for(LegDisplay leg: path.getDisplays()) {
                System.out.println(leg.getOrigin().getId() + " " + leg.getDestination().getId());
                for(Flight flight: leg.getFlights()) {
                    System.out.print(flight.getAirline().getId() + " " + flight.getFlightNo() + "\t");
                    System.out.println(flight.getLegs().get(0).getDeparture() + " " + flight.getLegs().get(0).getArrival());
                }
            }
            System.out.println();
        }*/

        request.setAttribute("paths", allPaths);
        request.setAttribute("pathCount", allPaths.size());
        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Checks all the legs i.e. if the difference between time of arrival of the first leg and the time of departure
     * is less than at least 1 hour 30 mins.
     * This updates the total travel days (attribute of FlightDisplay object).
     * This also checks if the flight is available on that particular day.
     * @param flight
     * @return
     */
/*
    public boolean validateFlightDisplay(PathDisplay flight) {
        // @todo
        return true;
    }
*/

    /**
     *
     * @param time1
     * @param time2
     * @return -1 if time2 < time1 i.e. the flight arrives the next day.
     *          1 if the flight arrives on the same day.
     *          0 if both the times are the same.
     */
    public int compareTime(String time1, String time2) {
        // @todo
        return 0;
    }

    public boolean isOperational(Leg l) {
        return true;
    }
}
