import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GoL2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private boolean[][] board;
	private boolean[][] newBoard;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private final int SIZE = 25;
	Timer gameLoopTimer;

	public GoL2() {
		frame = new JFrame("Game of Life");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null); // centers frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes close button actually close

		board = new boolean[WIDTH / SIZE][HEIGHT / SIZE];
		newBoard = new boolean[WIDTH / SIZE][HEIGHT / SIZE];

		Grid grid = new Grid();
		frame.add(grid);

		frame.addMouseListener(new CustomMouseListener());

		frame.addKeyListener(new CustomKeyListener());
	}

	class Grid extends Component {

		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics graphics) {
			Graphics2D g = (Graphics2D) graphics;

			g.setColor(Color.BLACK);

			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[r].length; c++) {
					if (board[r][c]) {
						g.fillRect(c * SIZE, r * SIZE, SIZE, SIZE);
					} else {
						g.drawRect(c * SIZE, r * SIZE, SIZE, SIZE);
					}
				}
			}
		}
	}

	class CustomMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			int r = (int) ((e.getY() - 34) / SIZE);
			int c = (int) ((e.getX() - 10) / SIZE);

			board[r][c] = !board[r][c];
			frame.repaint();
		}
	}

	class CustomKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Timer timer = new Timer(150, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateBoard();
						frame.repaint();
					}
				});
				timer.start();
			}
		}
	}

	public int test(int r, int c) {
		try {
			if (board[r][c]==true) {
				return 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
		return 0;
	}

	public int neighborCount(int r, int c) {
		int live = 0;
		live += test(r - 1, c - 1);
		live += test(r - 1, c);
		live += test(r - 1, c + 1);
		live += test(r, c - 1);
		live += test(r, c + 1);
		live += test(r + 1, c - 1);
		live += test(r + 1, c);
		live += test(r + 1, c + 1);
		return live;
	}

	private void updateBoard() {
		boolean[][] newBoard = new boolean[WIDTH/SIZE][HEIGHT/SIZE];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				newBoard[r][c] = board[r][c];
					int n = neighborCount(r, c);
					if(n==3) {
						newBoard[r][c] = true;
					} else if(n==2) {
						newBoard[r][c] = board[r][c];
					} else if(n<2 || n>3) {
						newBoard[r][c] = false;
					} 
			}
		}
		
		for(int r = 0; r<board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				board[r][c] = newBoard[r][c];
			}
		}
	}
}
