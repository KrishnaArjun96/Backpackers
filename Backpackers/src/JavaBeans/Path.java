package JavaBeans;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/21/17.
 */
public class Path {
    ArrayList<Connection> connections;

    public Path() {
        connections = new ArrayList<>();
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }
}

