import java.util.List;

/*
 * Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

 Notice
You can assume each number in the array is a positive integer and not greater than 100.

Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.

Tags 
LintCode Copyright Dynamic Programming Backpack
 */
public class Lint091_Minimum_Adjustment_Cost {
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
        // mac[i][j]表示前i个元素满足要求且第i个元素为j的最小cost。
        // 初始化：mac[1][j] = Math.abs(A[0] - j)，根据题意j在1到100之间
        // 状态函数： when abs(j-k)<=target
        // mac[i][j] = min(max[i][j], mac[i-1][k] + abs(A[i-1] - j))
        if(A == null || A.size() == 0){
            return 0;
        }
        int[][] dp = new int[A.size()+1][101];
        for(int i=1;i<=100;i++){
            dp[1][i] = Math.abs(A.get(0)-i);
        }
        
        for(int i=2;i<=A.size();i++){
            for(int j=1;j<=100;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = 1; k <= 100; k++){
                    if(Math.abs(j-k)<=target){
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][k]+Math.abs(A.get(i-1)-j));
                    }
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= 100; i++){
            min = Math.min(min, dp[A.size()][i]);
        }

        return min;
    }
}
