package nl.neurone.component;

import java.util.LinkedList;
import java.util.List;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitStream;

public class Decoder {
	private HuffManTree treeBuilder;
	private TreeNode root;

	public Decoder(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	Object decodeValue(IBitStream bitStream) {
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
//		System.out.println("Tree when decoding for " + leaf.getValue() + " --> " + root.getString());
		leaf.incrementFrequency();
		return leaf.getValue();
	}
	
	Object[] decodeValues(IBitStream bitStream) {
		List<Object> values = new LinkedList<>();
		
		while (!bitStream.isEOF()) {
			Object value = decodeValue(bitStream);
			values.add(value);
		}
		
		return values.toArray();
	}

	public HuffManTree getTree() {
		return treeBuilder;
	}
}
