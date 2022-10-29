package application;

 

import java.util.ArrayList;

import application.Grid.Node;

public class search {
	private ArrayList<Integer> visited;
	private ArrayList<Node> list;
	private Node[][] grid;

	public search() {
		visited = new ArrayList<>();
		list = new ArrayList<>();
		grid = Grid.grid;
	}

	public boolean found(int r, int c) {
		if (Grid.grid[r][c].cellType == -3) {
			return true;
		}
		return false;
	}

	public void exploreNeighbors(int r, int c, int hops) {
		grid[r - 1][c - 1].setCost(1.4, hops);
		list.add(grid[r - 1][c - 1]);
		grid[r - 1][c].setCost(1, hops);
		list.add(grid[r - 1][c]);
		grid[r - 1][c + 1].setCost(1.4, hops);
		list.add(grid[r - 1][c + 1]);
		grid[r][c - 1].setCost(1, hops);
		list.add(grid[r][c - 1]);
		grid[r][c + 1].setCost(1, hops);
		list.add(grid[r][c + 1]);
		grid[r + 1][c - 1].setCost(1.4, hops);
		list.add(grid[r + 1][c - 1]);
		grid[r + 1][c].setCost(1, hops);
		list.add(grid[r + 1][c]);
		grid[r + 1][c + 1].setCost(1.4, hops);
		list.add(grid[r + 1][c + 1]);
	}

	public void UCS() {
		int r = 0;
		int c = 0;
		for (int row = 0; row < Grid.grid[0].length; row++) {
			for (int col = 0; col < Grid.grid[0].length; col++) {
				if (Grid.grid[row][col].getType() == -2) {
					visited.add(row + col);
					r = row;
					c = col;
				}
			}
		}

		while (found(r, c) == false) {
			//exploreNeighbors(r, c);
			for (int i = 1; i < list.size(); i++) {
				if(list.get(i).getH() == 0) {
					list.remove(i);
				} else if (list.get(i).getG() > list.get(i - 1).getG()) {
					r = list.get(i - 1).getR();
					c = list.get(i - 1).getC();
				} else {
					r = list.get(i).getR();
					c = list.get(i).getC();
				}
			}
			visited.add(r + c);
			Grid.grid[r][c].cellType = -2;
			list.clear();
		}
		if(found(r,c) == true) {
			System.out.print(2);
		} else {
			System.out.print(0);
		}
	}

	public void greedy() {
		int r = 0;
		int c = 0;
		for (int row = 0; row < Grid.grid[0].length; row++) {
			for (int col = 0; col < Grid.grid[0].length; col++) {
				if (Grid.grid[row][col].getType() == -2) {
					visited.add(row + col);
					r = row;
					c = col;
				}
			}
		}
		int numOfHops = 0; 
		while (found(r, c) == false) {
			numOfHops++;
			exploreNeighbors(r, c, numOfHops);
			for (int i = 1; i < list.size(); i++) {
				if(list.get(i).getH() == 0) {
					list.remove(i);
				} else if (list.get(i).getH() > list.get(i - 1).getH()) {
					r = list.get(i - 1).getR();
					c = list.get(i - 1).getC();
				} else {
					r = list.get(i).getR();
					c = list.get(i).getC();
				}
			}
			visited.add(r + c);
			Grid.grid[r][c].cellType = -2;
			list.clear();
		}
	}

	public int aStar() {
		int r = 1;
		int c = 1;
		for (int row = 0; row < Grid.grid[0].length; row++) {
			for (int col = 0; col < Grid.grid[0].length; col++) {
				if (Grid.grid[row][col].getType() == -2) {
					visited.add(row + col);
					r = row;
					c = col;
				}
			}
		}
		int numOfHops = 0;
		while (found(r, c) == false) {
			numOfHops++;
			exploreNeighbors(r, c, numOfHops);
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i).getF() > list.get(i - 1).getF()) {
					r = list.get(i - 1).getR();
					c = list.get(i - 1).getC();
				} else {
					r = list.get(i).getR();
					c = list.get(i).getC();
				}
			}
			visited.add(r + c);
			Grid.grid[r][c].cellType = -2;
			list.clear();
		}
		return 0;
	}
}
