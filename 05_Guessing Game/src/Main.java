import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		Scanner kb = new Scanner(System.in);
		String start;

		System.out.println(
				"Welcome to the Guessing Game! Think of an animal and I will try and guess it through a series of questions.");
		System.out.print("Start game? Enter 'y' to continue:  ");
		start = kb.nextLine().toLowerCase();
		while(start.equals("y")) {
			/*
			 * if(start.equals("y")) { do { game.askQuestion(); if(game.getEndOfGame() ==
			 * true) { System.out.println("Want to play again? (y or n):  "); playAgain =
			 * kb.nextLine().toLowerCase(); if(playAgain.equals("y")) { start = "n"; } else
			 * { start = "y"; } } } while (start.equals("y")); } else {
			 * System.out.println("Alright, next time then!"); }
			 */
			if (game.getEndOfGame() == true) {
				System.out.println("Want to play again? (y or n):  ");
				start = kb.nextLine().toLowerCase();
				if(start.equals("y")) {
					game.setEndOfGame(false);
				} else {
					game.setEndOfGame(true);
				}
			} else {
				game.askQuestion();				
			}
		}
		
		if (start.equals("n")) {
			System.out.println("Alright, next time then!");
			kb.close();
		}
	}

}
