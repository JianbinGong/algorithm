import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SevenPuzzle {
    static class Board {
        int row = 2;
        int col = 4;
        int[][] board = new int[row][col];
        public Board() {

        }
        public Board(int[] values) {
            for (int i = 0; i < values.length; i++) {
                board[i/col][i%col] = values[i];
            }
        }

        public void swap (int row, int col, int newRow, int newCol) {
            int temp = board[row][col];
            board[row][col] = board[newRow][newCol];
            board[newRow][newCol] = temp;
        }

        public Board clone() {
            Board newBoard = new Board();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    newBoard.board[i][j] = board[i][j];
                }
            }
            return newBoard;
        }

        public int[] findZero() {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 0) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{};
        }

        // Hashcode is important here
        // When we override equals, we need to override hashcode
        @Override
        public int hashCode() {
            int result = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    result = result * 10 + board[i][j];
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) return false;
            Board b = (Board)o;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (b.board[i][j] != board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    private int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int numOfSteps(int[] values) {
        Board start = new Board(new int[]{0, 1, 2,3, 4, 5, 6, 7});
        Queue<Board> queue = new LinkedList<>();
        queue.offer(start);
        Map<Board, Integer> visited = new HashMap<>();
        visited.put(start, 0);
        while (!queue.isEmpty()) {
            Board cur = queue.poll();
            int level = visited.get(cur);
            int[] zeroPosition = cur.findZero();
            for (int[] dir : DIRS) {
                int nextRow = zeroPosition[0] + dir[0];
                int nextCol = zeroPosition[1] + dir[1];
                if (inBoard(nextRow, nextCol)) {
                    cur.swap(zeroPosition[0], zeroPosition[1], nextRow, nextCol);
                    if (!visited.containsKey(cur)) {
                        Board nextBoard = cur.clone();
                        visited.put(nextBoard, level + 1);
                        queue.offer(nextBoard);
                    }
                    cur.swap(zeroPosition[0], zeroPosition[1], nextRow, nextCol);
                }
            }
        }
        return visited.getOrDefault(new Board(values), -1);
    }

    private boolean inBoard(int row, int col) {
        return row >= 0 && row < 2 && col >= 0 && col < 4;
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 1, 2, 3, 5, 0, 6, 7};
        SevenPuzzle testRun = new SevenPuzzle();
        testRun.numOfSteps(test);
    }
}
