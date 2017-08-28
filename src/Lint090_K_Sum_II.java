import java.util.*;

/*
 * Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5. Return:

[
  [1,4],
  [2,3]
]
Tags 
LintCode Copyright Depth First Search
 */
public class Lint090_K_Sum_II {
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
	// write your code here
	// Backtracking
	List<List<Integer>> res = new ArrayList<>();
	helper(res, new ArrayList<>(), A, k, target, 0);
	return res;
    }

    public void helper(List<List<Integer>> res, ArrayList<Integer> elm, int[] A, int k, int target, int idx) {
	if (target < 0)
	    return;
	if (elm.size() == k && target == 0) {
	    res.add(new ArrayList<>(elm));
	    return;
	}

	for (int i = idx; i < A.length; i++) {
	    elm.add(A[i]);
	    helper(res, elm, A, k, target - A[i], i + 1);
	    elm.remove(elm.size() - 1);
	}
    }

}
