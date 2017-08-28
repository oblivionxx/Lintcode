import java.util.*;

/*
 * Throw n dices, the sum of the dices' faces is S. Given n, find the all possible value of S along with its probability.

Notice
You do not care about the accuracy of the result, we will help you to output results.

Example
Given n = 1, return [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]].

Tags 
Dynamic Programming Mathematics Probability
 */
public class Lint020_Dice_Sum {
    // dp[i][j]表示i个骰子一共得到j点的概率。要得到dp[i][j]可以考虑若最后一个筛子的点数为k（1~6），则前i－1个筛子一共得到的点数为j-k（因为i-1个筛子至少得到i-1点，所以j-k >= i - 1 => k <= j - i + 1）。所以只要把最后一个筛子为k的各种情况加起来最后再除以6即可（每多一个筛子概率要多除以一个6）。
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
	// Write your code here
	// Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
	// to create the pair
	// dp[i][j]保存投掷i次得到和为j的概率
	List<Map.Entry<Integer, Double>> res = new ArrayList<Map.Entry<Integer, Double>>();
	double[][] dp = new double[n + 1][6 * n + 1];
	for (int i = 1; i <= 6; i++) {
	    dp[1][i] = 1.0 / 6.0;
	}
	for (int i = 2; i <= n; i++) {
	    for (int j = i; j <= 6 * i; j++) {
		for (int k = 1; k <= 6; k++) {
		    if (j > k) {
			dp[i][j] += dp[i - 1][j - k];
		    }
		}
		dp[i][j] /= 6.0; // if select 2. dp[2][j] += dp[1][j-k] since k is a selection, so C_6^1. if select 3. will have C_36^1 for selecting 1st dice and C_6^1 for 2nd dice. so always /6
	    }
	}

	for (int i = n; i <= 6 * n; i++) {
	    res.add(new AbstractMap.SimpleEntry<Integer, Double>(i, dp[n][i]));
	}
	return res;
    }

    // similar float sumPossibility(int dice, int target)，就是投dice个骰子，求最后和为target的概率。
    // public static float sumPossibility(int n, int target) {
    // if (n <= 0 || target <= 0) {
    // return 0;
    // }

    // int total = (int) Math.pow(6, n);

    // int[][] dp = new int[n + 1][target + 1];
    // dp[0][0] = 1;

    // for (int i = 1; i <= n; i++) {
    // for (int j = 1; j <= target; j++) {
    // for (int k = 1; k <= 6; k++) {
    // if (j >= k) {
    // dp[i][j] += dp[i - 1][j - k];
    // }
    // }
    // }
    // }

    // return (float) dp[n][target] / total;
    // }
}
