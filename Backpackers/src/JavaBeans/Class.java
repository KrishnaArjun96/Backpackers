package JavaBeans;

/**
 * Created by Rahul on 11/11/17.
 */
public class Class implements java.io.Serializable {
    Flight flight;
    String id;
    String aircraft;
    String name;
    double fare;
    int seatsNo;
    boolean isVisible;

    public Class(Flight flight, String id, String aircraft, String name, double fare, int seatsNo) {
        this.flight = flight;
        this.id = id;
        this.aircraft = aircraft;
        this.name = name;
        this.fare = fare;
        this.seatsNo = seatsNo;
        this.isVisible = true;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(int seatsNo) {
        this.seatsNo = seatsNo;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
