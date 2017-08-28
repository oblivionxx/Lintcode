
import common.ListNode;

/*
 * The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

size=3, capacity=4

[null, 21, 14, null]
       ↓    ↓
       9   null
       ↓
      null
The hash function is:

int hashcode(int key, int capacity) {
    return key % capacity;
}
here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

rehashing this hash table, double the capacity, you will get:

size=3, capacity=8

index:   0    1    2    3     4    5    6   7
hash : [null, 9, null, null, null, 21, 14, null]
Given the original hash table, return the new hash table after rehashing .

 Notice

For negative integer in hash table, the position can be calculated as follow:

C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
Python: you can directly use -1 % 3, you will get 2 automatically.

Example
Given [null, 21->9->null, 14->null, null],

return [null, 9->null, null, null, null, 21->null, 14->null, null]

Tags 
LintCode Copyright Hash Table
 */
public class Lint129_Rehashing {
    /**
     * @param hashTable:
     *            A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
	// write your code here. rehash to double size
	if (hashTable == null || hashTable.length == 0) {
	    return hashTable;
	}
	int capacity = hashTable.length;
	int newCapacity = 2 * capacity;
	ListNode[] newHashTable = new ListNode[newCapacity];
	for (int i = 0; i < capacity; i++) {
	    ListNode ln = hashTable[i];
	    while (ln != null) {
		int code = hashcode(ln.val, newCapacity);
		insertToHashTable(newHashTable, code, ln.val);
		ln = ln.next;
	    }
	}
	return newHashTable;
    }

    public int hashcode(int key, int capacity) {
	int hash;
	if (key < 0) {
	    hash = (key % capacity + capacity) % capacity;
	} else {
	    hash = key % capacity;
	}
	return hash;
    }

    // 所得到的新的HashTable中，可能依然存在碰撞，所以仍然需要在对应hashcode位置的ListNode tail上插入新的ListNode
    private void insertToHashTable(ListNode[] hashTable, int code, int value) {
	if (code < hashTable.length) {
	    ListNode ln = hashTable[code];
	    if (ln == null) {
		hashTable[code] = ln = new ListNode(value);
	    } else {
		while (ln.next != null) {
		    ln = ln.next;
		}
		ln.next = new ListNode(value);
	    }
	}
    }
}
