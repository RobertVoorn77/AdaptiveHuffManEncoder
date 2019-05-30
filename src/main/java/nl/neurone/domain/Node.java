package nl.neurone.domain;

/**
 * This class is used to combine leafs and other nodes into a balanced binary tree (HuffManTree). The most important part of this class is the compareTo method.
 *  
 * @author Robert Voorn
 *
 */
public class Node implements TreeNode {
	private final TreeNode left;
	private final TreeNode right;
	private TreeNode parent;
	private final Long frequency;
	
	public Node(TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
		this.left.setParent(this);
		this.right.setParent(this);
		frequency = left.getFrequency() + right.getFrequency();
	}

	public Long getFrequency() {
		return frequency;
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

	/**
	 * This is the most important part of this class. The compareTo method compares 2 nodes (Leaf or Node) based on the frequency
	 * 
	 */
	@Override
	public int compareTo(Object that) {
		if (that == null) {
			return -1;
		} else {
			Long thatFrequency = ((TreeNode)that).getFrequency();
			Long thisFrequency = getFrequency();
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
