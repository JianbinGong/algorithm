import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PutChair {
    static class Cell {
        int row;
        int col;
        public Cell (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> putChair(char[][] gym) {
        // at least one equipment
        int M = gym.length;
        int N = gym[0].length;
        int[][] cost = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gym[i][j] == 'E' && !addCost(i, j, gym, cost)) {
                    return Arrays.asList(-1, -1);
                }
            }
        }
        List<Integer> result = null;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gym[i][j] != 'O' && gym[i][j] != 'E') {
                    if (result == null) {
                        result = Arrays.asList(i, j);
                    } else if (cost[i][j] < cost[result.get(0)][result.get(1)]){
                        result.set(0, i);
                        result.set(1, j);
                    }
                }
            }
        }
        return result == null ? Arrays.asList(-1, -1) : result;
    }

    private boolean addCost(int row, int col, char[][] gym, int[][] cost) {
        int M = gym.length;
        int N = gym[0].length;
        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];
        int level = 1;
        queue.offer(new Cell(row, col));
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell cur = queue.poll();
                for (int[] dir : DIRS) {
                    int nextRow = dir[0] + cur.row;
                    int nextCol = dir[1] + cur.col;
                    if (nextRow >= 0 && nextRow < M && nextCol >= 0 && nextCol < N && !visited[nextRow][nextCol] && gym[nextRow][nextCol] != 'O') {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new Cell(nextRow, nextCol));
                        cost[nextRow][nextCol] += level;
                    }
                }
            }
            level++;
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gym[i][j] == 'E' && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
