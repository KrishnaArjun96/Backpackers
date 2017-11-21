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

    public static ArrayList<Leg> findLegs(String origin, String destination) {
        ArrayList<Leg> legs = new ArrayList<>();
        for(Leg leg: LEGS) {
            if(leg.getOrigin().getId().equals(origin) && leg.getDestination().getId().equals(destination)) {
                legs.add(leg);
            }
        }
        return legs;
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

            Flight flight = findFlight(airline_id, String.valueOf(flightNo));
            Leg newLeg = new Leg(flight, String.valueOf(leg_id), findAirport(origin), findAirport(destination), departure, arrival);
            LEGS.add(newLeg);
            CONNECTIONS.addDestinationToNode(findAirport(origin), findAirport(destination));
        }
    }

    //THIS IS A HELPER METHOD TO GET THE DAYS OF OPERATIONS.
    public static char[] daysOperating(int days) {
        char[] daysArr = new char[7];
        int index = 6;
        while(days != 0) {
            int digit = days % 10;
            daysArr[index] = (char)('0' + digit);
            days = days/10;
            index--;
        }
        while(index >= 0) {
            daysArr[index] = '0';
            index--;
        }
        return daysArr;
    }
}
