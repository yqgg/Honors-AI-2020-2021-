import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private BinaryTree tree;
	private boolean endOfGame;
	//private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;
	Scanner kb = new Scanner(System.in);
	Main m = new Main();

	public Game() {
		tree = new BinaryTree();
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("BTNodes.txt"));
			bufferedReader = new BufferedReader(new FileReader("BTNodes.txt"));
		} catch (IOException ex) {

		}
	}

	public void save(BTNode current, BufferedWriter out) {
		out = bufferedWriter;
		try {
			if (current == null) {
				out.write("null");
				out.newLine();
				return;
			}
			out.write(current + "\n");
			save(current.left, out);
			save(current.right, out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
	public BTNode load(BufferedReader in) throws IOException {
		in = bufferedReader;
		String s = in.readLine();
		if (s.equals("null")) {
			return null;
		}
		BTNode current;
		current = new BTNode(s);
		current.left.data = load(in).toString();
		current.right.data = load(in).toString();
		return current;
	}
	

	public void askQuestion() {
		String guess;
		String question;
		String response;
		
		if (tree.isEmpty()) {
			askForHelp();
		} else if (tree.getCurrent().data.contains("?")) {
			// A question with "?" in the String question
			question = tree.getCurrent().data;
			do {
				System.out.println(question);
				response = kb.nextLine().toLowerCase();
				if (response.equals("n")) {
					if (tree.getCurrent().right.data.contains("null")) {
						askForHelp();
					} else {
						tree.moveToRight();
						askQuestion();
					}
				} else if (response.equals("y")) {
					tree.moveToLeft();
					askQuestion();
				}
			} while (response.equals("y") && response.equals("n"));
		} else {
			guess = tree.getCurrent().data;
			question = "Are you thinking of " + guess + "?";
			do {
				System.out.println(question);
				response = kb.nextLine().toLowerCase();
				if (response.equals("y")) {
					endOfGame = true;
					System.out.println("BOOYAH I guessed right!");
					tree.setCurrentAsRoot();
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
		if (tree.isEmpty() == true) {
			tree.setRoot(newQuestion);
			tree.getRoot().left = new BTNode(newGuess);
			tree.getRoot().right = new BTNode("null");
			tree.setCurrentAsRoot();
			System.out.println("Okay, thank you! I'll remember that for next time...");
			endOfGame = true;
		} // if the curr is at root and user answers no
		else if (tree.getCurrent().data == tree.getRoot().data) {
			tree.getCurrent().right = new BTNode(newQuestion);
			tree.moveToRight();
			tree.getCurrent().left = new BTNode(newGuess);
			tree.getCurrent().right = new BTNode("null");
			tree.setCurrentAsRoot();
			System.out.println("Okay, thank you! I'll remember that for next time...");
			endOfGame = true;
		} else {
			if (tree.getCurrent().data.contains("?")) {
				tree.getCurrent().right = new BTNode(newQuestion);
				tree.moveToRight();
				tree.getCurrent().left = new BTNode(newGuess);
				tree.setCurrentAsRoot();
				System.out.println("Okay, thank you! I'll remember that for next time...");
				endOfGame = true;
			} else {
				oldGuess = tree.getCurrent().data;
				tree.getCurrent().data = newQuestion;
				tree.getCurrent().right = new BTNode(oldGuess);
				tree.getCurrent().left = new BTNode(newGuess);
				tree.setCurrentAsRoot();
				System.out.println("Okay, thank you! I'll remember that for next time...");
				endOfGame = true;
			}
		}
		save(tree.getRoot(), bufferedWriter);
	}

	public boolean getEndOfGame() {
		return endOfGame;
	}

	public void setEndOfGame(boolean b) {
		endOfGame = b;
	}

	/*
	 * public void Tree() {
	 * 
	 * // create a simple tree current = new BTNode("1"); root = current; current =
	 * new BTNode("2"); root.right = current; current = new BTNode("3"); root.left =
	 * current; root.left.left = new BTNode("4"); root.left.right = new BTNode("5");
	 * 
	 * System.out.println(root); System.out.println(root.right);
	 * System.out.println(root.left); System.out.println(root.left.left);
	 * System.out.println(root.left.right); }
	 * 
	 * public BTNode getBTNode() { return root; }
	 */

}