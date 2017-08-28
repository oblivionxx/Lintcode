import java.util.ArrayList;

/*
 * Given an circular integer array (the next element of the last element is the first element), find a continuous subarray in it, where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number.
If duplicate answers exist, return any of them.
Example
Give [3, 1, -100, -3, 4], return [4,1].
 */
public class Lint403_Continuous_Subarray_Sum_II {
    // 这道题和continues subarray sum I思路一样，不同之处在于可以从成环，因此解法稍有不同。总共分为两种情况讨论：
    // 首先遍历数组找出没有环的情况下最大的子数组之和，方法和I相同。
    // 然后找有环情况下最大子数组之和。做法为，寻找数组中最小和的连续子数组，然后用整个数组之和减去这个最小和的连续子数组剩下的就是该情况下的最大和的子数组。将该情况下得到的最大和与1中得到的最大和相比，取和更大的那种即可。
    // 几个tips：1）在1中遍历时，可以顺便求出整个数组的和。2）2中要考虑一种情况为整个数组全部为负数，这样需要减去的是整个数组，因此返回1中的结果。3）根据2中求的最小数组，最大数组的index因该为end＋1到start－1，但是为了防止出现最小数组包含头尾的情况（即end＋1或start－1越界），index的表示方法为(end + 1) % A.length和(start -
    // 1 + A.length) % A.length。
    public ArrayList<Integer> continuousSubarraySumII(int[] A) {
	// Write your code here
	if (A == null || A.length == 0) {
	    return new ArrayList<Integer>();
	}

	ArrayList<Integer> res = new ArrayList<Integer>();
	res.add(0);
	res.add(0);
	int total = 0;
	int start = 0;
	int end = 0;
	int local = 0;
	int global = Integer.MIN_VALUE;
	// 先找不循环情况下最大子数组
	for (int i = 0; i < A.length; i++) {
	    total += A[i];
	    if (local > 0) {
		local += A[i];
		end = i;
	    } else {
		local = A[i];
		start = end = i;
	    }
	    if (local > global) {
		global = local;
		res.set(0, start);
		res.set(1, end);
	    }
	}

	start = 0;
	end = -1;
	local = 0;
	// 找最小子数组，数组总和减去最小子数组即为又循环情况下最大子数组
	for (int i = 0; i < A.length; i++) {
	    if (local <= 0) {
		local += A[i];
		end = i;
	    } else {
		local = A[i];
		start = end = i;
	    }
	    // 若最小数组为整个数组（即所有元素为负），则返回第一种情况结果
	    if (start == 0 && end == A.length - 1) {
		continue;
	    }
	    // 比较又循环情况和无循环情况大小，取大者
	    if (total - local > global) {
		global = total - local;
		// 为了防止出现最小子数组包含头尾而导致越界
		res.set(0, (end + 1) % A.length);
		res.set(1, (start - 1 + A.length) % A.length);
	    }
	}

	return res;
    }
}
