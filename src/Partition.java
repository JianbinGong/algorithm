import java.util.*;

public class Partition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (dislikes.length == 0 || dislikes[0].length == 0) return true;
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < dislikes.length; i++) {
            if (graph.get(dislikes[i][0]) == null) {
                graph.put(dislikes[i][0], new HashSet<>());
            } else {
                graph.get(dislikes[i][0]).add(dislikes[i][1]);
            }
            if (graph.get(dislikes[i][1]) == null) {
                graph.put(dislikes[i][1], new HashSet<>());
            } else {
                graph.get(dislikes[i][1]).add(dislikes[i][0]);
            }
        }
        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            if (!visited.containsKey(cur)) {
                visited.put(cur, 0);
            }
            int neiGroup = visited.get(cur) == 0 ? 1 : 0;
            Set<Integer> neis = graph.get(cur);
            for (Integer nei : neis) {
                if (visited.get(nei) == null) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Partition test = new Partition();
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 3}};
        test.possibleBipartition(3, dislikes);
    }
}
