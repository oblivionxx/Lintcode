

/*
 * There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.
Could you please decide the first play will win or lose?


Example
n = 1, return true.
n = 2, return true.
n = 3, return false.
n = 4, return true.
n = 5, return true.

Challenge 
O(n) time and O(1) memory


 */
public class Lint394_Coins_In_A_Line {
    public boolean firstWillWin(int n) {
	// write your code here
	return n % 3 != 0;
    }
    
    public boolean firstWillWin2(int n) {
        // write your code here
        if (n == 0)
            return false;
        else if (n == 1)
            return true;
        else if (n == 2)
            return true;
            
        boolean []dp = new boolean[n+1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        for (int i = 3; i <= n; i++) 
            dp[i] = !dp[i - 1] || !dp[i - 2];
            
        return dp[n];
    }

}
