import java.util.*;

/*
 * Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.
Example
Given [1,2,4], return 1.
 */
public class Lint197_Permutation_Index {
//    举例【6，5，2】吧。我们找6，5，2是permudation里面的第几个。     
//    正常排序，也就是permutation的第一个，应该是【2，5，6】      
//    如果要从首位，2，变成6，要跨过多少可能性呢？     
//    很简单，就是问：小于6的数字有多少个呢？（2，5）.每个数字变成head，都有各自的一套变化，都有(n-1)!种可能。然后要更新map，因为当前元素在这次已经使用，所以在map里的比当前元素大的元素的value要－1。
//    接下去要看５，２.    
//    ６确定，后面ｐｅｒｍｕｄａｔｉｏｎ可变的情况有可能是【６，５，２】，那还可能是【６，２，５】呢。有几个数字小于５呢? 
	    
    public long permutationIndex(int[] A) {
        // Write your code here
        if(A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        //为了防止操作改变A的顺序，要复制一份来排序
        int[] array = Arrays.copyOf(A, n);
        Arrays.sort(array);
        //将有多少个元素小于当前元素的信息存在map中
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++){
            map.put(array[i], i);
        }

        //index start from 1
        long sum = 1;
        for(int i = 0; i < n; i++){
            sum += map.get(A[i]) * factor(n - 1 - i);
            updateMap(map, A[i]);
        }

        return sum;
    }

    private long factor(int n){
        long now = 1;
        for(int i = 1; i <= n; i++){
            now *= (long) i;
        }
        return now;
    }

    private void updateMap(HashMap<Integer, Integer> map, int a){
        for(int key : map.keySet()){
            if(a < key){
                map.put(key, map.get(key) - 1);
            }
        }
    }
}
