import java.util.LinkedList;
import java.util.Queue;

public class LongestPath {
    static class Cell {
        int row;
        int col;
        int value;
        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    private static int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static int longestIncreasingPath(int[][] matrix) {
        int[] max = {Integer.MIN_VALUE};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                bfs(i, j, matrix, max);
            }
        }
        return max[0];
    }

    private static void bfs(int row, int col, int[][] matrix, int[] max) {
        int M = matrix.length;
        int N = matrix[0].length;
        boolean[][] visited = new boolean[M][N];
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(row, col, matrix[row][col]));
        visited[row][col] = true;
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell cur = queue.poll();
                max[0] = Math.max(max[0], level);
                for (int[] dir : DIRS) {
                    int nextRow = dir[0] + cur.row;
                    int nextCol = dir[1] + cur.col;
                    if (nextRow >= 0 && nextRow < M && nextCol >= 0 && nextCol < N && !visited[nextRow][nextCol] && matrix[nextRow][nextCol] > matrix[cur.row][cur.col]) {
                        queue.offer(new Cell(nextRow, nextCol, matrix[nextRow][nextCol]));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
            level++;
        }
    }
    public static void main(String[] args) {
        int[][] test = {{1, 2}, {2, 2}};
        System.out.println(longestIncreasingPath(test));
    }
}
