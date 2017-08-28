
/*
There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?


Example
Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.

Array, DP, Game Theory
 */
public class Lint395_Coins_In_A_Line_II {
	/*
     * @param : a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        //dp[i]表示1st player从i到end可取的最大钱数，大小比values数组多出一位，若n为values的长度，那么dp[n]先初始化为0.从后往前推
        //init
        //i = n-1时，dp[n-1]=values[n-1]，
        //i=n-2时，dp[n-2]=values[n-2]+values[n-1] 最大值肯定是两个都拿
        //i=n-3时，dp[n-3]=values[n-3]+values[n-2]
        //此时还剩三个硬币，你若只拿一个，那么就会给对手留两个，当然不行，所以自己要拿两个，只能给对手留一个
        //transition
        // 1)当我们只拿一个硬币values[i]时，对手有两种选择，拿一个硬币values[i+1]，或拿两个硬币values[i+1] + values[i+2]
        // a) 当对手只拿一个硬币values[i+1]时，我们只能从i+2到end之间来取硬币，所以我们能拿到的最大硬币数为dp[i+2]
        // b) 当对手拿两个硬币values[i+1] + values[i+2]时，我们只能从i+3到end之间来取硬币，所以我们能拿到的最大硬币数为dp[i+3]
        // 由于对手的目的是让我们拿较小的硬币，所以我们只能拿dp[i+2]和dp[i+3]中较小的一个，所以对于我们只拿一个硬币的情况，我们能拿到的最大钱数为values[i] + min(dp[i+2], dp[i+3])
        // 2)当我们只拿两个硬币values[i] + values[i + 1]时, 我们能拿到的最大钱数为values[i] + values[i + 1] + min(dp[i+3], dp[i+4])
        //递推式就有了 dp[i] = max(values[i] + min(dp[i+2], dp[i+3]), values[i] + values[i + 1] + min(dp[i+3], dp[i+4]))
        // dp[0] is the max values can get from 0~end
        if (values.length<= 2) return true;
        int n = values.length, sum = 0;
        int[] dp = new int[n+1];
        dp[n - 1] = values[n - 1];
        dp[n - 2] = values[n - 2] + values[n - 1];
        dp[n - 3] = values[n - 3] + values[n - 2];
        for (int i = n - 4; i >= 0; --i) {
            dp[i] = Math.max(values[i] + Math.min(dp[i + 2], dp[i + 3]), values[i] + values[i + 1] + Math.min(dp[i + 3], dp[i + 4]));
        }
        for (int d : values) {
            sum += d;
        }
        return sum - dp[0] < dp[0];
    }
}
