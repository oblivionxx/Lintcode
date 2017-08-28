package dup;
/*
 * Count the number of k's between 0 and n. k can be 0 - 9.


Example
if n = 12, k = 1 in

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
we have FIVE 1's (1, 10, 11, 12)

Tags 
Enumeration
 */
public class Lint003_Digit_Counts {
    /*
     * param k : As description.
     * param n : As description.
     * return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        int count = 0;
        for(int i=k;i<=n;i++){              //count start with k or 0
            count += singleCount(k,i);      //count num i has how many digit k
        }
        
        return count;
    }
    
    public int singleCount(int k, int i){
        if(k==0 && i==0) return 1;
        int count = 0;
        int temp = i;
        while(temp>0){
            if(temp==k) count++;
            temp/=10;
        }        
        return count;
    }
}
