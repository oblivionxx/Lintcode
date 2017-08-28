/*
 * Given an array with integers.
Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.
 Notice
The subarray should contain at least one number

Example
For [1, 2, -3, 1], return 6.

Challenge 
O(n) time and O(n) space.

Tags 
Greedy Enumeration LintCode Copyright Array Subarray Forward-Backward Traversal
 */
public class Lint045_Maximum_Subarray_Difference {
    // 用到了max subarray的技巧，并且分别从左边和右边扫，对于每一个index， 更新res = Math.max(res, Math.max(maxFromRight[i] – minFromLeft[i-1], maxFromLeft[i] – maxFromRight[i-1]));
    public int maxDiffSubArrays(int[] nums) {
	// write your code here
	if (nums == null || nums.length == 0) {
	    return 0;
	}
	int[] maxFromLeft = new int[nums.length];
	int[] minFromLeft = new int[nums.length];
	int min = nums[0];
	int max = min;
	int localmin = min;
	int localmax = max;
	maxFromLeft[0] = minFromLeft[0] = min;
	for (int i = 1; i < nums.length; i++) {
	    localmin = Math.min(nums[i], localmin + nums[i]);
	    localmax = Math.max(nums[i], localmax + nums[i]);
	    max = Math.max(max, localmax);
	    min = Math.min(min, localmin);
	    maxFromLeft[i] = max;
	    minFromLeft[i] = min;
	}
	// from right
	min = nums[nums.length - 1];
	max = min;
	localmin = min;
	localmax = max;
	int res = Math.max(max - minFromLeft[nums.length - 2], maxFromLeft[nums.length - 2] - min);
	for (int i = nums.length - 2; i > 0; i--) {
	    localmin = Math.min(nums[i], localmin + nums[i]);
	    localmax = Math.max(nums[i], localmax + nums[i]);
	    max = Math.max(max, localmax);
	    min = Math.min(min, localmin);
	    res = Math.max(res, Math.max(max - minFromLeft[i - 1], maxFromLeft[i - 1] - min));
	}
	return res;

    }
}
