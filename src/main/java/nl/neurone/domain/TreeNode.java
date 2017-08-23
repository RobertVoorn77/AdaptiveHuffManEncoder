package nl.neurone.domain;

public interface TreeNode extends Comparable<Object> {
	Long getFrequency();
	String getString();
	void setParent(TreeNode node);
	TreeNode getParent();
}
