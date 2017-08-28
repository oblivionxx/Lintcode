

/*
 * Lint_Longest_Repeating_Subsequence
Given a string, find length of the longest repeating subseequence such that the two subsequence don’t have same string character at same position, 
i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
Input: str = "abc"
Output: 0
There is no repeating subsequence

Input: str = "aab"
Output: 1
The two subssequence are 'a'(first) and 'a'(second). 
Note that 'b' cannot be considered as part of subsequence 
as it would be at same index in both.

Input: str = "aabb"
Output: 2

Input: str = "axxxy"
Output: 2
 */
public class Lint_Longest_Repeating_Subsequence {
    // Function to find the longest repeating subsequence
    //The length of the largest subsequence that repeats itself is : 2
    public static int findLongestRepeatingSubSeq(String str) {
	int n = str.length();

	// Create and initialize DP table
	int[][] dp = new int[n + 1][n + 1];

	// Fill dp table (similar to LCS loops)
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= n; j++) {
		// If characters match and indexes are not same
		if (str.charAt(i - 1) == str.charAt(j - 1) && i != j)
		    dp[i][j] = 1 + dp[i - 1][j - 1];

		// If characters do not match
		else
		    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
	    }
	}
	return dp[n][n];
    }
}
