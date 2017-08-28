package dup;
import java.util.PriorityQueue;

/*
 * Ugly number is a number that only have factors 2, 3 and 5.

Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

 Notice

Note that 1 is typically treated as an ugly number.


Example
If n=9, return 10.

Challenge 
O(n log n) or O(n) time.

Tags 
Priority Queue LintCode Copyright
 */
public class Lint004_Ugly_Number_II {
    /*
     * @param n: An integer
     * 
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
	// write your code here
	PriorityQueue<Long> q = new PriorityQueue<>();
	q.offer(1L);
	for (long i = 1; i < n; i++) { // pop up n times, may push more
	    long tmp = q.poll();
	    while (!q.isEmpty() && q.peek() == tmp)
		tmp = q.poll(); // i.e we can get 2*3 and 3*2. so two times need to get rid of one.
	    q.add(tmp * 2);
	    q.add(tmp * 3);
	    q.add(tmp * 5);
	}
	return q.poll().intValue();

    }
}
