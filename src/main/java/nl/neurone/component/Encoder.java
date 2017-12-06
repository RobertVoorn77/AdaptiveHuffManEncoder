package nl.neurone.component;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

/**
 * Generic encoder that does not have any knowledge of the data that needs encoding.
 * @author Robert Voorn
 *
 */
public class Encoder {
	private HuffManTree treeBuilder;
	private TreeNode root;
	private IBitStream bitStream;

	public Encoder(IBitStream bitStream) {
		this.bitStream = bitStream;
	}
	
	public void setHuffManTree(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	public HuffManTree getHuffManTree() {
		return this.treeBuilder;
	}
	
	public String encodeValue(Object value) {
		TreeNode leaf = (TreeNode) treeBuilder.getLeafByValue(value);
		root = treeBuilder.getRoot();
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
