package dup;
import java.util.*;
/*
 * Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。
An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.

Given 3 buildings：

[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：

[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
 */
public class Lint131_Building_Outline {
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // Similar to skyline problem. but should return the full range of outline instead of the changing point.
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<int[]> height = new ArrayList<>();
        for(int[] h:buildings){
            height.add(new int[]{h[0],-h[2]});
            height.add(new int[]{h[1], h[2]});
        }
        Collections.sort(height, (a, b) -> {
                if(a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
        });
        
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int preH = 0, preIndex = 0;
        for(int[] outline:height){
            if(outline[1]<0) 
                pq.offer(-outline[1]);
            else
                pq.remove(outline[1]);
            int curH = pq.peek();
            if(preH!=curH){
                ArrayList<Integer> elm = new ArrayList<Integer>();
                if(preH!=0){        //otherwise will save the outline for the ground
                    elm.add(preIndex);
                    elm.add(outline[0]);
                    elm.add(preH);
                    res.add(elm);
                }
                preH = curH;
                preIndex = outline[0];
            }
        }
        
        return res;
    }
}
