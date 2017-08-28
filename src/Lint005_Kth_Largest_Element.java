
/*
 * Find K-th largest element in an array.

 Notice

You can swap elements in the array


Example
In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Challenge 
O(n) time, O(1) extra memory.

Tags 
Sort Quick Sort
 */
public class Lint005_Kth_Largest_Element {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here. O(n) quick select
        return findK(nums, 0, nums.length-1, k);
    }
    
    public int findK(int[] nums, int left, int right, int k){
        if(left>=right) return nums[left];
        int m = partition(nums, left, right);
        if(m-left==k-1) return nums[m];
        if(m-left<k-1) return findK(nums, m+1, right, k-m+left-1);
        else 
            return findK(nums, left, m-1, k);
    }
    
    public int partition(int[] nums, int low, int high){
        int i = low-1, pivot = nums[high];
        for(int j=low;j<high;j++){
            if(nums[j]>=pivot){
                i++;
                swap(nums, i, j);
            }
        }
        
        swap(nums, i+1, high);
        return i+1;                 //place pivot at the correct place i+1
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
