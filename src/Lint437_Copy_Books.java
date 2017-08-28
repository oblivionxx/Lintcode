
/*
 * Given n books and the ith book has A[i] pages. You are given k people to copy the n books.
n books list in a row and each person can claim a continous range of the n books. For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )
 */
public class Lint437_Copy_Books {
    // 第一种DP建立一个二维数组(n+1 * k+1)，T[i][j]表示前i本书分配给j个人copy。
    // 初始化T[1][j]=pages[0]，初始化T[i][1]= pages[0] + pages[1] + ... + pages[i-1]
    // 然后从2本书开始到n本书为止，依次计算分配给2～k个人的最小时间。当人数比书大时，有些人不干活也不会影响速度，因此和少一个人情况相同。
    // 对于新加进来的人j，考虑让前j－1个人copy的书的数量h（0～n），则新进来的人相应的copy的数量为n～i本，前者的时间为T[h][j-1]，后者的时间为T[i][1
    // -T[h][1]（即一个人copy从h＋1到i本需要的时间），两者的较大值即为T[i][j]的一个后选项。选择所有后选项中的最小值即为T[i][j]的值。这里可以优化，即我们知道如果前j－1个人copy的书的数量少于j－1必然有人不干活，而所有人都干活的结果一定会更快，所以h的范围可以从j－1～n，因为我们知道h为0～j－1时的结果一定不会是最优的答案。

    public int copyBooks(int[] pages, int k) {
	// write your code here
	if (pages == null || pages.length == 0) {
	    return 0;
	}

	if (k < 1) {
	    return -1;
	}

	int n = pages.length;
	int[][] T = new int[n + 1][k + 1];

	for (int j = 1; j <= k; j++) {
	    T[1][j] = pages[0];
	}

	int sum = 0;
	for (int i = 1; i <= n; i++) {
	    sum += pages[i - 1];
	    T[i][1] = sum;
	}

	for (int i = 2; i <= n; i++) {
	    for (int j = 2; j <= k; j++) {
		if (j > i) {
		    T[i][j] = T[i][j - 1];
		    continue;
		}
		int min = Integer.MAX_VALUE;
		for (int h = j - 1; h <= i; h++) {
		    int temp = Math.max(T[h][j - 1], T[i][1] - T[h][1]); // when adding 1 more person, update the time.
		    min = Math.min(min, temp);
		}
		T[i][j] = min;
	    }
	}

	return T[n][k];
    }

    // Binary Search.
    public int copyBooks2(int[] pages, int k) {
	if (pages.length == 0) {
	    return 0;
	}

	int total = 0;
	int max = pages[0];
	for (int i = 0; i < pages.length; i++) {
	    total += pages[i];
	    if (max < pages[i]) {
		max = pages[i];
	    }
	}

	int start = max;
	int end = total;

	while (start + 1 < end) {
	    int mid = (end - start) / 2 + start;
	    if (countCopiers(pages, mid) > k) {
		start = mid;
	    } else {
		end = mid;
	    }
	}

	if (countCopiers(pages, start) <= k) {
	    return start;
	}

	return end;
    }

    private int countCopiers(int[] pages, int limit) {
	if (pages.length == 0) {
	    return 0;
	}

	int copiers = 1;
	int sum = pages[0]; // limit is always >= pages[0]
	for (int i = 1; i < pages.length; i++) {
	    if (sum + pages[i] > limit) {
		copiers++;
		sum = 0;
	    }
	    sum += pages[i];
	}

	return copiers;
    }

}
