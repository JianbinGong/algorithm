import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    String[] direction = {"r", "l", "u", "d"};
    int m, n;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        m = maze.length;
        n = maze[0].length;
        String[][] path = new String[m][n];
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            Arrays.fill(path[i], "");
        }

        Queue<int[]> queue = new LinkedList<>();
        dis[ball[0]][ball[1]] = 0;
        queue.add(new int[]{ball[0], ball[1], 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < dirs.length; i++) {
                int x = cur[0];
                int y = cur[1];
                int z = cur[2];
                while (x + dirs[i][0] >= 0 && x + dirs[i][0] < m && y + dirs[i][1] >= 0
                        && y + dirs[i][1] < n && maze[x + dirs[i][0]][y + dirs[i][1]] == 0) {
                    x += dirs[i][0];
                    y += dirs[i][1];
                    z++;
                    if (x == hole[0] && y == hole[1]) break;
                }
                String temp = path[cur[0]][cur[1]];
                temp += direction[i];
                if (dis[x][y] > z) {
                    dis[x][y] = z;
                    path[x][y] = temp;
                    queue.add(new int[]{x, y, z});
                } else if (dis[x][y] == z) {
                    if (temp.compareTo(path[x][y]) < 0) path[x][y] = temp;
                }
            }
        }
        return dis[hole[0]][hole[1]] == Integer.MAX_VALUE ? "impossible" : path[hole[0]][hole[1]];
    }

    public static void main(String[] args) {
        Maze test = new Maze();
        int[][] testData = {{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,0,1}, {0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {0, 1};
        System.out.println(test.findShortestWay(testData, ball, hole));
    }
}
