 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import weightedGraph.Edge;
import weightedGraph.Graph;
import weightedGraph.Node;

public class uniformCostSearch {
	private Queue<Node> queue;
	private ArrayList<Node> visited;
	private Node start;
	private String des;
	private Graph graph;

	public uniformCostSearch(String s, String destination, Graph g) {
		queue = new PriorityQueue<>();
		visited = new ArrayList<>();
		for (Node n : g.nodes) {
			if (n.nodeToString().equals(s)) {
				start = n;
			}
		}
		des = destination;
		graph = g;
	}

	// UCS f = g(real cost to a node)
	// https://stackoverflow.com/questions/20635394/uniform-cost-search-implementation
	public void uSearch() {
		start.cost = 0;
		queue.add(start);
		boolean found = false;
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			visited.add(current);
			if (current.nodeToString().equals(des)) {
				found = true;
				// queue.clear();
			}
			for (Edge e : current.edges) {
				Node child = convertToNode(e.vertex);
				double weight = e.weight;
				child.cost += weight;

				if (!visited.contains(child) && !queue.contains(child)) {
					child.parent = current;
					queue.add(child);
					// System.out.println(child.nodeToString() + "\n" + queue);
				} else if ((queue.contains(child)) && (child.cost > current.cost)) {
					child.parent = current;
					queue.remove(child);
					queue.add(child);
				}
			}
		}
	}

	public List<Node> printPath(String s) {
		Node target = convertToNode(s);
		List<Node> path = new ArrayList<Node>();
		for (Node node = target; node != null; node = node.parent) {
			path.add(node);
		}
		Collections.reverse(path);
		return path;
	}

	public Node convertToNode(String s) {
		for (Node n : graph.nodes) {
			if (n.nodeToString().equals(s)) {
				return n;
			}
		}
		return null;
	}
}
