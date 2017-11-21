package DataStructue;

import JavaBeans.Airport;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/20/17.
 */
public class Graph {
    ArrayList<GraphNode> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public ArrayList<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public boolean addNode(Airport origin) {
        GraphNode newNode = new GraphNode(origin);
        return this.nodes.add(newNode);
    }

    public boolean addDestinationToNode(Airport origin, Airport destination) {
        for(int i=0; i<nodes.size(); i++) {
            GraphNode node = nodes.get(i);
            if(node.getOrigin().getId().equals(origin.getId())) {
                return nodes.get(i).addDestination(destination);
            }
        }
        GraphNode newNode = new GraphNode(origin);
        newNode.addDestination(destination);
        return nodes.add(newNode);
    }

    public void getAllPathsHelper(int originIndex, int destinationIndex, boolean[] visited, ArrayList<Airport> path, ArrayList<ArrayList<Airport>> allPaths) {
        visited[originIndex] = true;
        path.add(nodes.get(originIndex).getOrigin());
        if(originIndex == destinationIndex) {
            if(!checkIfPresent(path, allPaths)) allPaths.add((ArrayList<Airport>) path.clone());
        }
        else {
            for(Airport apt: nodes.get(originIndex).getDestinations()) {
                int i = findIndex(apt);
                if(!visited[i]) getAllPathsHelper(i, destinationIndex, visited, path, allPaths);
            }
        }

        path.remove(path.size() - 1);
        visited[originIndex] = false;
    }

    public int findIndex(Airport apt) {
        int index = 0;
        for(GraphNode node: nodes) {
            if(node.getOrigin().getId().equals(apt.getId())) return index;
            index++;
        }
        return -1;
    }

    public ArrayList<ArrayList<Airport>> getAllPaths(Airport origin, Airport destination) {
        boolean[] visited = new boolean[nodes.size()];
        ArrayList<Airport> path = new ArrayList<Airport>();
        ArrayList<ArrayList<Airport>> allPaths = new ArrayList<ArrayList<Airport>>();

        for(int i=0; i<nodes.size(); i++) visited[i] = false;
        getAllPathsHelper(this.findIndex(origin), this.findIndex(destination), visited, path, allPaths);

        return allPaths;
    }

    public boolean checkIfPresent(ArrayList<Airport> path, ArrayList<ArrayList<Airport>> allPaths) {
        for(ArrayList<Airport> element: allPaths) {
            if(element.containsAll(path) && element.size() == path.size()) return true;
        }
        return false;
    }
}
