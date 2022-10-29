package application;

 

import java.util.ArrayList;

import application.Grid.Node;

public class UCS {
	private ArrayList<Node> visited;
	private ArrayList<Node> list;
	private Node[][] grid;
	private boolean found;
	private Node n;

	public UCS() {
		visited = new ArrayList<>();
		list = new ArrayList<>();
		grid = Grid.grid;
	}

	public boolean found(int r, int c) {
		if (Grid.grid[r][c].getType() == -3) {
			return found = true;
		}
		return found = false;
	}
	public boolean getFound() {
		return found;
	}

	public void exploreNeighbors(int r, int c, int hops) {
		grid[r - 1][c - 1].setCost(1.4, hops);
		if (grid[r - 1][c - 1].getType() == -1 || grid[r-1][c-1].getType() == -2) {
			list.remove(grid[r - 1][c - 1]);
		} else {
			list.add(grid[r - 1][c - 1]);
		}
		grid[r - 1][c].setCost(1, hops);
		if (grid[r - 1][c].getType() == -1 || grid[r - 1][c].getType() == -2) {
			list.remove(grid[r - 1][c]);
		} else {
			list.add(grid[r - 1][c]);
		}
		grid[r - 1][c + 1].setCost(1.4, hops);
		if (grid[r - 1][c + 1].getType() == -1 || grid[r - 1][c + 1].getType() == -2) {
			list.remove(grid[r - 1][c + 1]);
		} else {
			list.add(grid[r - 1][c + 1]);
		}
		grid[r][c - 1].setCost(1, hops);
		if (grid[r][c - 1].getType() == -1 || grid[r][c - 1].getType() == -2) {
			list.remove(grid[r][c - 1]);
		} else {
			list.add(grid[r][c - 1]);
		}
		grid[r][c + 1].setCost(1, hops);
		if (grid[r][c + 1].getType() == -1 || grid[r][c + 1].getType() == -2) {
			list.remove(grid[r][c + 1]);
		} else {
			list.add(grid[r][c + 1]);
		}
		grid[r + 1][c - 1].setCost(1.4, hops);
		if (grid[r + 1][c - 1].getType() == -1 || grid[r + 1][c - 1].getType() == -2) {
			list.remove(grid[r + 1][c - 1]);
		} else {
			list.add(grid[r + 1][c - 1]);
		}
		grid[r + 1][c].setCost(1, hops);
		if (grid[r + 1][c].getType() == -1 || grid[r + 1][c].getType() == -2) {
			list.remove(grid[r + 1][c]);
		} else {
			list.add(grid[r + 1][c]);
		}
		grid[r + 1][c + 1].setCost(1.4, hops);
		if (grid[r + 1][c + 1].getType() == -1 || grid[r + 1][c + 1].getType() == -2) {
			list.remove(grid[r + 1][c + 1]);
		} else {
			list.add(grid[r + 1][c + 1]);
		}
	}

	public Node uniform() {

		int r = 0;
		int c = 0;
		// find and add player to arrayList of visited nodes
		for (int row = 0; row < Grid.grid[0].length; row++) {
			for (int col = 0; col < Grid.grid[0].length; col++) {
				if (Grid.grid[row][col].getType() == -2) {
					visited.add(Grid.grid[row][col]);
					r = row;
					c = col;
				}
			}
		}
		int numOfHops = 0;
		while (found(r+1, c+1) == false) {
			numOfHops++;
			exploreNeighbors(r, c, numOfHops);
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i).getG() > list.get(i - 1).getG()) {
					r = list.get(i - 1).getR();
					c = list.get(i - 1).getC();
				} else {
					r = list.get(i).getR();
					c = list.get(i).getC();
				}
			}
			visited.add(Grid.grid[r][c]);
			Grid.grid[r][c].setType(-2);
			list.clear();
			System.out.println(r + "," + c);
			return Grid.grid[r][c];
		}
		return Grid.grid[r][c];
	}
	
	public Node getNode() {
		return n;
	}
}
