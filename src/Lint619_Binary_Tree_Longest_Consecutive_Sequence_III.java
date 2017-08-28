

import java.util.*;

/*
 * Description
It's follow up problem for Binary Tree Longest Consecutive Sequence II

Given a k-ary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree



Example
An example of test data: k-ary tree 5<6<7<>,5<>,8<>>,4<3<>,5<>,3<>>>, denote the following structure:


     5
   /   \
  6     4
 /|\   /|\
7 5 8 3 5 3

Return 5, // 3-4-5-6-7
 */
public class Lint619_Binary_Tree_Longest_Consecutive_Sequence_III {
    class ResultType {
	int globalMax;
	int maxUp;
	int maxDown;

	public ResultType(int globalMax, int maxUp, int maxDown) {
	    this.globalMax = globalMax;
	    this.maxUp = maxUp;
	    this.maxDown = maxDown;
	}
    }

    public int longestConsecutive3(MultiTreeNode root) {
	// Write your code here

	return helper(root).globalMax;
    }

    public ResultType helper(MultiTreeNode root) {
	if (root == null) {
	    return new ResultType(0, 0, 0);
	}

	int maxUp = 0;
	int maxDown = 0;
	int max = Integer.MIN_VALUE;

	for (MultiTreeNode child : root.children) { // need to check not only left and right, but all children
	    if (child == null) {
		continue;
	    }

	    ResultType childResult = helper(child);
	    if (child.val + 1 == root.val) {
		maxDown = Math.max(maxDown, childResult.maxDown + 1);
	    }

	    if (child.val - 1 == root.val) {
		maxUp = Math.max(maxUp, childResult.maxUp + 1);
	    }

	    max = Math.max(Math.max(max, childResult.globalMax), maxUp + maxDown + 1);
	}

	return new ResultType(max, maxUp, maxDown);
    }

    public class MultiTreeNode {
	int val;
	List<MultiTreeNode> children;

	MultiTreeNode(int x) {
	    val = x;
	}
    }

}
