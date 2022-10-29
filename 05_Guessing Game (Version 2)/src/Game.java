import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private BTNode node;
	private boolean endOfGame;
	// private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;
	File file = new File("BTNodes.txt");
	Scanner kb = new Scanner(System.in);

	public Game() {
		node = new BTNode(null);
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (IOException ex) {

		}
	}

	public void save(BTNode node, BufferedWriter out) {
		out = bufferedWriter;
		try {
			if (node == null) {
				out.write("null");
				out.newLine();
				return;
			}
			out.write(node.data + "\n");
			save(node.left, out);
			save(node.right, out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public BTNode load(BufferedReader in) throws IOException {
		in = bufferedReader;
		String s = in.readLine();
		if (s == null) {
			return null;
		}
		node.data = s;
		node.left = load(in);
		node.right = load(in);
		return node;
	}

	public void askQuestion() {
		String guess;
		String question;
		String response;

		if (node.isEmpty()) {
			askForHelp();
		} else if (node.current.data.contains("?")) {
			// A question with "?" in the String question
			question = node.current.data;
			do {
				System.out.println(question);
				response = kb.nextLine().toLowerCase();
				if (response.equals("n")) {
					if (node.current.right.data.contains("null")) {
						askForHelp();
					} else {
						node.moveToRight();
						askQuestion();
					}
				} else if (response.equals("y")) {
					node.moveToLeft();
					askQuestion();
				}
			} while (response.equals("y") && response.equals("n"));
		} else {
			guess = node.current.data;
			question = "Are you thinking of " + guess + "?";
			do {
				System.out.println(question);
				response = kb.nextLine().toLowerCase();
				if (response.equals("y")) {
					endOfGame = true;
					System.out.println("BOOYAH I guessed right!");
					node.setCurrentAsRoot();
				} else if (response.equals("n")) {
					askForHelp();
				}
			} while (response.equals("n") && response.equals("y"));
		}
	}

	public void askForHelp() {
		String newQuestion;
		String oldGuess;
		String newGuess;

		System.out.print("Aww man... I didn't get it this time. ");
		System.out.println("What animal were you thinking of?  ");
		newGuess = kb.nextLine();
		System.out.println("What question would tell me that you are thinking of " + newGuess + "?");
		newQuestion = kb.nextLine() + "?";
		if (node.isEmpty() == true) {
			node.root = new BTNode(newQuestion);
			node.root.left = new BTNode(newGuess);
			node.root.left.left = new BTNode("null");
			node.root.left.right = new BTNode("null");
			node.root.right = new BTNode("null");
			node.setCurrentAsRoot();
			System.out.println("Okay, thank you! I'll remember that for next time...");
			endOfGame = true;
		} // if the curr is at root and user answers no
		else if (node.current.data == node.root.data) {
			node.current.right = new BTNode(newQuestion);
			node.moveToRight();
			node.current.left = new BTNode(newGuess);
			node.current.left.left = new BTNode("null");
			node.current.left.right = new BTNode("null");
			node.current.right = new BTNode("null");
			node.setCurrentAsRoot();
			System.out.println("Okay, thank you! I'll remember that for next time...");
			endOfGame = true;
		} else {
			if (node.current.data.contains("?")) {
				node.current.right = new BTNode(newQuestion);
				node.moveToRight();
				node.current.left = new BTNode(newGuess);
				node.moveToLeft();
				if (node.isAtEnd() == true) {
					node.current.left = new BTNode("null");
					node.current.right = new BTNode("null");
				}
				node.setCurrentAsRoot();
				System.out.println("Okay, thank you! I'll remember that for next time...");
				endOfGame = true;
			} else {
				oldGuess = node.current.data;
				node.current = new BTNode(newQuestion);
				node.current.right = new BTNode(oldGuess);
				node.current.right.left = new BTNode("null");
				node.current.right.right = new BTNode("null");
				node.current.left = new BTNode(newGuess);
				node.current.left.left = new BTNode("null");
				node.current.left.right = new BTNode("null");
				node.setCurrentAsRoot();
				System.out.println("Okay, thank you! I'll remember that for next time...");
				endOfGame = true;
			}
		}
		save(node.root, bufferedWriter);
	}

	public boolean getEndOfGame() {
		return endOfGame;
	}

	public void setEndOfGame(boolean b) {
		endOfGame = b;
	}

	public void run() throws IOException {
		String start;
		System.out.println(
				"Welcome to the Guessing Game! Think of an animal and I will try and guess it through a series of questions.");
		System.out.print("Start game? Enter 'y' to continue:  ");
		start = kb.nextLine().toLowerCase();
		while (start.equals("y")) {
			try {
				load(bufferedReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (getEndOfGame() == true) {
				System.out.println("Want to play again? (y or n):  ");
				start = kb.nextLine().toLowerCase();
				if (start.equals("y")) {
					System.out.println("\n" + "\n");
					setEndOfGame(false);
				} else {
					setEndOfGame(true);
				}
			} else {
				askQuestion();
			}
		}

		if (start.equals("n")) {
			System.out.println("Alright, next time then!");
			kb.close();
		}
	}

}