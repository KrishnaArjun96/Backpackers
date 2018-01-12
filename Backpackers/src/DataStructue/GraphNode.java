package DataStructue;

import JavaBeans.Airport;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/20/17.
 */
public class GraphNode {
    Airport origin;
    ArrayList<Airport> destinations;

    public GraphNode(Airport origin) {
        this.origin = origin;
        this.destinations = new ArrayList<>();
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public ArrayList<Airport> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Airport> destinations) {
        this.destinations = destinations;
    }

    public boolean addDestination(Airport newDestination) {
        return this.destinations.add(newDestination);
    }

}
