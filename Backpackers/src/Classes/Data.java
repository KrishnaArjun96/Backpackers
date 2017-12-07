package Classes;

import DataStructue.Graph;
import JavaBeans.Airline;
import JavaBeans.Airport;
import JavaBeans.Flight;
import JavaBeans.Leg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Rahul on 11/12/17.
 */
public final class Data {

    //THESE ARE MASTER LISTS OF AIRPORTS, AIRLINES AND FLIGHTS (INCL. LEGS)
    public static ArrayList<Airport> AIRPORTS;
    public static ArrayList<Airline> AIRLINES;
    public static ArrayList<Leg> LEGS;
    public static Graph CONNECTIONS;

    //REFRESHES THE LIST WITH NO ELEMENTS.
    public static void initData() {
        AIRPORTS = new ArrayList<>();
        AIRLINES = new ArrayList<>();
        LEGS = new ArrayList<>();
        CONNECTIONS = new Graph();
    }

    /*
        THE NEXT THREE METHODS SHALL BE USED LATER.
     */
    public static Airport findAirport(String code) {
        for(Airport apt: AIRPORTS) {
            if(apt.getId().equalsIgnoreCase(code)) return apt;
        }
        return null;
    }

    public static Airline findAirline(String code) {
        for(Airline airline: AIRLINES) {
            if(airline.getId().equalsIgnoreCase(code)) return airline;
        }
        return null;
    }

    public static Flight findFlight(String code, String flightNumber) throws SQLException, ClassNotFoundException {
        Airline airline = findAirline(code);
        ResultSet rs_flight = ExecQuery.execQuery("SELECT * FROM Flight WHERE AirlineId=\"" + airline.getId() + "\" AND FlightNo=" + flightNumber + " AND IsVisible=1");
        if(rs_flight.next()) {
            Flight newFlight = new Flight(airline, flightNumber, Integer.parseInt(rs_flight.getString(3)), getDays(rs_flight.getString(4)));
            return newFlight;
        }
        return null;
    }

    public static char[] getDays(String days) {
        char[] days_op = new char[7];
        while(days.length() < 7) {
            days = "0".concat(days);
        }
        for(int i=0; i<7; i++) {
            days_op[i] = days.charAt(i);
        }

        return days_op;
    }

    public static ArrayList<Airport> getDestinations(String flight) {
        String[] flightComponents = flight.split(" ");
        String airlineCode = flightComponents[0];
        String flightNo = flightComponents[1];
        ArrayList<Airport> destinations = new ArrayList<>();
        for(Leg leg: LEGS) {
            if(leg.getFlight().getAirline().getId().equals(airlineCode) && leg.getFlight().getFlightNo().equals(flightNo))
                destinations.add(leg.getDestination());

        }

        return destinations;
    }

    public static ArrayList<Leg> findLegs(String origin, String destination) {
        ArrayList<Leg> legs = new ArrayList<>();
        for(Leg leg: LEGS) {
            if(leg.getOrigin().getId().equals(origin) && leg.getDestination().getId().equals(destination)) {
                legs.add(leg);
            }
        }
        return legs;
    }

    public static ArrayList<Leg> findLegs(String origin, String destination, String flight) {
        ArrayList<Leg> legs = new ArrayList<>();
        for(Leg leg: LEGS) {
            String legFlight = leg.getFlight().getAirline().getId().concat(" ").concat(leg.getFlight().getFlightNo());
            if(leg.getOrigin().getId().equals(origin) && leg.getDestination().getId().equals(destination) && legFlight.equals(flight)) {
                legs.add(leg);
            }
        }
        return legs;
    }

    public static int getLegId(Leg leg) throws SQLException, ClassNotFoundException {
        ResultSet rs = ExecQuery.execQuery("SELECT LegId FROM Leg WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "' AND Origin='" + leg.getOrigin().getId() + "' AND Destination='" + leg.getDestination().getId() + "'");
        int legId = 0;
        while(rs.next()) legId = rs.getInt(1);
        return legId;
    }

    //THIS METHOD CLEANS THE MASTER LISTS AND ADDS DATA FROM THE DB.
    public static void Refresh() throws SQLException, ClassNotFoundException {
        initData();

        ResultSet rs_airport = ExecQuery.execQuery("SELECT * FROM Airport");
        ResultSet rs_airline = ExecQuery.execQuery("SELECT * FROM Airline");
        ResultSet rs_leg = ExecQuery.execQuery("SELECT * FROM Leg");

        while (rs_airline.next()) {
            Airline newAirline = new Airline(rs_airline.getString(1), rs_airline.getString(2));
            AIRLINES.add(newAirline);
        }

        while (rs_airport.next()) {
            Airport newAirport = new Airport(rs_airport.getString(1), rs_airport.getString(2), rs_airport.getString(3), rs_airport.getString(4));
            AIRPORTS.add(newAirport);
        }

        while(rs_leg.next()) {
            int leg_id = rs_leg.getInt(1);
            int flightNo = rs_leg.getInt(2);
            String airline_id = rs_leg.getString(3);
            String origin = rs_leg.getString(4);
            String destination = rs_leg.getString(5);
            String departure = rs_leg.getString(6);
            String arrival = rs_leg.getString(7);
            String duration = rs_leg.getString(8);

            Flight flight = findFlight(airline_id, String.valueOf(flightNo));
            Leg newLeg = new Leg(flight, String.valueOf(leg_id), findAirport(origin), findAirport(destination), departure, arrival, duration);
            LEGS.add(newLeg);
            CONNECTIONS.addDestinationToNode(findAirport(origin), findAirport(destination));
        }
    }

    public static String getUserId(String userName) {
        String user = "";
        int personId = 0;
        try {
            ResultSet rs_user = ExecQuery.execQuery("SELECT * FROM Person WHERE Username='"+userName+"'");
            if(rs_user.next()) personId = rs_user.getInt(1);
            rs_user = ExecQuery.execQuery("SELECT * FROM Customer WHERE PersonId="+personId);
            if(rs_user.next()) user= rs_user.getString(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
