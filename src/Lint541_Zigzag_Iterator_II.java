import java.util.*;

/*
 * Follow up Zigzag Iterator: What if you are given k 1d vectors? How well can your code be extended to such cases? The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
Example
Given k = 3 1d vectors:
[1,2,3]
[4,5,6,7]
[8,9]
Return [1,4,8,2,5,9,3,6,7].
 */
public class Lint541_Zigzag_Iterator_II {
    // 和I类似，只是用一个list来存k个iterator（如果iterator中没有元素则不用加入）。用一个count%list_size来确定应该用哪个iterator。若其中一个iterator里面的数已经被取完（即该iterator的hasNext()为false），则将其从list中移除，并将count设置为count%new list_size（如果new list_size
    // != 0）。
    private ArrayList<Iterator<Integer>> vec;
    private int count;

    public Lint541_Zigzag_Iterator_II(ArrayList<ArrayList<Integer>> vecs) {
        // initialize your data structure here.
        vec = new ArrayList<Iterator<Integer>>();
        for(ArrayList<Integer> list : vecs){
            if(list.size() > 0){
                vec.add(list.iterator());
            }
        }
        count = 0;
    }

    public int next() {
	// Write your code here
	int res = vec.get(count).next();
	if (vec.get(count).hasNext()) {
	    count = (count + 1) % vec.size();
	} else {
	    vec.remove(count);
	    if (vec.size() > 0) {
		count %= vec.size();
	    }
	}
	return res;
    }

    public boolean hasNext() {
	// Write your code here
	return vec.size() > 0;
    }
}
