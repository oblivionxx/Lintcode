/*
 * Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

 Notice
The subarray should contain at least one number

Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Tags 
LintCode Copyright Dynamic Programming Array Subarray
 */
public class Lint043_Maximum_Subarray_Sum_III {
    // local[i][k]表示前i个元素取k个子数组并且必须包含第i个元素的最大和。
    // global[i][k]表示前i个元素取k个子数组不一定包含第i个元素的最大和。
    // local[i][k]的状态函数：
    // max(global[i-1][k-1], local[i-1][k]) + nums[i-1]
    // 有两种情况，第一种是第i个元素自己组成一个子数组，则要在前i－1个元素中找k－1个子数组，第二种情况是第i个元素属于前一个元素的子数组，因此要在i－1个元素中找k个子数组（并且必须包含第i－1个元素，这样第i个元素才能合并到最后一个子数组中），取两种情况里面大的那个。
    // global[i][k]的状态函数：
    // max(global[i-1][k]，local[i][k])
    public int maxSubArray(int[] nums, int k) {
	// write your code here
	if (nums.length < k) {
	    return 0;
	}

	int len = nums.length;

	// local[i][k]表示前i个元素取k个子数组并且必须包含第i个元素的最大和。
	int[][] localMax = new int[len + 1][k + 1];
	// global[i][k]表示前i个元素取k个子数组不一定包含第i个元素的最大和。
	int[][] globalMax = new int[len + 1][k + 1];

	for (int j = 1; j <= k; j++) {
	    // 前j－1个元素不可能找到不重叠的j个子数组，因此初始化为最小值，以防后面被取到
	    localMax[j - 1][j] = Integer.MIN_VALUE;
	    for (int i = j; i <= len; i++) {
		localMax[i][j] = Math.max(globalMax[i - 1][j - 1], localMax[i - 1][j]) + nums[i - 1];
		if (i == j) {
		    globalMax[i][j] = localMax[i][j];
		} else {
		    globalMax[i][j] = Math.max(globalMax[i - 1][j], localMax[i][j]);
		}
	    }
	}

	return globalMax[len][k];
    }
}
