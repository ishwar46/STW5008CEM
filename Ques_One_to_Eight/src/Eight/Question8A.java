package Eight;
public class Question8A {

    public static int getMaxSquareArea(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSquareArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 1;
                    if (i > 0 && j > 0) {
                        dp[i][j] += Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                    }
                    maxSquareArea = Math.max(maxSquareArea, dp[i][j]);
                }
            }
        }
        return maxSquareArea * maxSquareArea;
    }

}
