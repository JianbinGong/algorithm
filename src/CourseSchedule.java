import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
        }
        return topologicalSort(graph);
    }

    private int[] topologicalSort(List<List<Integer>> graph) {
        int[] incomings = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            for (int course : graph.get(i)) {
                incomings[course]++;
            }
        }
        int numExpanded = 0;
        int[] result = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomings.length; i++) {
            if (incomings[i] == 0) {
                queue.offer(i);
                result[numExpanded++] = i;
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            List<Integer> neis = graph.get(course);
            for (Integer nei : neis) {
                incomings[nei]--;
                if (incomings[nei] == 0) {
                    queue.offer(nei);
                    result[numExpanded++] = nei;
                }
            }
        }
        return numExpanded == graph.size() ? result : new int[0];
    }
}
