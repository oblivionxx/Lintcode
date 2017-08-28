

import java.util.HashMap;

/*
 * Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller.
We will give you a compare function to compare nut with bolt.

Example
Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].

Your code should find the matching bolts and nuts.
one of the possible return: nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
we will tell you the match compare function. If we give you another compare function.

the possible return is the following:
nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].

So you must use the compare function that we give to do the sorting.
The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.

Other way of asking this problem is, given a box with locks and keys where one lock can be opened by one key in the box. We need to match the pair.
Tags 
Sort Quick Sort

螺帽螺母问题脱胎于排序问题，这里的特别之处在于需要通过两个array进行对应的排序。这就需要利用一个array中的元素对另一个array进行partition，并反过来重复这一个过程，最终让两个array都满足comparator所定义的相同顺序。
这里要注意的是partition的变式，因为pivot并非来源当前array，只能通过一方元素作为基准，对另一个array进行partition。
核心在于：首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts 进行 partition 操作。
 */
public class Lint399_Nuts_And_Bolts {
    // Brute force Way: Start with the first bolt and compare it with each nut until we find a match. In the worst case we require n comparisons. Doing this for all bolts gives us O(n^2) complexity.
    //
    // Quick Sort Way: We can use quick sort technique to solve this. This algorithm first performs a partition by picking last element of bolts array as pivot, rearrange the array of nuts and returns
    // the partition index ‘i’ such that all nuts smaller than nuts[i] are on the left side and all nuts greater than nuts[i] are on the right side. Next using the nuts[i] we can partition the array
    // of bolts. Partitioning operations can easily be implemented in O(n). This operation also makes nuts and bolts array nicely partitioned. Now we apply this partitioning recursively on the left
    // and right sub-array of nuts and bolts.
    // As we apply partitioning on nuts and bolts both so the total time complexity will be Θ(2*nlogn) = Θ(nlogn) on average.
    // Here for the sake of simplicity we have chosen last element always as pivot. We can do randomized quick sort too.

    // Method which works just like quick sort
    public class NBComparator {
	public int cmp(String a, String b){			
	    return 1;			//?
	}
    }
    
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        if(nuts == null || nuts.length == 0 || bolts == null || bolts.length == 0 || nuts.length != bolts.length){
            return;
        }
        quickSort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void quickSort(String[] nuts, String[] bolts, NBComparator compare, int left, int right){
        if(left >= right){
            return;
        }

        int index = sortNuts(nuts, bolts[left], compare, left, right);
        sortBolts(bolts, nuts[index], compare, left, right);

        quickSort(nuts, bolts, compare, left, index - 1);
        quickSort(nuts, bolts, compare, index + 1, right);
    }

    private int sortNuts(String[] nuts, String pivot, NBComparator compare, int start, int end) {
        int i = start-1, j = end+1;
        for (int index = start; index < j; index++) {
            if (compare.cmp(nuts[index], pivot) == -1) {
                swap(nuts, ++i, index);
            } else if (compare.cmp(nuts[index], pivot) == 1) {
                swap(nuts, --j, index--);
            }
        }
        return j-1;
    }
    private int sortBolts(String[] bolts, String pivot, NBComparator compare, int start, int end) {
        int i = start-1, j = end+1;
        for (int index = start; index < j; index++) {
            if (compare.cmp(pivot, bolts[index]) == -1) {
                swap(bolts, --j, index--);
            } else if (compare.cmp(pivot, bolts[index]) == 1) {
                swap(bolts, ++i, index);
            }
        }
        return j-1;
    }

    private void swap(String[] array, int a, int b){
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }


    
    
    // if nuts and bolts are from same char set, like matching "A->A" instead matching "A->AA" then can use hashmap. Travese the nuts array and create a hashmap
    // Traverse the bolts array and search for it in hashmap.
    // If it is found in the hashmap of nuts than this means bolts exist for that nut.
    public boolean nutboltmatch(char nuts[], char bolts[], int n) {
	HashMap<Character, Integer> map = new HashMap<>();
	int count = 0;
	for (int i = 0; i < n; i++)
	    map.put(nuts[i], i);

	// searching for nuts for each bolt in hash map
	for (int i = 0; i < n; i++) {
	    if (map.containsKey(bolts[i])) {
		count++;
	    }
	}
	
	return count==n;
    }
    
    
}
