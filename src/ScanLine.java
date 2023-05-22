import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// count airplane
public class ScanLine {
    public static class Interval {
        Integer start, end;
        Interval(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class Node implements Comparable<Node> {
        Integer val;
        boolean isStart;
        Node(Integer val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }

        public int compareTo(Node other) {
            if (this.val == other.val) {
                return Boolean.compare(this.isStart, other.isStart);
            } else {
                return Integer.compare(this.val, other.val);
            }
        }
    }


    public static int countOfAirplanes(List<Interval>airplanes) {
        int count = 0;
        int max = 0;
        int size = airplanes.size();

        Node[] array = new Node[2*size];

        for (int i = 0; i < size; i++) {
            array[2 * i] = new Node(airplanes.get(i).start, true);
            array[2 * i + 1] = new Node(airplanes.get(i).end, false);
        }

        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i].isStart) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count--;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] array = {{1, 10}, {2, 3}, {5, 8}, {4, 7}};
        List<Interval> airplanes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            airplanes.add(new Interval(array[i][0], array[i][1]));
        }
        System.out.println(countOfAirplanes(airplanes));
    }
}
