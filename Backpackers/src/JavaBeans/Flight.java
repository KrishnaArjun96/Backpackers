package JavaBeans;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/11/17.
 */
public class Flight implements java.io.Serializable {
    Airline airline;
    String flightNo;
    int stopsNo;
    char[] days;
    boolean isVisible;

    public Flight(Airline airline, String flightNo, int stopsNo, char[] days) {
        this.airline = airline;
        this.flightNo = flightNo;
        this.stopsNo = stopsNo;
        this.days = days;
        this.isVisible = true;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public int getStopsNo() {
        return stopsNo;
    }

    public void setStopsNo(int stopsNo) {
        this.stopsNo = stopsNo;
    }

    public char[] getDays() {
        return days;
    }

    public void setDays(char[] days) {
        this.days = days;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
