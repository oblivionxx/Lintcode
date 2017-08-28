
/*
 * 给定一个长字符串s和一堆短字符串，要求从长字符串中删掉所有的短字符串使得最后剩下的字符串最短。比如，当s = “ccdaabcdbb”，短字符串为["ab", "cd"]时，最后剩下的最短字符串长度为2。删除顺序为：ccdaabcdbb -> ccdacdbb -> cabb -> cb。
这里最关键的问题就是删除短字符串的顺序会影响到最终结果，因此这就变成了一道搜索问题：我们每次删掉一个短字符串，把剩下的字符串当做一个子问题继续进行搜索，在搜索过程中记录所得到的最短字符串长度即可。同时为了避免重复搜索，我们用一个哈希表来记录已经搜索过的字符串。

 */
import java.util.*;

public class Lint_Remove_Substrings {
    public int minLength(String s, Set<String> dict) {
        // Write your code here
        Queue<String> que = new LinkedList<String>();
        Set<String> hash = new HashSet<String>();
    
        int min = s.length();
        que.offer(s);
        hash.add(s);

        while (!que.isEmpty()) {
            s = que.poll();
            for (String sub : dict) {
                int found = s.indexOf(sub);
                while (found != -1) {
                    String new_s = s.substring(0, found) +
                        s.substring(found + sub.length(), s.length());
                    if (!hash.contains(new_s)) {
                        if (new_s.length() < min)
                            min = new_s.length();
                        que.offer(new_s);
                        hash.add(new_s);
                    }
                    found = s.indexOf(sub, found + 1);
                }
            }
        }
        return min;
    }
}
