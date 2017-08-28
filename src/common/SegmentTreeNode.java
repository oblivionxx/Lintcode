package common;

public class SegmentTreeNode {
    public int start, end, max, count;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
	this.start = start;
	this.end = end;
	this.left = this.right = null;
    }

    public SegmentTreeNode(int start, int end, int max) {
	this.start = start;
	this.end = end;
	this.max = max;
	this.left = this.right = null;
    }

    public SegmentTreeNode(int start, int end, int max, int count) {			//no need max. just put everything together
	this.start = start;
	this.end = end;
	this.count = count;
	this.left = this.right = null;
    }
    
    //could have min/max, count and sum

}
