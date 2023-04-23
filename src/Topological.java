import java.util.*;

public class Topological {
    public static boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                if (map.get(seq[i]) == null) {
                    map.put(seq[i], new HashSet<Integer>());
                }
                if (indegree.get(seq[i]) == null) {
                    indegree.put(seq[i], 0);
                }
                if (i > 0) {
                    indegree.put(seq[i], indegree.get(seq[i]) + 1);
                    map.get(seq[i - 1]).add(seq[i]);
                }
            }
        }
        if (indegree.size() > org.length) return false;
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int index = 0;
        int[] result = new int[org.length];
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            result[index++] = cur;
            Set<Integer> neis = map.get(cur);
            if (neis == null) continue;
            for (Integer nei : neis) {
                int cnt = indegree.get(nei);
                indegree.put(nei, cnt - 1);
                if (cnt == 1) {
                    queue.offer(nei);
                }
            }
            if (queue.size() > 1) {
                return false;
            }
        }
        for (int i = 0; i < org.length; i++) {
            if (org[i] != result[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] seq = {4,1,5,2,6,3};
        int[][] seqs = {{5,2,6,3}, {4,1,5,2}};
        sequenceReconstruction(seq, seqs);
    }
}
