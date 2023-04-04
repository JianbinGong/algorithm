import java.util.*;

public class KthClosestPoint {
    static class Pair {
        int x;
        int y;
        int z;
        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return Long.compare(a[p1.x] * a[p1.x] + b[p1.y] * b[p1.y] + c[p1.z] * c[p1.z], a[p2.x] * a[p2.x] + b[p2.y] * b[p2.y] + c[p2.z] * c[p2.z]);
            }
        });
        queue.offer(new Pair(0, 0, 0));
        Set<List<Integer>> visited = new HashSet<>();
        visited.add(Arrays.asList(0, 0, 0));
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (k == 1) return Arrays.asList(a[cur.x], b[cur.y], c[cur.z]);
            if (cur.x < a.length - 1 && !visited.contains(Arrays.asList(cur.x + 1, cur.y, cur.z))) {
                queue.offer(new Pair(cur.x + 1, cur.y, cur.z));
                visited.add(Arrays.asList(cur.x + 1, cur.y, cur.z));
            }
            if (cur.y < b.length - 1 && !visited.contains(Arrays.asList(cur.x, cur.y + 1, cur.z))) {
                queue.offer(new Pair(cur.x, cur.y + 1, cur.z));
                visited.add(Arrays.asList(cur.x, cur.y + 1, cur.z));
            }
            if (cur.z < c.length - 1 && !visited.contains(Arrays.asList(cur.x, cur.y, cur.z + 1))) {
                queue.offer(new Pair(cur.x, cur.y, cur.z + 1));
                visited.add(Arrays.asList(cur.x, cur.y, cur.z + 1));
            }
            k--;
        }
        return new ArrayList<Integer>();
    }
}
