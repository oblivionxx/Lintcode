import java.util.ArrayList;

/*
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return anyone)
Example
Give [-3, 1, 3, -3, 4], return [1,4].

Tags 
Array Subarray
 */
public class Lint402_Continuous_Subarray_Sum {
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return res;
        }
        int sum = A[0];
        int max = sum;
        int start = 0;
        res.add(0); 
        res.add(0);
        for (int i = 0; i < A.length; i++) {
            if (sum > max) {
                res.set(0, start); 
                res.set(1, i-1);    //here the index comes from last loop, so is (i-1)
                max = sum;
            }
            if (sum < 0) {
                start = i;
                sum = 0;
            }
            sum += A[i];
        }
        if (sum > max) {
            res.set(0, start);
            res.set(1, A.length-1);
        }
        return res;
    }
}
