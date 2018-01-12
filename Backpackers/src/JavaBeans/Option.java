package JavaBeans;

import Classes.ExecQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static Classes.Data.getLegId;

/**
 * Created by Rahul on 11/21/17.
 */
public class Option {
    ArrayList<Leg> legs;
    int totalDuration;
    int[] layovers;
    String[] dates;
    ArrayList<Airline> airlines;
    ArrayList<Integer> classes;
    double totalFare;
    String time;
    ArrayList<String> stops;
    int passengers;
    //MORE ATTRIBUTES TO FOLLOW.

    public Option() {
        legs = new ArrayList<>();
        airlines = new ArrayList<>();
        classes = new ArrayList<>();;
        stops = new ArrayList<>();
    }

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int[] getLayovers() {
        return layovers;
    }

    public void setLayovers(int[] layovers) {
        this.layovers = layovers;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(ArrayList<Airline> airlines) {
        this.airlines = airlines;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public ArrayList<Integer> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Integer> classes) {
        this.classes = classes;
    }

    public ArrayList<String> getStops() {
        return stops;
    }

    public void setStops(ArrayList<String> stops) {
        this.stops = stops;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void updateTotalFare(String prefClass) throws SQLException, ClassNotFoundException {
        String[] classArray = {"eco", "bus", "fc"};
        ArrayList<String> classArrayList = new ArrayList<String>(Arrays.asList(classArray));
        int classIndex = classArrayList.indexOf(prefClass) + 1;
        double totalFare = 0;
        for(Leg leg: this.legs) {
            ResultSet rs = ExecQuery.execQuery("SELECT COUNT(*) FROM Class WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "'");
            int maxRank = 0;
            while(rs.next()) {
                maxRank = rs.getInt(1);
            }
            if(maxRank < classIndex) classIndex = maxRank;
            ResultSet rs_class = ExecQuery.execQuery("SELECT * FROM Class WHERE FlightNo=" + leg.getFlight().getFlightNo() + " AND AirlineId='" + leg.getFlight().getAirline().getId() + "' AND ClassRank=" + classIndex + " AND IsVisible=1");
            while(rs_class.next()) {
                totalFare += Double.parseDouble(rs_class.getString(6));
                this.classes.add(rs_class.getInt(1));
            }
        }
        this.setTotalFare(totalFare*this.passengers);
    }

    public void updateAirlines() {
        for(Leg leg: this.legs) {
            if(!this.getAirlines().contains(leg.getFlight().getAirline())) {
                this.airlines.add(leg.getFlight().getAirline());
            }
        }
    }

    public void updateTime() {
        String dep = this.getLegs().get(0).getDeparture();
        String arr = this.getLegs().get(this.getLegs().size() - 1).getArrival();
        this.time = dep.concat(" - ").concat(arr);
    }

    public void updateStops() {
        for(int i=0; i<this.getLegs().size(); i++) {
            if(i == this.getLegs().size() - 1) return;
            this.stops.add(this.getLegs().get(i).getDestination().getId());
        }
    }
}
