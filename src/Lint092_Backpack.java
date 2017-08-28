
/*
 * MaxWeight/MaxSize
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice
You can not divide any item into small pieces.

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
You function should return the max size we can fill in the given backpack.

Challenge 
O(n x m) time and O(m) memory.
O(n x m) memory is also acceptable if you do not know how to optimize memory.

Tags 
LintCode Copyright Dynamic Programming Backpack
 */
public class Lint092_Backpack {
    //Super Good Summary:  http://love-oriented.com/pack/
    
    public int backPack(int m, int[] A) {
	// write your code here
	int[][] dp = new int[A.length + 1][m + 1];
	// dp[i+1][j]为前 i 个物品中选出重量不超过j时总价值的最大值
	for (int i = 0; i < A.length; i++) {
	    for (int j = 0; j <= m; j++) {
		if (j >= A[i])
		    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - A[i]] + A[i]);
		else
		    dp[i + 1][j] = dp[i][j];
	    }
	}

	return dp[A.length][m];
    }

    // Optimize 1d space O(m). from backward
    // 肯定是有一个主循环i=1..N，每次算出来二维数组f[i][0..V]的所有值。那么，如果只用一个数组f[0..V]，能不能保证第i次循环结束后f[v]中表示的就是我们定义的状态f[i][v]呢？
    // f[i][v]是由f[i-1][v]和f[i-1][v-c[i]]两个子问题递推而来，能否保证在推f[i][v]时（也即在第i次主循环中推f[v]时）能够得到f[i-1][v]和f[i-1][v-c[i]]的值呢？
    // 事实上，这要求在每次主循环中我们以v=V..0的顺序推f[v]，这样才能保证推f[v]时f[v-c[i]]保存的是状态f[i-1][v-c[i]]的值
    public int backPack2(int m, int[] A) {
	// write your code here
	int[] dp = new int[m + 1];
	for (int i = 0; i < A.length; i++) {
	    for (int j = m; j > 0; j--) {
		if (j >= A[i])
		    dp[j] = Math.max(dp[j], dp[j - A[i]] + A[i]);
	    }
	}

	return dp[m];
    }

    // Optimize to 1d
    public int backPackII(int m, int[] A, int V[]) {
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
