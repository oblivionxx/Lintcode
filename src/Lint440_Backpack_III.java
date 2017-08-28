

/*
 * Can put multiple. MaxValue
 * Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. What's the maximum value can you put into the backpack?
Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.
 */
public class Lint440_Backpack_III {
    // 和II不同的是，这道题物品可以重复选择，所以内层遍历j的时候从小到大遍历，这样物品可以重复选取。
    // 比如一开始在j的时候取了i，然后随着j的增大，在j'的时候又取了i，而恰好j = j' - A[i]，在这种情况下i就被重复选取。如果从大往小遍历则所有物品只能取一次，所以II中是从大往小遍历。
    public int backPackII2(int m, int[] A, int V[]) {
	// write your code here
	int[] dp = new int[m + 1]; // dp[i+1][j] represent the max value with i item and j size
	for (int i = 0; i < A.length; i++) {
	    for (int j = 0; j <= m; j++) {
		if (j >= A[i])
		    dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);

	    }
	}
	return dp[m];
    }
    
    // 如果要求恰好装满背包，那么在初始化时除了f[0]为0其它f[1..V]均设为-∞，这样就可以保证最终得到的f[N]是一种恰好装满背包的最优解。
    // 如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将f[0..V]全部设为0。
}
