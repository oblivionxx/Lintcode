

/*
 * There is a stone game.At the beginning of the game the player picks n piles of stones in a circle.
The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.
 
Example
For [1, 4, 4, 1], in the best solution, the total score is 18:
1. Merge second and third piles => [2, 4, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Other two examples:
[1, 1, 1, 1] return 8
[4, 4, 5, 9] return 43
 

Solution.
This is a follow up question of Stone Game I.  
Inaddition to the regular non-circular case A[0], A[1]......A[n- 1], start index must be smaller than end index;
When the n piles of stones are in a circle, start index can be bigger than end index. They are shown as follows.
For n = 5, 
Regular case: A[0], A[1], A[2], A[3], A[4]
circular cases: A[4], A[0], A[1], A[2], A[3]
　　　　　　    	A[1], A[2], A[3], A[4], A[0]
                A[2], A[3], A[4], A[0], A[1]
                A[3], A[4], A[0], A[1], A[2]

Based on the above observation, start index can be any A[i] for i : [0.... n - 1], length of subproblems can be [2.......A.length]. 
 */
public class Lint593_Stone_Game_II {
    // State:
    // dp[start][end] still represents the min cost of merging A[start....end], end can be >= A.length, indicating a circular wrap.
    // Function:
    // end < A.length: dp[start][end] = min{dp[start][k] + dp[k + 1][end] + prefixSum[end + 1] - prefixSum[start]} for all k: [start, end]
    // end >= A.length: dp[start][end % A.length] = min{dp[start][k % A.length] + dp[(k + 1) % A.length][end % A.length] + prefixSum[A.length]
    // - (prefixSum[(A.length + start) % A.length] - prefixSum[(end + 1) % A.length])} for all k: [start, end]
    // The formula in blue is the prefix sum of the circular subproblem of A[start...... end % A.length], we calculate this subtracting the continuous subarray in
    // middle from the total sum of A.
    // Initialization:
    // dp[i][i] = 0;
    // dp[i][j] = Integer.MAX_VALUE for all i != j;
    // prefixSum[i]: the sum of the first ith elements of A;
    //
    // Answer:
    // In Stone Game I, the answer is simply dp[0][A.length - 1];
    // In Stone Game II, there are A.length of subproblems that have length A.length;
    // so the minimum of these n subproblems is the final minimal cost.
    //
    // Runtime: O(n^2) (n - 2 + 1) * n, for each fixed length, we have n different start positions;
    // Space: O(n^2)
    
    //http://blog.csdn.net/txl199106/article/details/40620957   ??
    //Similar question like adding m *sign into a string and check out many possible output
    //http://blog.csdn.net/y990041769/article/details/24194605
    public int stoneGame2(int[] A) {
	if (A == null || A.length <= 1) {
	    return 0;
	}
	int[] prefixSum = new int[A.length + 1];
	prefixSum[0] = 0;
	for (int i = 1; i <= A.length; i++) {
	    prefixSum[i] = prefixSum[i - 1] + A[i - 1];
	}
	int[][] dp = new int[A.length][A.length];
	for (int i = 0; i < A.length; i++) {
	    for (int j = 0; j < A.length; j++) {
		dp[i][j] = Integer.MAX_VALUE;
	    }
	}
	for (int i = 0; i < A.length; i++) {
	    dp[i][i] = 0;
	}
	for (int len = 2; len <= A.length; len++) {
	    for (int start = 0; start < A.length; start++) {
		int end = start + len - 1;
		for (int k = start; k < end; k++) {
		    if (end < A.length) {
			dp[start][end] = Math.min(dp[start][end],
				dp[start][k] + dp[k + 1][end] + prefixSum[end + 1] - prefixSum[start]);
		    } else {
			dp[start][end % A.length] = Math.min(dp[start][end % A.length],
				dp[start][k % A.length] + dp[(k + 1) % A.length][end % A.length] + prefixSum[A.length]
					- (prefixSum[(A.length + start) % A.length] - prefixSum[(end + 1) % A.length]));
		    }
		}
	    }
	}
	int min = Integer.MAX_VALUE;
	for (int i = 0; i < A.length; i++) {
	    min = Math.min(min, dp[i][(A.length - 1 + i) % A.length]);
	}
	return min;
    }
}
