package Graph;

import java.util.*;

public class NearestAncestor {
    // The graph is represented as a dictionary where each key is a node
    // and the corresponding value is a list of its children.
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    // The values of the nodes are stored in a separate array.
    private int[] values;
    // The GCDs along the path from the root node to each node are stored in a list.
    private List<Integer> gcds = new ArrayList<>();
    // The nearest ancestors are stored in an array.
    private int[] result;

    public int[] nearestAncestor(int[] values, int[][] edges) {
        this.values = values;
        result = new int[values.length];
        Arrays.fill(result, -1);
        // Initialize the graph with empty lists for each node.
        for (int i = 0; i < values.length; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        // Add the edges to the graph.
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // Start the DFS from the root node (node 0) with no parent (-1).
        dfs(0, -1);
        return result;
    }

    private void dfs(int node, int parent) {
        // For each child of the current node, call the DFS recursively.
        for (int child : graph.get(node)) {
            if (child != parent) {
                dfs(child, node);
            }
        }
        // Compute the GCD of the current node's value and the GCD along the path from the root to the current node.
        int gcd = gcd(values[node], gcds.get(gcds.size() - 1));
        // If the GCD is 1, set the nearest ancestor to the current node.
        if (gcd == 1) {
            result[node] = node;
        } else {
            // Search for the nearest ancestor whose value is relatively prime to the current node's value.
            for (int i = gcds.size() - 1; i >= 0; i--) {
                if (gcds.get(i) == gcd) {
                    for (int j = i; j < gcds.size(); j++) {
                        int ancestor = getAncestor(j - i, node);
                        if (ancestor != -1 && isRelativelyPrime(values[ancestor], values[node])) {
                            result[node] = ancestor;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        // Add the current GCD to the list of GCDs along the path.
        gcds.add(gcd);
    }

    private int getAncestor(int distance, int node) {
        // Return the ancestor at the given distance from the current node, or -1 if no such ancestor exists.
        while (distance > 0 && node != -1) {
            node = graph.get(node).get(0);
            distance--;
        }
        return node;
    }

    private boolean isRelativelyPrime(int a, int b) {
        // Check if two numbers are relatively prime by computing their GCD.
        return gcd(a, b) == 1;
    }

    private int gcd(int a, int b) {
        // Compute the GCD of two numbers using Euclid's algorithm.
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        NearestAncestor nearestAncestor = new NearestAncestor();
        int[] values = {3, 2, 6, 6, 4, 7, 12};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] result = nearestAncestor.nearestAncestor(values, edges);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
