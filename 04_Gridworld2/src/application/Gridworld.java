 

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gridworld {
	private static Gridworld m_instance;
	private int size;
	private Rectangle rec; // grid
	// 16 by 12 grid,
	private double [][] grid = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	private Gridworld() {
		size = 60;
	}

	public static Gridworld getInstance() {
		if (m_instance == null) {
			m_instance = new Gridworld();
		}
		return m_instance;
	}

	
	public Rectangle draw(int r, int c) {
		rec = new Rectangle(c * size, r * size, size, size);
		if (grid[r][c] == 1) {
			rec.setFill(Color.rgb(177, 221, 224));
		} else if (grid[r][c] == 0) {
			rec.setFill(Color.TRANSPARENT);
		} else if (grid[r][c] == 2) {
			rec.setFill(Color.GOLD);
		}

		return rec;
	}

	public Rectangle drawMenuBar() {
		Rectangle r = new Rectangle(0, 660, 960, 210);
		r.setFill(Color.rgb(177, 221, 224));
		return r;
	}

	public double[][] getGrid() {
		return grid;
	}

	public int getGridLength() {
		return grid.length;
	}

	public int getGridWidth(int r) {
		return grid[r].length;
	}

}
