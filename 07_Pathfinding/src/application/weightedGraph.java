 

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class weightedGraph {
	// https://algorithms.tutorialhorizon.com/weighted-graph-implementation-java/

	class Node implements Comparable <Node> {
		String data;
		double cost;
		ArrayList<Edge> edges;
		public Node parent;
		int x;
		int y;

		public Node(String s, double c, int xCoordinate, int yCoordinate) {
			data = s;
			cost = c;
			edges = new ArrayList<>();
			x = xCoordinate;
			y = yCoordinate;
		}
		
		public double calculateH(int x1, int y1, int x2, int y2) {
			double hCost = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
			return hCost;
		}

		public String nodeToString() {
			return data;
		}

		@Override
		public int compareTo(Node other) {
			if(cost > other.cost) {
				return 1;
			} else if(cost < other.cost) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	class Edge {
		String vertex;
		double weight;

		public Edge(String s, double w) {
			vertex = s;
			weight = w;
		}	
	}

	class Graph {
		ArrayList<Node> nodes;

		public Graph() {
			nodes = new ArrayList<>();
		}

		public void addNode(String s, int x, int y) {
			Node n = new Node(s, 0, x, y);
			nodes.add(n);
			// System.out.println(s);
		}

		public void addEdge(String s, String d, double w) {
			Edge e1 = new Edge(d, w);
			Edge e2 = new Edge(s, w);
			for (Node n : nodes) {
				if (n.nodeToString().equals(s)) {
					n.edges.add(e1);
				}
				if (n.nodeToString().equals(d)) {
					n.edges.add(e2);
				}
			}
		}

		public void print() {
			for (Node n : nodes) {
				System.out.print(n.nodeToString() + "==> ");
				for (Edge e : n.edges) {
					System.out.print(e.toString() + ", ");
				}
				System.out.println();
			}

		}
	}
}
