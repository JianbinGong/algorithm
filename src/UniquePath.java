public class UniquePath {
    public static int uniquePaths(int m, int n) {
        // write your code here
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++){
            for (int j = 1; j < n; j++){
                // dp[1] = dp[0](1) + dp[1](0); = 1
                // dp[2] = d[]
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 6;
        uniquePaths(m, n);
    }
}
