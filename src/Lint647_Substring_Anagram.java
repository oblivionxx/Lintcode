

import java.util.*;

/*
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.

The order of output does not matter.


Example
Given s = "cbaebabacd" p = "abc"

return [0, 6]

The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Hash Table Amazon
 */
public class Lint647_Substring_Anagram {
    /**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        int[] map = new int[256];
        for(int i=0;i<p.length();i++){
            map[p.charAt(i)]++;
        }
        List<Integer> list = new ArrayList<>();
        int left = 0, right = 0, count = p.length();
        while(right<s.length()){
            if(map[s.charAt(right)]>=1){
                count--;
            }
            map[s.charAt(right)]--;
            right++;
            
            if(count==0) list.add(left);
            
            if(right-left==p.length()){
                if(map[s.charAt(left)]>=0)
                    count++;
                map[s.charAt(left)]++;
                left++;
            }
           
        }
        
        return list;
    
    }
}
