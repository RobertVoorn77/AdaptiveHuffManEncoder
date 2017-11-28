package nl.neurone.component;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

public class Encoder {
	private HuffManTree treeBuilder;
	private TreeNode root;
	private IBitStream bitStream;

	public Encoder(HuffManTree treeBuilder, IBitStream bitStream) {
		this.treeBuilder = treeBuilder;
		this.bitStream = bitStream;
	}
	
	String encodeValue(Object value) {
		TreeNode leaf = (TreeNode) treeBuilder.getLeafByValue(value);
		root = treeBuilder.getRoot();
		System.out.println(root.getString());
		return encodeValueStr(leaf);
	}
	
	private String encodeValueStr(TreeNode node) {
		if (node == root) {
			// root is reached, start building the bit string!
			return "";
		}
		TreeNode parent = node.getParent();
		String result = encodeValueStr(parent);
		if (((Node)parent).getLeft().equals(node)) {
			result += "0";
			bitStream.writeBit(false);
		} else {
			result += "1";
			bitStream.writeBit(true);
		}
		return result;
	}
	
	public void encodeValues(Object[] values) {
		for (Object value : values) {
			encodeValue(value);
		}
	}
	
	@Override
	public String toString() {
		return "Encoder state, bitStream: " + bitStream + " with treeBuilder: " + treeBuilder.getRoot().getString();
	}
}
