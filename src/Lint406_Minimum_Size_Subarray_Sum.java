/*
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.

Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

Challenge 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Tags 
Array Two Pointers Facebook
 */
public class Lint406_Minimum_Size_Subarray_Sum {
    public int minimumSize(int[] nums, int s) {
	// write your code here
	if (nums == null || nums.length == 0)
	    return -1;
	int left = 0, right = 0, len = nums.length + 1, sum = 0;
	while (right < nums.length) {
	    while (sum < s && right != nums.length) {
		sum += nums[right++];
	    }
	    while (sum >= s) {
		len = Math.min(len, right - left);
		sum -= nums[left++];
	    }
	}

	return len == nums.length + 1 ? -1 : len;

    }
}
