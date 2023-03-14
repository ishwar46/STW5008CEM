package One;
import java.util.*;

import java.util.*;

//public class QuestionB {
//
//    // A class to represent a graph.
//    // Size of Matrix will be V (number of vertices in graph) * V
//    private static final int SOURCE_DEVICE = 0;
//    private static final int NETWORK_CONNECTION = 1;
//    private final int V;
//    private final int[][] adjMatrix;
//    private final int faultyDevice;
//
//    // constructor
//    public QuestionB(int V, int[][] networkConnections, int faultyDevice) {
//        //defines the vertices
//        this.V = V;
//        // define the size of array as the number of vertices
//        this.adjMatrix = new int[V][V];
//        //looped over the elements of networkConnections and populate it to Graph
//        for (int[] connection : networkConnections) {
//            // Call AddConnection Function to Populate
//            addConnection(connection[0], connection[1]);
//        }
//        //defines the faultyDevice
//        this.faultyDevice = faultyDevice;
//    }
//
//    // Adds a connection to an undirected graph
//    public void addConnection(int src, int dest) {
//        // Add a connection from src to dest.
//        adjMatrix[src][dest] = NETWORK_CONNECTION;
//        // Since Network graph is undirected, add a connection from dest to src also
//        adjMatrix[dest][src] = NETWORK_CONNECTION;
//    }
//
//    // Removes a vertex and all connections connected to it
//    public void removeDevice() {
//        // Remove the device from the matrix
//        for (int i = 0; i < V; i++) {
//            adjMatrix[i][faultyDevice] = 0;
//            adjMatrix[faultyDevice][i] = 0;
//        }
//    }
//
//    // Returns a list of all disconnected devices
//    public List<Integer> getDisconnectedDevices() {
//        //Call removeDevice function to remove the faulty device from Graph
//        removeDevice();
//        // Mark all the vertices as not visited
//        boolean[] visited = new boolean[V];
//        //Defined a List of Integer as subgraphs that  returns disconnected devices
//        List<Integer> disconnectedDevices = new ArrayList<>();
//
//        // Find all disconnected devices
//        for (int v = 0; v < V; ++v) {
//            //Checks if Vertex is Visited or is faulty or not
//            //If Visited, it is not necessary to visit again and also if it is faulty then it automatically separates graphs
//            if (!visited[v] && v != faultyDevice) {
//                // Defined a subgraph for each as there might be multiple small disconnected due to one fault point
//                List<Integer> subgraph = new ArrayList<>();
//                // Print all reachable vertices from v
//                DFSUtil(v, visited, subgraph);
//                // if the Subgraph is the fault point or if the sub
//                //graph has only one vertex, then it is not disconnected
//                if (subgraph.size() > 1 || subgraph.contains(faultyDevice)) {
//                    disconnectedDevices.addAll(subgraph);
//                }
//            }
//        }
//// Add the faulty device back to the graph after finding all disconnected devices
//        addFaultyDeviceBack();
//// Sort the list of disconnected devices
//        Collections.sort(disconnectedDevices);
//        return disconnectedDevices;
//    }
//
//    // A recursive function to print DFS starting from v
//    private void DFSUtil(int v, boolean[] visited, List<Integer> subgraph) {
//        // Mark the current node as visited and add it to the subgraph
//        visited[v] = true;
//        subgraph.add(v);
//        // Recur for all the vertices adjacent to this vertex
//        for (int i = 0; i < V; ++i) {
//            if (adjMatrix[v][i] == NETWORK_CONNECTION && !visited[i]) {
//                DFSUtil(i, visited, subgraph);
//            }
//        }
//    }
//
//    // Adds the faulty device back to the graph
//    private void addFaultyDeviceBack() {
//        for (int i = 0; i < V; i++) {
//            adjMatrix[i][faultyDevice] = NETWORK_CONNECTION;
//            adjMatrix[faultyDevice][i] = NETWORK_CONNECTION;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[][] networkConnections = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
//        int faultyDevice = 4;
//        QuestionB graph = new QuestionB(8, networkConnections, faultyDevice);
//        System.out.println("Disconnected Devices: " + graph.getDisconnectedDevices());
//    }
//}


class QuestionB {
    // A class to represent a graph.
        // Number of vertices in graph
        int numVertices;
        // Adjacency matrix to define the graph
        int[][] adjacencyMatrix;
        // Broken node
        int brokenNode;

    // Constructor
    QuestionB(int numVertices, int[][] edges, int brokenNode) {
        // Define the number of vertices
        this.numVertices = numVertices;
        // Define the size of the adjacency matrix as the number of vertices
        this.adjacencyMatrix = new int[numVertices][numVertices];
        // Loop over the elements of edges and populate the graph
        for (int[] edge : edges) {
            // Call addEdge function to populate
            addEdge(edge[0], edge[1]);
        }
        // Define the broken node
        this.brokenNode = brokenNode;
    }

    // Adds an edge to an undirected graph
    void addEdge(int source, int destination) {
        // Add an edge from source to destination
        adjacencyMatrix[source][destination] = 1;
        // Since the network graph is undirected, add an edge from destination to source also
        adjacencyMatrix[destination][source] = 1;
    }

    // Removes a vertex and all edges connected to it
    void removeVertex() {
        // Remove the vertex from the matrix
        for (int i = 0; i < numVertices; i++) {
            adjacencyMatrix[i][brokenNode] = 0;
            adjacencyMatrix[brokenNode][i] = 0;
        }
    }

    // Returns a list of all disconnected nodes
    List<Integer> getDisconnectedSubgraphs() {
        // Call removeVertex function to remove the broken node from the graph
        removeVertex();
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[numVertices];
        // Define a list of integers as subgraphs that returns disconnected nodes
        List<Integer> subgraphs = new ArrayList<>();

        // Find all subgraphs
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            // Check if vertex is visited or is broken or not
            // If visited, it is not necessary to visit again and also if it is broken then it automatically separates graphs
            if (!visited[vertex] && vertex != brokenNode) {
                // Define a subgraph for each as there might be multiple small disconnected subgraphs due to one breakage point
                List<Integer> subgraph = new ArrayList<>();
                // Print all reachable vertices from vertex
                DFSUtil(vertex, visited, subgraph);
                // If the subgraph is the breakpoint or if the subgraph has source of network then
                // it is not disconnected from the network
                if (!subgraph.contains(brokenNode) && !subgraph.contains(0)) {
                    // If above condition satisfied then add all small parts of subgraph to subgraphs
                    subgraphs.addAll(subgraph);
                }
            }
        }
        // Finally return subgraphs
        return subgraphs;
    }

    void DFSUtil(int vertex, boolean[] visited, List<Integer> subgraph) {
        // Mark the current node as visited and add it to the subgraph
        visited[vertex] = true;
        subgraph.add(vertex);
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < numVertices; ++i) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                DFSUtil(i, visited, subgraph);
            }
        }
    }
        //Main Method
    public static void main(String[] args) {
        //Edges OF the Graph
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        //broken Node
        int brokenNode = 4;
        // Create a graph
        QuestionB g = new QuestionB(8,edges,brokenNode);
        System.out.println("Impacted Device:");
        //Get Disconnected Networks
        List<Integer> subgraphs = g.getDisconnectedSubgraphs();
        System.out.println(subgraphs);
    }
}
