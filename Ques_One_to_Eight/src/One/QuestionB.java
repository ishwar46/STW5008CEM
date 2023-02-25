package One;
import java.util.*;

    public class QuestionB {

        public static void main(String[] args) {
            int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
            int target = 4;
            List<Integer> impactedDevices = getImpactedDevices(edges, target);
            System.out.println("Impacted Device List: " + impactedDevices);
        }

        public static List<Integer> getImpactedDevices(int[][] edges, int target) {
            Map<Integer, Set<Integer>> adjList = new HashMap<>();
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adjList.computeIfAbsent(u, k -> new HashSet<>()).add(v);
                adjList.computeIfAbsent(v, k -> new HashSet<>()).add(u);
            }

            List<Integer> impactedDevices = new ArrayList<>();
            boolean[] visited = new boolean[edges.length + 1];
            dfs(target, visited, adjList);
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    impactedDevices.add(i);
                }
            }
            return impactedDevices;
        }

        public static void dfs(int curr, boolean[] visited, Map<Integer, Set<Integer>> adjList) {
            visited[curr] = true;
            if (adjList.containsKey(curr)) {
                for (int neighbor : adjList.get(curr)) {
                    if (!visited[neighbor]) {
                        dfs(neighbor, visited, adjList);
                    }
                }
            }
        }
    }


