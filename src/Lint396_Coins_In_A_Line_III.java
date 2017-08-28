

/*
 * Question

There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
Could you please decide the first player will win or lose?
Example
Given array A = [3,2,2], return true.
Given array A = [1,2,4], return true.
Given array A = [1,20,4], return false.
Challenge
Follow Up Question:
If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?
Tags

Dynamic Programming Array Game Theory
 */
public class Lint396_Coins_In_A_Line_III {
    // dp[i][j] = sum[i][j] - min(dp[i+1][j], dp[i][j-1]), Similar to predict winner
    // Follow up
    // 如果N是偶数的时候，把在奇数位置上的coin相加得到OddSum，把在偶数位置上的coin相加EvenSum。如果OddSum>EvenSum，那么从左边开始取，反之从右边。因为你取走一个奇数位置的coin之后，留给对手的两头都是原来的偶数位置的coin

}
