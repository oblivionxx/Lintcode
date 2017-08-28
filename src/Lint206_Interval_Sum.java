import java.util.*;

import common.Interval;

/*
 * Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]

Challenge 
O(logN) time for each query

Tags 
LintCode Copyright Binary Search Segment Tree
 */
public class Lint206_Interval_Sum {
    //Time Complexity for tree construction is O(n)
    //Time complexity to query is O(Logn). To query a sum, we process at most four nodes at every level and number of levels is O(Logn).
    //Time complexity of update is also O(Logn). To update a leaf value, we process one node at every level and number of levels is O(Logn).
    //There are total 2n-1 nodes
    
    public List<Long> intervalSum(List<Integer> A, ArrayList<Interval> queries) {
	// write your code here
	List<Long> res = new ArrayList<>();
	SegmentTreeNode root = build(A, 0, A.size() - 1);
	for (Interval interval : queries) {
	    res.add(query(root, interval.start, interval.end));
	}
	return res;
    }

    public class SegmentTreeNode {
	int start, end;
	long sum;
	SegmentTreeNode left;
	SegmentTreeNode right;

	public SegmentTreeNode(int start, int end, long sum) {
	    this.start = start;
	    this.end = end;
	    this.sum = sum;
	    this.left = this.right = null;
	}
    }

    public SegmentTreeNode build(List<Integer> A, int left, int right) {
	if (left > right)
	    return null;
	if (left == right)
	    return new SegmentTreeNode(left, right, A.get(left));

	int mid = (left + right) >> 1;
	SegmentTreeNode root = new SegmentTreeNode(left, right, A.get(left));
	root.left = build(A, left, mid);
	root.right = build(A, mid + 1, right);

	root.sum = root.left.sum + root.right.sum;
	return root;
    }

    public long query(SegmentTreeNode root, int left, int right) {
	if (left == root.start && right == root.end)
	    return root.sum;
	int mid = (root.start + root.end) >> 1;
	long leftSum = 0, rightSum = 0;
	if (left <= mid) {
	    leftSum = query(root.left, left, Math.min(mid, right));
	}
	if (mid < right) {
	    rightSum = query(root.right, Math.max(mid + 1, left), right);
	}
	return leftSum + rightSum;
    }
}
