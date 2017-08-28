/*
 * Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.


Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
For "ABCD" and "EACB", the LCS is "AC", return 2.

Tags 
LintCode Copyright Dynamic Programming Longest Common Subsequence
 */
public class Lint077_Longest_Common_Subsequence {
    // 当char i == char j， D[i ][j]=max(D[i - 1][j - 1] + 1, D[i ][j - 1], D[i - 1][j])，即i可以和j匹配，也可以和j－1匹配，反之也一样，三种情况里面选一个最大的。
    // 当char i!= char j,D[i][j]＝ max(D[i ][j - 1], D[i - 1][j])（因为最后一个字符不相同，所以有可能s1的最后一个字符会出现在s2的前部分里，或者s2的最后一个字符会出现在s1的前部分里。

    public int longestCommonSubsequence(String A, String B) {
	// write your code here
	int n = A.length();
	int m = B.length();
	int[][] f = new int[n + 1][m + 1];
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= m; j++) {
		f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
		if (A.charAt(i - 1) == B.charAt(j - 1)) {
		    f[i][j] = Math.max(f[i - 1][j - 1] + 1, f[i][j]);
		}
	    }
	}
	return f[n][m];
    }
}
