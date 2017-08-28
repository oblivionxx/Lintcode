package dup;
/*
Write an algorithm which computes the number of trailing zeros in n factorial.

Example
11! = 39916800, so the out should be 2

Challenge
O(log N) time

Tags Expand 
Mathematics
 */
public class Lint002_Trailing_Zeros {
	/*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here
        if(n<0) return -1;
        long count = 0;
        for(long i=5;n/i>=1;i*=5){
            count += n/i;
        }
        
        return count;
    }
}
