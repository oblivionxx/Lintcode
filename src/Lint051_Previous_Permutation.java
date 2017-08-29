import java.util.*;

/*
 * Given a list of integers, which denote a permutation.

Find the previous permutation in ascending order.

 Notice

The list may contains duplicate integers.

Have you met this question in a real interview? Yes
Example
For [1,3,2,3], the previous permutation is [1,2,3,3]

For [1,2,3,4], the previous permutation is [4,3,2,1]

Tags 
LintCode Copyright Permutation

 */
public class Lint051_Previous_Permutation {
    public List<Integer> previousPermuation(List<Integer> nums) {
	// write your code here
	// 1 5 7 3 4 6
	// Find 3. i~len-1 is increasing sequence. find nums[j]<nums[i-1] when j=i~len-1 --> 6<7. swap j and i-1. reverse i~len-1
	// Similar to next permutation
	int i = nums.size() - 1;
	for (; i > 0; i--) {
	    if (nums.get(i - 1) > nums.get(i))
		break;
	}
	if (i == 0) {
	    reverse(nums, 0, nums.size() - 1);
	} else {
	    int val = nums.get(i - 1);
	    int j = nums.size() - 1;
	    for (; j >= i; j--) {
		if (nums.get(j) < val) {
		    break;
		}
	    }

	    swap(nums, i - 1, j);
	    reverse(nums, i, nums.size() - 1);
	}

	return nums;
    }

    public void reverse(List<Integer> nums, int start, int end) {
	for (int i = start; i <= (start + end) / 2; i++) {
	    swap(nums, i, start + end - i);
	}
    }

    public void swap(List<Integer> nums, int i, int j) {
	int temp = nums.get(i);
	nums.set(i, nums.get(j));
	nums.set(j, temp);
    }
}
