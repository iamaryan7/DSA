import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        
        // Build graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(k);
        suspicious[k] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph[curr]) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious method
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Return remaining methods after removing suspicious ones
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }

        return remaining;
    }
}