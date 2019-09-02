package nl.neurone.component;

import java.util.LinkedList;
import java.util.List;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

/**
 * Generic decoder that does not have any knowledge of the data that needs decoding. No initial tree is present. This will be done by a subclass of this class.
 * 
 * @author Robert Voorn
 */
public class Decoder {
	private HuffManTree treeBuilder;

	void setHuffManTree(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	/**
	 * This method decodes a value based on the bits in the bitstream. It traverses the HuffManTree from the root node to the first leaf node, going into the
	 * left child tree when a 0 is read, the right child tree if a 1 is read.
	 *   
	 * @param bitStream the bitstream to read the bits from
	 * @return an Object value that was decoded from the bitstream
	 */
	public Object decodeValue(IBitStream bitStream) {
		TreeNode node = treeBuilder.getRoot();

		while (!(node instanceof Leaf)) {
			if (bitStream.readBit()) {
				node = ((Node)node).getRight();
			} else {
				node = ((Node)node).getLeft();
			}
		}
		Leaf leaf = (Leaf)node;
		treeBuilder.incrementFrequency(leaf);
		return leaf.getValue();
	}
	
	/**
	 * Convenience method and not really used in the applications and then written individually to an output stream
	 * 
	 * @param bitStream the bitstream to read from
	 * @return an Object[] of decoded values
	 */
	Object[] decodeValues(IBitStream bitStream) {
		List<Object> values = new LinkedList<>();

		while (!bitStream.isEOF()) {
			Object value = decodeValue(bitStream);
			values.add(value);
		}
		
		return values.toArray();
	}

	HuffManTree getHuffManTree() {
		return treeBuilder;
	}
}
