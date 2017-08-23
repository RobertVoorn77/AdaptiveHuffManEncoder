package nl.neurone.component;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;

public class Encoder {
	private HuffManTree treeBuilder;
	private TreeNode root;

	public Encoder(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	String encodeValue(Object value) {
		TreeNode leaf = (TreeNode) treeBuilder.getLeafByValue(value);
		root = treeBuilder.getRoot();
		System.out.println(root.getString());
		return encodeValueStr(leaf);
	}
	
	String encodeValueStr(TreeNode node) {
		if (node == root) {
			// root is reached, start building the bit string!
			return "";
		}
		TreeNode parent = node.getParent();
		String result = encodeValueStr(parent);
		if (((Node)parent).getLeft().equals(node)) {
			result += "0";
		} else {
			result += "1";
		}
		return result;
	}
}
