package nl.neurone.domain;

public interface TreeNode extends Comparable<Object> {
	long getFrequency();
	void setParent(TreeNode parent);
	TreeNode getParent();
}
