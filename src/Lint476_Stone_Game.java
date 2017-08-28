

/*
 * Question

There is a stone game.At the beginning of the game the player picks n piles of stones in a line.
The goal is to merge the stones in one pile observing the following rules:
At each step of the game, the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.
Example
For [4, 1, 1, 4], in the best solution, the total score is 18:
1. Merge second and third piles => [4, 2, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Other two examples:
[1, 1, 1, 1] return 8 [4, 4, 5, 9] return 43
Tags

Dynamic Programming
 */
public class Lint476_Stone_Game {
    // similar to burst ballon
    // 死胡同:容易想到的一个思路从小往大，枚举第一次合并是在哪?
    // 应用记忆化搜索的思路，从大到小，先考虑最后的0 ~ n-1合并的总花费。
    // DP四要素
    // State:
    // dp[i][j]表示把第i到第j个石子合并到一起的最小花费
    // Function:
    // 预处理sum[i,j]表示i到j所有石子价值和. dp[i][j]=合并i－j的代价为合并左边部分的代价＋合并右边部分的代价＋合并左右部分的代价（即i－j所有元素的总和）
    // dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}.找到使dp[i][j]最小的k
    // Intialize:
    // for each i
    // dp[i][i] = 0
    // Answer:
    // dp[0][n-1]
    // 区间型DP，利用二维数组下标表示下标范围。 需要注意的是对状态转移方程的理解，也就是对每一种分割方式进行遍历
    /**
     * @param A
     *            an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
	// Write your code here
	// DP
	if (A == null || A.length == 0) {
	    return 0;
	}

	int n = A.length;
	int[][] sum = new int[n][n];
	for (int i = 0; i < n; i++) {
	    sum[i][i] = A[i];
	    for (int j = i + 1; j < n; j++) {
		sum[i][j] = sum[i][j - 1] + A[j];
	    }
	}

	int[][] dp = new int[n][n];
	for (int i = 0; i < n; i++) {
	    dp[i][i] = 0;
	}

	for (int len = 2; len <= n; len++) {
	    for (int i = 0; i + len - 1 < n; i++) {
		int j = i + len - 1;
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
		    min = Math.min(min, dp[i][k] + dp[k + 1][j]);
		}
		dp[i][j] = min + sum[i][j];
	    }
	}

	return dp[0][n - 1];
    }
    
    // Memorization Search
    public int stoneGame2(int[] A) {
        // Write your code here
        // Memorized search
        if(A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                dp[i][j] = -1;
            }
        }

        sum[0] = 0;
        for(int i = 0; i < n; i++){
            dp[i][i] = 0;
            sum[i + 1] = sum[i] + A[i];
        }

        return search(0, n - 1, sum, dp);
    }

    private int search(int start, int end, int[] sum, int[][] dp){
        if(dp[start][end] >= 0){
            return dp[start][end];
        }

        int min = Integer.MAX_VALUE;
        for(int k = start; k < end; k++){
            int left = search(start, k, sum, dp);
            int right = search(k + 1, end, sum, dp);
            int now = sum[end + 1] - sum[start];
            min = Math.min(min, left + right + now);
        }
        dp[start][end] = min;
        return dp[start][end];
    }
}
