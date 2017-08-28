/*
 * Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.

Tags 
LintCode Copyright Dynamic Programming

 */
public class Lint089_K_Sum {
    public int kSum(int[] A, int k, int target) {
        // write your code here
        // find k-subarray sum is target
        // dp[n+1][k+1][target]: dp[i][j][t] meaning from 0~i-1 number find j-1 number to get sum t
        int[][][] dp = new int[A.length+1][k+1][target+1];
        for (int i = 0; i <= A.length; i++) dp[i][0][0] = 1;
        
        //check A[i-1] 
        //若第i个元素，也就是A[i-1]，大于t, not select. 
        //从前i个元素中取j个元素令j个元素之和为t的所有情况”和第i个元素无关。也就是说dp[i][j][t] = dp[i-1][j][t]
        //若第i个元素，也就是A[i-1]<t can select or not select
        //dp[i][j][t] = dp[i-1][j-1][t-A[i-1]] + dp[i-1][j][t]
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= k && j <= i; j++) {        //j select from 0~i
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = dp[i-1][j][t];
                    if (A[i-1] <= t) dp[i][j][t] += dp[i-1][j-1][t-A[i-1]];
                }
            }
        }
        return dp[A.length][k][target];
    }
}
