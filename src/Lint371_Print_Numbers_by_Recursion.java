import java.util.ArrayList;
import java.util.List;

/*
 * Print numbers from 1 to the largest number with N digits by recursion.

 Notice

It's pretty easy to do recursion like:

recursion(i) {
    if i > largest number:
        return
    results.add(i)
    recursion(i + 1)
}
however this cost a lot of recursion memory as the recursion depth maybe very large. Can you do it in another way to recursive with at most N depth?

Have you met this question in a real interview? Yes
Example
Given N = 1, return [1,2,3,4,5,6,7,8,9].

Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].

Challenge 
Do it in recursion, not for-loop.

Tags 
Recursion
 */
public class Lint371_Print_Numbers_by_Recursion {
    // 在求n-1位数到n位数的时候，先求n-2位数到n-1位数，就如同下面一样：
    public List<Integer> numbersByRecursion(int n) {
	// write your code here
	ArrayList<Integer> res = new ArrayList<>();
	num(n, 0, res);
	return res;
    }

    // ans在这里可以看成前一位的值，在递归过程中会乘以n－1次10，即10^(n-1)
    public void num(int n, int ans, ArrayList<Integer> res) {
	if (n == 0) {
	    if (ans > 0) {
		res.add(ans);
	    }
	    return;
	}

	int i;
	for (i = 0; i <= 9; i++) {
	    num(n - 1, ans * 10 + i, res);
	}

    }
}
