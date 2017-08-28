
import java.util.*;

public class Lint048_Majority_Number_III {
    public static int majorityNumber(List<Integer> nums, int k) {
        // write your code here
        // challenge is space limit O(k). Moore voting canceling counts
        // similar idea as in II. put <int, freq> in map. find first k candidate. 
        // if find new num, 1： 如果map里面有值为count= 0，那么删除掉这个元素，加入新元素；2：map里面没有0出现，那么就每个元素的count-- 
        // if num exist, count++
        // loop final map and find the max
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for(;i<nums.size()&&map.size()<k;i++){          //map size<k so can do one more add
            if(!map.containsKey(nums.get(i)))
                map.put(nums.get(i), 1);
            else
                map.put(nums.get(i), map.get(nums.get(i))+1);
        }
        
        //index i should be continued use
        for(;i<nums.size();i++){
            if(map.containsKey(nums.get(i))){
                map.put(nums.get(i), map.get(nums.get(i))+1);
            }else{
                if(map.values().contains(0)){
                    map.put(nums.get(i),1);
                    Integer zeros = 0;
                    for(Integer cur:map.keySet()){
                        if(map.get(cur)==0){
                            zeros = cur; 
                            break;
                        }
                    }
                    map.remove(zeros);
                }else{
                    for(Integer cur:map.keySet()){
                        map.put(cur, map.get(cur)-1);
                    }
                }
            }
        }
        
        Map<Integer, Integer> newMap = new HashMap<Integer, Integer>();  
        int maxCount = 0;  
        int maxNum = 0;  
        for (int j = 0; j < nums.size(); j++) {  
            int cur = nums.get(j);  
            if (map.containsKey(cur)) {  
                newMap.put(cur, newMap.get(cur) == null ? 1 : newMap.get(cur) + 1);  
                if (newMap.get(cur) > maxCount) {  
                    maxCount = newMap.get(cur);  
                    maxNum = cur;  
                }  
            }  
        }  
        return maxNum;  
    }
    //be aware. the main the map is not the maximum. all key in map could be the answer. should reloop the list.

    public static void main(String[] args) {
	List<Integer> list = Arrays.asList(6,2,3,4,5,1,6,6,1,1,6);
	System.out.println(majorityNumber(list,3));
    }
}
