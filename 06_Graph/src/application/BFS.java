 

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	Queue<GNode> fringe;
	Queue<GNode> visited;
	private boolean found;
	private GNode start;
	private GNode goal;
	private Graph graph;

	public BFS(String startNode, String goalNode, Graph g) {
		fringe = new LinkedList<>();
		visited = new LinkedList<>();
		for(GNode n: g.getNode()) {
			if(n.nodeToString().equals(startNode)) {
				start = n;
			}
		}
		goal = new GNode(goalNode);
		graph = g;
	}

	public boolean isVisited(GNode current) {
		for (GNode n : visited) {
			if (current.equals(n)) {
				return true;
			}
		}
		return false;
	}

	public boolean check() {
		for (GNode n : graph.getNode()) {
			if (n.nodeToString().equals(goal.nodeToString())) {
				return true;
			}
		}
		return false;
	}

	public GNode breadth() {
		if (check() == false) {
			found = false;
			return null;
		} else {
			fringe.add(start);
			while (fringe.size() > 0) {
				GNode current = fringe.poll();
				visited.add(current);
				if (current.nodeToString().equals(goal.nodeToString())) {
					found = true;
					return goal;
				}
				for (GNode n : current.getLinks()) {
					if (isVisited(n) == false) {
						fringe.add(n);
					}
				}
			}
		}
		return null;
	}
	
	public void print() {		
		System.out.println("\nBREADTH FIRST SEARCH: ");
		if(found == true) {
			System.out.print("Visited Nodes ==> [ ");
			for(GNode n: visited) {
				System.out.print(n.nodeToString() + " ");
			}
			System.out.print("]\n");
			System.out.println(goal.nodeToString() + " found.");
		} else {
			System.out.println(goal.nodeToString() + " does not exist in this graph.");			
		}
	}
}
