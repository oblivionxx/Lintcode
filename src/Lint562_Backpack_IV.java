

/* 
 * Ways to fill the bag. Can use multiple times
 * Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
Each item may be chosen unlimited number of times.
Example
Given candidate items [2,3,6,7] and target 7,
A solution set is:
[7]
[2, 2, 3]
return 2
 */
public class Lint562_Backpack_IV {
    // 对于j容量的背包，其新的方法数为前i－1件物品能装满j容量背包的方法数（即不装第i件物品的方法数）＋前i-1件物品能装满j-nums[i]容量的背包的方法数（即装第i件物品的方法数）
    // 这里状态方程只是把III中的max改成了+。所有求总共有多少种方法的题都可以从最大值问题变换max为+得到
    // dp[j] = dp[j] + dp[j - nums[i]] (右边的dp[j]表示上一行中（即i－1件物品）能装满j容量的方法数)
    public int backPackIV(int[] nums, int target) {
	// Write your code here
	int[] dp = new int[target + 1];
	dp[0] = 1;

	for (int i = 0; i < nums.length; i++) {
	    for (int j = nums[i]; j <= target; j++) {
		dp[j] += dp[j - nums[i]];				//前i-1件物品能装满j-nums[i]容量的背包的方法数 and pick ith to be nums[i]
	    }
	}

	return dp[target];
    }
}
