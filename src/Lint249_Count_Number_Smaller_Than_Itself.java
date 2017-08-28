import java.util.*;

import common.SegmentTreeNode;

/*
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], return [0,1,2,3,2]
LintCode Copyright Binary Search Segment Tree
 */
public class Lint249_Count_Number_Smaller_Than_Itself {
    public List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        //与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。
        // Trick: 先Query，再modify.   
        // 每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
        // 那么就自然是coutning smaller number before itself.   
        List<Integer> res = new ArrayList<>();
        SegmentTreeNode root = build(0, 10000);
        for(int i=0;i<A.length;i++){
            int count = 0;
            if(A[i]>0)
                count = query(root, 0, A[i]-1);
            res.add(count);
            modify(root, A[i], 1);
        }
        return res;
    }
    

    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start>end) return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        
        if(start!=end){
            int mid = start + (end-start)/2;
            root.left = build(start, mid);
            root.right = build(mid+1, end);
        }
        return root;
    }
    
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start>end || root==null) return 0;
        if(start<=root.start && end>=root.end) return root.count;
        
        int mid = (root.start + root.end)/2;
        int leftCount = 0, rightCount = 0;
        if(start <= mid) {
           leftCount = query(root.left, start, Math.min(mid,end));
        }
        if(mid < end) {
           rightCount = query(root.right, Math.max(mid+1,start), end);
        }  
        
        return leftCount+rightCount;
    }
    
    public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        if(root.start==index & root.end == index) {
            root.count += value;
            return;
        }
        
        int mid = (root.start+root.end)/2;
        if(index>=root.start && index<=mid){
            modify(root.left, index, value);
        }
        
        if(index<=root.end && index>mid){
            modify(root.right, index, value);
        }
        
        root.count = root.left.count + root.right.count;
    }
}
