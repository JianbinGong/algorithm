import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazeII {
    private int[][] DIRS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private String[] move = {"l", "u", "r",  "d"};
    int m, n;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return "impossible";
        m = maze.length;
        n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] cost = new int[m][n];
        String[][] path = new String[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            Arrays.fill(path[i], "");
        }

        queue.offer(new int[]{ball[0], ball[1]});
        cost[ball[0]][ball[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < DIRS.length; i++) {
                int[] dir = DIRS[i];
                int nextRow = cur[0];
                int nextCol = cur[1];
                int count = 0;
                while (nextRow + dir[0] >= 0 && nextRow + dir[0] < m && nextCol + dir[1] >= 0
                        && nextCol + dir[1] < n && maze[nextRow + dir[0]][nextCol + dir[1]] == 0) {
                    count++;
                    nextRow = nextRow + dir[0];
                    nextCol = nextCol + dir[1];
                    if (nextRow == hole[0] && nextCol == hole[1]) {
                        break;
                    }
                }
                if (cost[nextRow][nextCol] > cost[cur[0]][cur[1]] + count) {
                    cost[nextRow][nextCol] =  cost[cur[0]][cur[1]] + count;
                    queue.offer(new int[]{nextRow, nextCol});
                    path[nextRow][nextCol] = path[cur[0]][cur[1]] + move[i];
                }
                if (cost[nextRow][nextCol] == (cost[cur[0]][cur[1]] + count)) {
                    String newString = path[cur[0]][cur[1]] + move[i];
                    if (nextRow == hole[0] && nextCol == hole[1]) {
                        System.out.println("old string is: " + path[nextRow][nextCol]);

                        System.out.println("new string is: " + newString);
                    }
                    if (path[nextRow][nextCol].compareTo(newString) > 0) {
                        path[nextRow][nextCol] = newString;
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }
                // int result = "lul".compareTo("ul");
                // System.out.println("result shoule be 1: " + result);
            }
        }

        return cost[hole[0]][hole[1]] == Integer.MAX_VALUE ? "impossible" : path[hole[0]][hole[1]];
    }

    public static void main(String[] args) {
        MazeII test = new MazeII();
        int[][] matrix = {{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,1,1}, {0,1,0,0,0}};
        int[] ball = {4, 3};
        int[] hole = {0, 1};
        test.findShortestWay(matrix, ball, hole);
    }
}
