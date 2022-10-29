

public class BTNode {
	//instance variables
	public String data;
	public BTNode left;
	public BTNode right;
	
	public BTNode(String rhs) {
		data = rhs;
		left = null;
		right = null;
	}
	
	public String toString() {
		return data;
	}

}
