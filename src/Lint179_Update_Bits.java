/*
 * Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

 Notice
In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Clarification
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2

Challenge 
Minimum number of operations?

Tags 
Bit Manipulation Cracking The Coding Interview
 */
public class Lint179_Update_Bits {
    // 将N的i－j的位置上的元素都变成0其他位不变，然后将M移动i位后和N相加，这样M的所有元素就被放到N的i－j位上了
    // 一个mask其i－j位为0，其他位为1. 当j<31时，将1移动j＋1位后减去将1移动i位后的数（位减法和普通减法类似，0-1时要向前一位借数），这样可以得到i～j全为1其他位为0，然后取反即可得到i～j为0其他位为1.当j=31时，则将1移动i位后减去1移动0位后的数，这样可以得到i～j为0，i之后其他位为1。
    // 将mask & N即可使得N的i-j位为0，其他位保持不变。
    public int updateBits(int n, int m, int i, int j) {
	// write your code here
	int mask = (j < 31 ? (~((1 << (j + 1)) - (1 << i))) : ((1 << i) - 1));
	return (m << i) + (mask & n);
    }
}
