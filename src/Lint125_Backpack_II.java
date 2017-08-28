
/*
 * MaxValue
 * Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Challenge 
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags 
LintCode Copyright Dynamic Programming Backpack
 */
public class Lint125_Backpack_II {
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[][] dp = new int[A.length+1][m+1];      //dp[i+1][j] represent the max value with i item and j size
        for(int i=0;i<A.length;i++){
            for(int j=0;j<=m;j++){
                if(j>=A[i])
                    dp[i+1][j] = Math.max(dp[i][j], dp[i][j-A[i]]+V[i]);
                else
                    dp[i+1][j] = dp[i][j];
            }
        }
        
        return dp[A.length][m];
    }
    
    public int backPackII2(int m, int[] A, int V[]) {
        // write your code here
        int[] dp = new int[m+1];      //dp[i+1][j] represent the max value with i item and j size
        
        for(int i=0;i<A.length;i++){
            for(int j=m;j>0;j--){
                if(j>=A[i])
                    dp[j] = Math.max(dp[j], dp[j-A[i]]+V[i]);
               
            }
        }
        
        return dp[m];
    }
}
