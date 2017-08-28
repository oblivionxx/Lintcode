

import java.util.*;

import common.Interval;

/*
 * Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice

If landing and flying happens at the same time, we consider landing should happen at first.


Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3

Tags 
LintCode Copyright Array Interval
Same as meeting point, sort by start
 */
public class Lint391_Number_Of_Airplanes {
    public int countOfAirplanes(List<Interval> airplanes) { 
        // write your code here
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Comparator<Interval> cmp = new Comparator<Interval>(){
            public int compare(Interval p1, Interval p2){
                return p1.start!=p2.start?p1.start-p2.start:p1.end-p2.end;
            }
        };
        Collections.sort(airplanes, cmp);
        int count = 0;
        for(Interval plane:airplanes){
            if(q.size()==0){
                q.add(plane.end);
                count++;
                continue;
            }
            if(plane.start>=q.peek()){
                q.poll();
                q.offer(plane.end);
            }else{
                q.offer(plane.end);
                count++;
            }
        }
        return count;
    }
}
