package nl.neurone.component;

import java.util.LinkedList;
import java.util.List;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

/**
 * Generic decoder that does not have any knowledge of the data that needs decoding.
 * @author Robert Voorn
 *
 */
public class Decoder {
	private HuffManTree treeBuilder;
	private TreeNode root;

	public void setHuffManTree(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	public Object decodeValue(IBitStream bitStream) {
		root = treeBuilder.getRoot();
		TreeNode node = root;

		while (!(node instanceof Leaf)) {
			if (bitStream.readBit()) {
				node = ((Node)node).getRight();
			} else {
				node = ((Node)node).getLeft();
			}
		}
		Leaf leaf = (Leaf)node;
		leaf.incrementFrequency();
		return leaf.getValue();
	}
	
	/**
	 * Convenience method and not really used in the applications (makes no sense for data to be first processed into array of Objects
	 * and then written individually to an output stream
	 * @param bitStream
	 * @return
	 */
	Object[] decodeValues(IBitStream bitStream) {
		List<Object> values = new LinkedList<>();
		
		while (!bitStream.isEOF()) {
			Object value = decodeValue(bitStream);
			values.add(value);
		}
		
		return values.toArray();
	}

	public HuffManTree getHuffManTree() {
		return treeBuilder;
	}
}
