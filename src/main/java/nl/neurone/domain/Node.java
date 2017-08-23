package nl.neurone.domain;

public class Node implements TreeNode {
	TreeNode left = null;
	TreeNode right = null;
	TreeNode parent;
	
	public Node(TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
		this.left.setParent(this);
		this.right.setParent(this);
	}

	public Long getFrequency() {
		return left.getFrequency() + right.getFrequency();
	}
	
	public TreeNode getLeft() {
		return left;
	}
	
	public TreeNode getRight() {
		return right;
	}
	
	public String getString() {
		return "( " + left.getString() + " <- " + getFrequency() + " -> " + right.getString() + " )";
	}

	@Override
	public int compareTo(Object that) {
		if (that == null) {
			return -1;
		} else {
			Long thatFrequency = ((TreeNode)that).getFrequency();
			Long thisFrequency = new Long(getFrequency());
			return thisFrequency.compareTo(thatFrequency);
		}
	}

	@Override
	public void setParent(TreeNode node) {
		this.parent = node;
	}

	@Override
	public Node getParent() {
		return (Node) parent;
	}
}
