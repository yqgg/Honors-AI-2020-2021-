import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		try {
			game.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
