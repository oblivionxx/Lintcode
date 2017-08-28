import java.util.HashMap;

/*
 * Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

Example
Given the permutation [1, 4, 2, 2], return 3.
 */
public class Lint198_Permutation_Index_II {
    // 这道题和Permutation IndexI思想一样，计算每一位上数字是该位上第几个排列，再将每一位结果加和即可。只是这道题有重复元素，有无重复元素最大的区别在于原来的1!, 2!, 3!...等需要除以重复元素个数的阶乘。按照数字从低位到高位进行计算。每遇到一个重复的数字就更新重复元素个数的阶乘的值。
    // 从后往前遍历数组，用一个hashmap来记录重复元素个数。若新来的数不是重复元素，则加入hashmap，否则将重复元素个数＋1，同时更新重复元素个数的阶乘。
    // 比较当前位和其后面位的数，计算当前位是第几大的数count
    // 当前位的index为：2的结果count * 其后面位数的阶乘/重复数个数的阶乘。将当前位计入阶乘，重复1-3计算前一位。
    // 注意：1.题目说index从1开始算。2.要用long来保存index，fact和muitlFact，用int有可能超过范围
    public long permutationIndexII(int[] A) {
	// Write your code here
	if (A == null || A.length == 0) {
	    return 0;
	}

	// 用int可能越界
	long index = 1;// index start from 1
	long fact = 1;
	long multiFact = 1;
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	for (int i = A.length - 1; i >= 0; i--) {
	    if (!map.containsKey(A[i])) {
		map.put(A[i], 1);
	    } else {
		map.put(A[i], map.get(A[i]) + 1);
		multiFact *= map.get(A[i]);
	    }

	    int count = 0;
	    for (int j = i + 1; j < A.length; j++) {
		if (A[i] > A[j]) {
		    count++;
		}
	    }

	    index += count * fact / multiFact;
	    fact *= A.length - i;
	}

	return index;
    }
}
