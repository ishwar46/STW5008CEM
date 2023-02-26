package One;
import java.util.*;

import java.util.*;

public class QuestionB {

    // A class to represent a graph.
    // Size of Matrix will be V (number of vertices in graph) * V
    private static final int SOURCE_DEVICE = 0;
    private static final int NETWORK_CONNECTION = 1;
    private final int V;
    private final int[][] adjMatrix;
    private final int faultyDevice;

    // constructor
    public QuestionB(int V, int[][] networkConnections, int faultyDevice) {
        //defines the vertices
        this.V = V;
        // define the size of array as the number of vertices
        this.adjMatrix = new int[V][V];
        //looped over the elements of networkConnections and populate it to Graph
        for (int[] connection : networkConnections) {
            // Call AddConnection Function to Populate
            addConnection(connection[0], connection[1]);
        }
        //defines the faultyDevice
        this.faultyDevice = faultyDevice;
    }

    // Adds a connection to an undirected graph
    public void addConnection(int src, int dest) {
        // Add a connection from src to dest.
        adjMatrix[src][dest] = NETWORK_CONNECTION;
        // Since Network graph is undirected, add a connection from dest to src also
        adjMatrix[dest][src] = NETWORK_CONNECTION;
    }

    // Removes a vertex and all connections connected to it
    public void removeDevice() {
        // Remove the device from the matrix
        for (int i = 0; i < V; i++) {
            adjMatrix[i][faultyDevice] = 0;
            adjMatrix[faultyDevice][i] = 0;
        }
    }

    // Returns a list of all disconnected devices
    public List<Integer> getDisconnectedDevices() {
        //Call removeDevice function to remove the faulty device from Graph
        removeDevice();
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        //Defined a List of Integer as subgraphs that  returns disconnected devices
        List<Integer> disconnectedDevices = new ArrayList<>();

        // Find all disconnected devices
        for (int v = 0; v < V; ++v) {
            //Checks if Vertex is Visited or is faulty or not
            //If Visited, it is not necessary to visit again and also if it is faulty then it automatically separates graphs
            if (!visited[v] && v != faultyDevice) {
                // Defined a subgraph for each as there might be multiple small disconnected due to one fault point
                List<Integer> subgraph = new ArrayList<>();
                // Print all reachable vertices from v
                DFSUtil(v, visited, subgraph);
                // if the Subgraph is the fault point or if the sub
                //graph has only one vertex, then it is not disconnected
                if (subgraph.size() > 1 || subgraph.contains(faultyDevice)) {
                    disconnectedDevices.addAll(subgraph);
                }
            }
        }
// Add the faulty device back to the graph after finding all disconnected devices
        addFaultyDeviceBack();
// Sort the list of disconnected devices
        Collections.sort(disconnectedDevices);
        return disconnectedDevices;
    }

    // A recursive function to print DFS starting from v
    private void DFSUtil(int v, boolean[] visited, List<Integer> subgraph) {
        // Mark the current node as visited and add it to the subgraph
        visited[v] = true;
        subgraph.add(v);
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < V; ++i) {
            if (adjMatrix[v][i] == NETWORK_CONNECTION && !visited[i]) {
                DFSUtil(i, visited, subgraph);
            }
        }
    }

    // Adds the faulty device back to the graph
    private void addFaultyDeviceBack() {
        for (int i = 0; i < V; i++) {
            adjMatrix[i][faultyDevice] = NETWORK_CONNECTION;
            adjMatrix[faultyDevice][i] = NETWORK_CONNECTION;
        }
    }

    public static void main(String[] args) {
        int[][] networkConnections = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int faultyDevice = 4;
        QuestionB graph = new QuestionB(8, networkConnections, faultyDevice);
        System.out.println("Disconnected Devices: " + graph.getDisconnectedDevices());
    }
}


