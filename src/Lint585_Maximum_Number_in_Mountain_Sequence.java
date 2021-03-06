/*
 * Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.

Example: 
Given nums = [1, 2, 4, 8, 6, 3] return 8 
Given nums = [10, 9, 8, 7], return 10
 */
public class Lint585_Maximum_Number_in_Mountain_Sequence {
    //Binary Search. O(lgn)
    public int mountainSequence(int[] nums) {
	if (nums == null || nums.length == 0) {
	    return -1;
	}
	int start = 0, end = nums.length - 1;
	while (start + 1 < end) {
	    int mid = start + (end - start) / 2;
	    // find out if on downhill or uphill
	    if (nums[mid] < nums[mid + 1])
		start = mid;
	    else
		end = mid;
	}
	if (nums[start] > nums[end])
	    return nums[start];
	else
	    return nums[end];
    }
}
