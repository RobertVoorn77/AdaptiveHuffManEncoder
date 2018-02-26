package nl.neurone.component;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

/**
 * Generic encoder that does not have any knowledge of the data that needs encoding. No initial tree is present. This will be done by a subclass of this class.
 * 
 * @author Robert Voorn
 */
public class Encoder {
	private HuffManTree treeBuilder;
	private TreeNode root;
	private final IBitStream bitStream;

	public Encoder(IBitStream bitStream) {
		this.bitStream = bitStream;
	}
	
	public void setHuffManTree(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	public HuffManTree getHuffManTree() {
		return this.treeBuilder;
	}
	
	/**
	 * This method encodes a value and writes the binary encoding bits to the bitstream (given at construction).
	 * 
	 * @param value the value that needs to be encoded
	 * @return String this string should not be used and is primarily for testing purposes
	 */
	public String encodeValue(Object value) {
		TreeNode leaf = (TreeNode) treeBuilder.getLeafByValue(value);
		root = treeBuilder.getRoot();
		String encodeValueStr = encodeValueStr(leaf);
		// after encoding is finished, update the frequency if the initial value was a leaf (nodes will be recalculated when rebuilding the tree)
		if (leaf instanceof Leaf) {
			Leaf initialLeaf = (Leaf)leaf;
			treeBuilder.incrementFrequency(initialLeaf);
		}
		return encodeValueStr;
	}
	
	private String encodeValueStr(TreeNode node) {
		if (node == root) {
			// root is reached, start building the bit string by backing out of the recursion!
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
	
	
	/**
	 * Convenience method to encode an array of values. This simply loops over the values and feeds them to the encodeValue method.
	 * 
	 * @param values Object[] an array of values to be encoded 
	 */
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
