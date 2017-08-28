/*
 * Given two strings, find the longest common substring.
Return the length of it.

 Notice
The characters in substring should occur continuously in original string. This is different with subsequence.

Example
Given A = "ABCD", B = "CBCE", return 2.

Challenge 
O(n x m) time and memory.

Tags 
LintCode Copyright String
 */
public class Lint079_Longest_Common_Substring {
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        // state: dp[i][j] is the length of the longest lcs ended with A[i - 1] & B[j - 1] in A[0..i-1] & B[0..j-1]
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    max = Math.max(max, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }  
        
        return max;
    }
}
