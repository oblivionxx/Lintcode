/*
 * Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Have you met this question in a real interview? Yes
Clarification
What is heap?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge 
O(n) time complexity
 */
public class Lint130_Heapify {
    public void heapify(int[] A) {
	// write your code here
	// 从下往上heapify，如果两个子节点都比母节点小，那么就swap最小的那个。swap之后，我们要重新heapify 被换的节点（曾经的母节点，现在的子节点）
	for (int i = A.length / 2 - 1; i >= 0; i--) { // loop possible parent node
	    siftDown(A, i);
	}
    }

    public void siftDown(int[] A, int i) {
	int left = i * 2 + 1 >= A.length ? Integer.MAX_VALUE : A[2 * i + 1];
	int right = i * 2 + 2 >= A.length ? Integer.MAX_VALUE : A[2 * i + 2];

	if (left < right && left < A[i]) { // swap left and parent
	    A[2 * i + 1] = A[i];
	    A[i] = left;
	    siftDown(A, 2 * i + 1); // redo heapify of the childrens
	} else if (right < A[i]) { // swap right and parent
	    A[2 * i + 2] = A[i];
	    A[i] = right;
	    siftDown(A, 2 * i + 2);
	}
	// if parent is smaller. do need to do anything
    }

    // Siftdown is O(n)
    // Siftup is O(nlgn)
    // private void siftup(int[] A, int k) {
    // while (k != 0) {
    // int father = (k - 1) / 2;
    // if (A[k] > A[father]) {
    // break;
    // }
    // int temp = A[k];
    // A[k] = A[father];
    // A[father] = temp;

    // k = father;
    // }
    // }

    // public void heapify(int[] A) {
    // for (int i = 0; i < A.length; i++) {
    // siftup(A, i);
    // }
    // }
}
