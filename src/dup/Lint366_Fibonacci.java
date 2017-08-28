package dup;
/*
Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...


Example
Given 1, return 0

Given 2, return 1

Given 10, return 34

Note
The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Enumeration, Mathematics, Non Recursion
 */
public class Lint366_Fibonacci {
	 /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        // write your code here
        if(n<1) return -1;
        if(n==1) return 0;
        if(n==2) return 1;
        
        int l1 = 0, l2 = 1;
        while(n>2){
            int tmp = l2;
            l2 = l1+l2;
            l1 = tmp;
            n--;
        }
        
        return l2;
    }
}
