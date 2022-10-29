

public class BTNode {
	//instance variables
	public String data;
	public BTNode left;
	public BTNode right;
	public BTNode current;
	public BTNode root;
	
	public BTNode(String s) {
		data = s;
		left = null;
		right = null;
		current = null;
		root = null;
	}
	
	public String toString() {
		return data;
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
	
	public void moveToLeft() {
		if(current.left == null) {
			current.data = "null";
		}
		current = current.left;
	}

	public void moveToRight() {
		if(current.right == null) {
			current.data = "null";
		}
		current = current.right;
	}

	public void setCurrentAsRoot() {
		current = root;
	}

}
