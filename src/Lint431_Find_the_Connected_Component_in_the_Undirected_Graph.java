

import java.util.*;

import common.UndirectedGraphNode;

/*
 * Find the number connected component in the undirected graph. Each node in the graph contains a label and a list of its neighbors. (a connected component (or just component) of an undirected graph is a subgraph in which any two vertices are connected to each other by paths, and which is connected to no additional vertices in the supergraph.)
Example
Given graph:
A------B C
    \ | |
    \ | |
    \ | |
    \  |  |
     D   E
Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}
 */
public class Lint431_Find_the_Connected_Component_in_the_Undirected_Graph {
    /**
     * @param nodes
     *            a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
	if (nodes == null || nodes.size() == 0)
	    return null;

	HashSet<Integer> allNodes = new HashSet<Integer>();
	for (UndirectedGraphNode now : nodes) {
	    allNodes.add(now.label);
	    for (UndirectedGraphNode neighbor : now.neighbors) {
		allNodes.add(neighbor.label);
	    }
	}

	UnionFind uf = new UnionFind(allNodes);
	for (UndirectedGraphNode cur : nodes) {
	    for (UndirectedGraphNode neighbor : cur.neighbors) {
		int fcur = uf.root(cur.label);
		int fneighbor = uf.root(neighbor.label);
		if (fcur != fneighbor) {
		    uf.union(cur.label, neighbor.label);
		}
	    }
	}

	return generateResult(allNodes, uf, nodes.size());
    }

    public List<List<Integer>> generateResult(HashSet<Integer> allNodes, UnionFind uf, int n) {
	List<List<Integer>> ans = new ArrayList<List<Integer>>();
	HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>(); // save fatherNode and all nodes in connected part
	for (int i : allNodes) {
	    int fa = uf.root(i);
	    if (!hashMap.containsKey(fa)) {
		hashMap.put(fa, new ArrayList<Integer>());
	    }
	    List<Integer> now = hashMap.get(fa);
	    now.add(i);
	    hashMap.put(fa, now);
	}

	for (List<Integer> now : hashMap.values()) {
	    Collections.sort(now);
	    ans.add(now);
	}
	return ans;
    }

    class UnionFind {
	HashMap<Integer, Integer> conection = new HashMap<Integer, Integer>(); // if 1 connect with 2 and 3. will have 2-2 => 2-1

	UnionFind(HashSet<Integer> nodes) {
	    for (Integer curNode : nodes) {
		conection.put(curNode, curNode);
	    }
	}

	public int root(int x) {
	    int parent = conection.get(x);
	    while (parent != conection.get(parent)) {
		parent = conection.get(parent);
	    }
	    return parent;
	}

	public int compressed_find(int x) {
	    int parent = conection.get(x);
	    while (parent != conection.get(parent)) {
		parent = conection.get(parent);
	    }
	    int temp = -1;
	    int fa = conection.get(x);
	    while (fa != conection.get(fa)) {
		temp = conection.get(fa);
		conection.put(fa, parent);
		fa = temp;
	    }
	    return parent;
	}

	public void union(int x, int y) {
	    int fa_x = root(x);
	    int fa_y = root(y);
	    if (fa_x != fa_y) {
		conection.put(fa_x, fa_y); // update root
	    }
	}
    }

    // DFS
    public List<List<Integer>> connectedSet2(ArrayList<UndirectedGraphNode> nodes) {
	if (nodes == null || nodes.size() == 0)
	    return null;

	List<List<Integer>> result = new ArrayList<List<Integer>>();
	Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
	for (UndirectedGraphNode node : nodes) {
	    if (visited.contains(node))
		continue;
	    List<Integer> temp = new ArrayList<Integer>();
	    dfs(node, visited, temp);
	    Collections.sort(temp);
	    result.add(temp);
	}

	return result;
    }

    private void dfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited, List<Integer> result) {

	// add node into result
	result.add(node.label);
	visited.add(node);
	// node is not connected, exclude by for iteration
	// if (node.neighbors.size() == 0 ) return;
	for (UndirectedGraphNode neighbor : node.neighbors) {
	    if (visited.contains(neighbor))
		continue;
	    dfs(neighbor, visited, result);
	}
    }

    // BFS
    public List<List<Integer>> connectedSet3(ArrayList<UndirectedGraphNode> nodes) {
	// Write your code here
	Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();

	for (UndirectedGraphNode node : nodes) {
	    visited.put(node, false);
	}

	List<List<Integer>> result = new ArrayList<>();
	for (UndirectedGraphNode node : nodes) {
	    if (visited.get(node) == false) {
		bfs(node, visited, result);
	    }
	}

	return result;
    }

    public void bfs(UndirectedGraphNode node, Map<UndirectedGraphNode, Boolean> visited, List<List<Integer>> result) {
	List<Integer> row = new ArrayList<Integer>();
	Queue<UndirectedGraphNode> queue = new LinkedList<>();
	visited.put(node, true);
	queue.offer(node);
	while (!queue.isEmpty()) {
	    UndirectedGraphNode u = queue.poll();
	    row.add(u.label);
	    for (UndirectedGraphNode v : u.neighbors) {
		if (visited.get(v) == false) {
		    visited.put(v, true);
		    queue.offer(v);
		}
	    }
	}
	Collections.sort(row);
	result.add(row);

    }
}
