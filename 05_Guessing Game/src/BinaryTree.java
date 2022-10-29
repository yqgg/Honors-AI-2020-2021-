
public class BinaryTree {
	private BTNode root;
	private BTNode current;

	public BinaryTree() {
		root = null;
		current = null;
	}
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	public boolean isAtEnd() {
		if ((current != null) && (current.left == null && current.right == null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setRoot(String s) {
		root = new BTNode(s);
	}

	public BTNode getCurrent() {
		return current;
	}

	public BTNode getRoot() {
		return root;
	}

	public void moveToLeft() {
		if(current.left == null) {
			current.left = new BTNode("null");
		}
		current = current.left;
	}

	public void moveToRight() {
		if(current.right == null) {
			current.right = new BTNode("null");
		}
		current = current.right;
	}

	public void setCurrentAsRoot() {
		current = root;
	}
	
	public void Tree() {

		// create a simple tree current = new BTNode("1"); root = current; current =
		new BTNode("Does the animal have a long neck?");
		root.right = new BTNode(null);
		root.left = new BTNode("Giraffe");
	}

	public BTNode getBTNode() {
		return root;
	}

}
