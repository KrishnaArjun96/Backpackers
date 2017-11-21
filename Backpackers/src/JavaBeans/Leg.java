package JavaBeans;

/**
 * Created by Rahul on 11/11/17.
 */
public class Leg implements java.io.Serializable {
    Flight flight;
    String legId;
    Airport origin;
    Airport destination;
    String departure;
    String arrival;

    public Leg(Flight flight, String legId, Airport origin, Airport destination, String departure, String arrival) {
        this.flight = flight;
        this.legId = legId;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getLegId() {
        return legId;
    }

    public void setLegId(String legId) {
        this.legId = legId;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
