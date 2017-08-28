/*
 * Given a string str, find the longest repeating non-overlapping substring in it. In other words find 2 identical substrings of maximum length which do not overlap. If there exists more than one such substring return any of them.
Examples:

Input : str = "geeksforgeeks"
Output : geeks

Input : str = "aab"
Output : a

Input : str = "aabaabaaba"
Output : aaba

Input : str = "aaaaaaaaaaa"
Output : aaaaa

Input : str = "banana"
Output : an 
         or na

Naive Solution : The problem can be solved easily by taking all the possible substrings and for all the substrings check it for the remaining(non-overlapping) string if there exists an identical substring. There are O(n2) total substrings and checking them against the remaining string will take O(n) time. So overall time complexity of above solution is O(n3).

Dynamic Programming : This problem can be solved in O(n2) time using Dynamic Programming. The basic idea is to find the longest repeating suffix for all prefixes in the string str.
Length of longest non-repeating substring can be recursively
defined as below.

LCSRe(i, j) stores length of the matching and
            non-overlapping substrings ending 
            with i'th and j'th characters.

If str[i-1] == str[j-1] && (j-i) > LCSRe(i-1, j-1)
     LCSRe(i, j) = LCSRe(i-1, j-1) + 1, 
Else
     LCSRe(i, j) = 0

Where i varies from 1 to n and 
      j varies from i+1 to n
      
 */
public class Lint_Longest_repeating_and_non_overlapping_substring {
    static void printLongetsRepeatingNonOverlappingSubString(String str, int n) {
	int dp[][] = new int[n + 1][n + 1];
	int index = 0, max = 0;
	for (int i = 1; i <= n; i++) {
	    for (int j = i + 1; j <= n; j++) {
		if (str.charAt(i - 1) == str.charAt(j - 1) && dp[i - 1][j - 1] < (j - i)) {
		    dp[i][j] = dp[i - 1][j - 1] + 1;
		    if (max < dp[i][j]) {
			max = dp[i][j];
			index = Integer.max(index, i);
		    }
		} else
		    dp[i][j] = 0;
	    }
	}
	if (max > 0) {
	    for (int i = index - max + 1; i <= index; i++)
		System.out.print(str.charAt(i - 1));
	} else
	    System.out.print(-1);

    }
}
