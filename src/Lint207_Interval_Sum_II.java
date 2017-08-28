/*
 * Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):

For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value
 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Have you met this question in a real interview? Yes
Example
Given array A = [1,2,7,8,5].

query(0, 2), return 10.
modify(0, 4), change A[0] from 1 to 4.
query(0, 1), return 6.
modify(2, 1), change A[2] from 7 to 1.
query(2, 4), return 14.
Challenge 
O(logN) time for query and modify.

Tags 
LintCode Copyright Binary Search Segment Tree
 */
public class Lint207_Interval_Sum_II {
    SegmentTreeNode node;
    /**
     * @param A: An integer array
     */
    public Lint207_Interval_Sum_II(int[] A) {
        // write your code here
        this.node = build(A, 0, A.length-1);
        
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        return query(node, start, end);
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        // write your code here
        modify(node, index, value);
    }
    
    public class SegmentTreeNode{
        int start, end;
        long sum;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int start, int end, long sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }
    }
    
    public SegmentTreeNode build(int[] A, int left, int right){
        if(left>right) return null;
        if(left==right) return new SegmentTreeNode(left, right, A[left]);
        
        int mid = (left+right)>>1;
        SegmentTreeNode root = new SegmentTreeNode(left, right, A[left]);
        root.left = build(A, left, mid);
        root.right = build(A, mid+1, right);
        
        root.sum = root.left.sum + root.right.sum;
        return root;
    }
    
    public long query(SegmentTreeNode root, int left, int right){
        if(left==root.start && right==root.end) return root.sum;
        int mid = (root.start+root.end)>>1;
        long leftSum = 0, rightSum = 0;
        if(left <= mid) {
           leftSum = query(root.left, left, Math.min(mid,right));
        }
        if(mid < right) {
           rightSum = query(root.right, Math.max(mid+1,left), right);
        }  
        return leftSum+rightSum;
    }
    
    public void modify(SegmentTreeNode root, int index, int value) {        
        // write your code here
        if(root.start==index & root.end == index) {
            root.sum = value;
            return;
        }
        
        int mid = (root.start+root.end)/2;
        if(index>=root.start && index<=mid){
            modify(root.left, index, value);
        }
        
        if(index<=root.end && index>mid){
            modify(root.right, index, value);
        }
        
        root.sum = root.left.sum + root.right.sum;          //not much difference with modify max
    }
}
