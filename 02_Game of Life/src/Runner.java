
/**
 * Write a description of class GameOfLifeRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner
{
    // instance variables - replace the example below with your own
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				GoL2 frame = new GoL2();
				frame.setVisible(true);
			}
		});
	}
}

