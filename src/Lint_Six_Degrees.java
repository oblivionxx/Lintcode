

import java.util.*;

import common.UndirectedGraphNode;

/*
 * Six Degrees

Question

Six degrees of separation is the theory that everyone and everything is six or fewer steps away, by way of introduction, from any other person in the world, so that a chain of "a friend of a friend" statements can be made to connect any two people in a maximum of six steps.
Given a friendship relations, find the degrees of two people, return -1 if they can not been connected by friends of friends.
Example

Given a graph:
1------2-----4
 \          /
  \        /
   \--3--/
{1,2,3#2,1,4#3,1,4#4,2,3} and s = 1, t = 4 return 2
Given a graph:
1      2-----4
             /
           /
          3
{1#2,4#3,4#4,2,3} and s = 1, t = 4 return -1
 */
public class Lint_Six_Degrees {
    // 人际六度分离问题，本质是在Graph中寻找最短路径，那么利用Breadth-first Search(BFS)，通过计算s到t的路径长度，也就得到了degrees of two people。与最基础的BFS相比，利用hashmap存visited的节点的step，也就可以通过frontier节点得到邻近节点的steps(+1)。
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
	if (s == t) {
	    return 0;
	}

	Map<UndirectedGraphNode, Integer> visited = new HashMap<UndirectedGraphNode, Integer>();
	Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

	queue.offer(s);
	visited.put(s, 0);
	while (!queue.isEmpty()) {
	    UndirectedGraphNode frontier = queue.poll();
	    int size = frontier.neighbors.size();
	    for (int i = 0; i < size; i++) {
		UndirectedGraphNode node = frontier.neighbors.get(i);
		if (visited.containsKey(node)) {
		    continue;
		}
		if (node == t) {
		    return visited.get(frontier) + 1;
		}
		queue.offer(node);
		visited.put(node, visited.get(frontier) + 1);
	    }
	}
	return -1;
    }
}
