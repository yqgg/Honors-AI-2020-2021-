 

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Agent {
	private double[] ar;
	private String direction;
	private Rectangle ag;
	private int r;
	private int c;

	public Agent() {
		ar = new double[8];	
	}

	public void setLocation(int row, int col) {
		r += row;
		c += col;
		ag = new Rectangle(c*60, r*60, 60,60);
		ag.setFill(Color.GOLD);
	}

	public void sense() {
		if(c==15) {
			direction = "End";
		}
	}

	public String decide() {
		Gridworld gw = Gridworld.getInstance();
		double[][] grid = gw.getGrid();
		if (c == 15) {
			for (int i = 0; i < ar.length; i++) {
				ar[i] = 3;
			}
		} else {
			ar[0] = grid[r - 1][c - 1];
			ar[1] = grid[r - 1][c];
			ar[2] = grid[r - 1][c + 1];
			ar[3] = grid[r][c + 1];
			ar[4] = grid[r + 1][c + 1];
			ar[5] = grid[r + 1][c];
			ar[6] = grid[r + 1][c - 1];
			ar[7] = grid[r][c - 1];
		}

		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == 3) {
				direction = "End";
			}
		}
		if (ar[1] == 1 || ar[2] == 1) {
			if (ar[3] == 0 && ar[4] == 0) {
				direction = "East";
			} else if (ar[5] == 0 && ar[6] == 0) {
				direction = "South";
			} else if (ar[7] == 0 && ar[0] == 0) {
				direction = "West";
			}
		} else if (ar[3] == 1 || ar[4] == 1) {
			if (ar[5] == 0 && ar[6] == 0) {
				direction = "South";
			} else if (ar[7] == 0 && ar[0] == 0) {
				direction = "West";
			} else if (ar[1] == 0 && ar[2] == 0) {
				direction = "North";
			}
		} else if (ar[5] == 1 || ar[6] == 1) {
			if (ar[7] == 0 && ar[0] == 0) {
				direction = "West";
			} else if (ar[1] == 0 && ar[2] == 0) {
				direction = "North";
			} else if (ar[3] == 0 && ar[4] == 0) {
				direction = "East";
			}
		} else if (ar[7] == 1 || ar[0] == 1) {
			if (ar[1] == 0 && ar[2] == 0) {
				direction = "North";
			} else if (ar[3] == 0 && ar[4] == 0) {
				direction = "East";
			} else if (ar[5] == 0 && ar[6] == 0) {
				direction = "South";
			}
		}
		return direction;
	}

	public void act() {
		if (direction.equals("North")) {
			r -= 1;
		} else if (direction.equals("East")) {
			c += 1;
		} else if (direction.equals("South")) {
			r += 1;
		} else if (direction.equals("West")) {
			c -= 1;
		} else if (direction.equals("End")) {
			r = 0;
			c = 0;
		}
		ag.setX(c*60);
		ag.setY(r*60);
	}
	
	public Rectangle drawAgent() {		
		return ag;
	}
	
	public int getR() {
		return r;
	}
	public int getC() {
		return c;
	}
}
