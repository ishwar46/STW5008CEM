package Two;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//You are given a 2D array containing hierarchical information about certain species,
//        with edge[i]=[xi,yi], where node xi is connected to xj. You are also provide
//        d an array of values associated with each species, such that value[i] reflec
//        ts the ith nodes value. If the greatest common divisor of two values is 1,
//        they are "relatively prime." Any other node on the shortest path from that
//        node to the absolute parent node is an ancestor of certain species i. Return
//        a list of nearest ancestors, where result[i] is the node i's nearest ances
//        tor such that values[i] and value[result[i]] are both relative primes otherw
//        ise -1.
public class QuestionTwoA {

    class Solution {
        public int gcd(int a, int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }

        public void dfs(int[] nums, LinkedList<Integer>[] graph, int depth, int node, boolean[] visited, int[] ans, Map<Integer, int[]> ancestorMap, boolean[][] isCoprime) {
            if (visited[node]) {
                return;
            }
            visited[node] = true;
            int ancestor = -1;
            int d = Integer.MAX_VALUE;
            for (int i = 1; i < 51; i++) {
                if (isCoprime[nums[node]][i] && ancestorMap.containsKey(i)) {
                    if (depth - ancestorMap.get(i)[0] <= d) {
                        d = depth - ancestorMap.get(i)[0];
                        ancestor = ancestorMap.get(i)[1];
                    }
                }
            }
            ans[node] = ancestor;
            int[] exist = (ancestorMap.containsKey(nums[node])) ? ancestorMap.get(nums[node]) : new int[]{-1, -1};
            ancestorMap.put(nums[node], new int[]{depth, node});
            for (int child : graph[node]) {
                if (visited[child]) {
                    continue;
                }
                dfs(nums, graph, depth + 1, child, visited, ans, ancestorMap, isCoprime);
            }
            if (exist[0] != -1) {
                ancestorMap.put(nums[node], exist);
            } else {
                ancestorMap.remove(nums[node]);
            }
            return;
        }

        public int[] getCoprimes(int[] nums, int[][] edges) {
            boolean[][] isCoprime = new boolean[51][51];
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (gcd(i, j) == 1) {
                        isCoprime[i][j] = true;
                        isCoprime[j][i] = true;
                    }
                }
            }
            int n = nums.length;
            LinkedList<Integer>[] graph = new LinkedList[n];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[0] = -1;
            Map<Integer, int[]> ancestorMap = new HashMap<>();
            boolean[] visited = new boolean[n];
            dfs(nums, graph, 0, 0, visited, ans, ancestorMap, isCoprime);
            return ans;
        }
    }

    public static void main(String[] args) {
        QuestionTwoA q = new QuestionTwoA();
        Solution s = q.new Solution();
        int[] nums = {5, 6, 10, 2, 3, 6, 15};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] ans = s.getCoprimes(nums, edges);
        System.out.println("Output : " + Arrays.toString(ans));
    }
}

