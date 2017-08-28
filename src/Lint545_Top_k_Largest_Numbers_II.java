

import java.util.*;

/*
 * Implement a data structure, provide two interfaces:
add(number). Add a new number in the data structure.
topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure.

Heap
 */
public class Lint545_Top_k_Largest_Numbers_II {
    private PriorityQueue<Integer> minheap;
    private int maxSize;

    public Lint545_Top_k_Largest_Numbers_II(int k) {
        minheap = new PriorityQueue<Integer>();
        maxSize = k;
    }

    public void add(int num) {
        if (minheap.size() < maxSize) {
            minheap.offer(num);
        } else {
            if (num > minheap.peek()) {
                minheap.poll();
                minheap.offer(num);
            }
        }

    }

    public List<Integer> topk() {
        Iterator<Integer> iter = minheap.iterator();
        List<Integer> result = new ArrayList<Integer>();
        while (iter.hasNext()) {
            result.add((Integer) iter.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
}
