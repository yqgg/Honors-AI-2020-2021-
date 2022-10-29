package application;

 

public class Grid {

	/*
	 * static double[][] grid2 = { { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	 * -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -3, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, {
	 * -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0,
	 * 0, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
	 * { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1 }, {
	 * -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	 * -1 } };
	 */

	static Node[][] grid;

	public Grid(int row, int col) {
		grid = new Node[row][col];
		for (int i = 0; i < row; i++) {
			grid[0][i] = new Node(0, 0, 0, 0, i, -1);
			grid[i][0] = new Node(0, 0, 0, i, 0, -1);
			grid[row - 1][i] = new Node(0, 0, 0, row - 1, i, -1);
			grid[i][col - 1] = new Node(0, 0, 0, i, col - 1, -1);
		}

		for (int r = 1; r < row - 1; r++) {
			for (int c = 1; c < col - 1; c++) {
				grid[r][c] = new Node(0, 0, 0, r, c, 0);
			}
		}

		grid[1][1].setType(-2);// player
		grid[18][18].setType(-3);// goal
	}

	public double getGrid(int r, int c) {
		return grid[r][c].cellType;
	}

	static class Node {
		double cellType;
		int r;
		int c;
		static int goalR;
		static int goalC;
		double hCost;
		double gCost;
		double fValue;

		public Node(double h, double g, double f, int rValue, int cValue, int type) {
			hCost = h;
			gCost = g;
			fValue = f;
			cellType = h;
			r = rValue;
			c = cValue;
			cellType = type;
		}

		public void setCost(double n, int hops) {
			for (int a = 0; a < grid[0].length; a++) {
				for (int b = 0; b < grid[a].length; b++) {
					if (grid[a][b].cellType == -3) {
						goalR = a;
						goalC = b;
					}
				}
			}
			if (cellType == 0) {
				gCost = n * hops;
				hCost = Math.sqrt(((goalC - c) * (goalC - c)) + ((goalR - r) * (goalR - r)));
				fValue = gCost + hCost;
			} else {
				gCost = 0;
				hCost = 0;
				fValue = 0;
			}
		}

		public void setType(int n) {
			cellType = n;
		}

		public double getType() {
			return cellType;
		}

		public static int getGoalC() {
			return goalC;
		} // GET METHODS

		public static int getGoalR() {
			return goalR;
		}

		public int getC() {
			return c;
		}

		public int getR() {
			return r;
		}

		public double getG() {
			return gCost;
		}

		public double getF() {
			return fValue;
		}

		public double getH() {
			return hCost;
		}

	}
}
