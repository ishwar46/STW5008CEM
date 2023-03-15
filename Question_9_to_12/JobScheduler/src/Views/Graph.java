package Views;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[numVertices];
        boolean[] recStack = new boolean[numVertices]; // to detect cycle
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        // Perform DFS on each vertex
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, recStack, stack);
            }
        }

        // Add vertices to the result in the order they are popped from the stack
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    // Perform DFS on a vertex
    private void dfs(int u, boolean[] visited, boolean[] recStack, Stack<Integer> stack) {
        visited[u] = true;
        recStack[u] = true;

        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                dfs(v, visited, recStack, stack);
            } else if (recStack[v]) { // cycle detected
                throw new IllegalArgumentException("The graph has a cycle!");
            }
        }

        recStack[u] = false;
        stack.push(u);
    }
}
