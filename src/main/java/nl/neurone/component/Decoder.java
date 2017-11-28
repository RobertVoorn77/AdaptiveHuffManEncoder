package nl.neurone.component;

import nl.neurone.domain.HuffManTree;
import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;

public class Decoder {
	private HuffManTree treeBuilder;
	private TreeNode root;

	public Decoder(HuffManTree treeBuilder) {
		this.treeBuilder = treeBuilder;
	}
	
	Object decodeValue(String bits) {
		root = treeBuilder.getRoot();
		System.out.println(root.getString());
		TreeNode node = root;

		int i = 0;
		while (!(node instanceof Leaf)) {
			if (bits.charAt(i++) == '1') {
				node = ((Node)node).getRight();
			} else {
				node = ((Node)node).getLeft();
			}
		}
		return ((Leaf)node).getValue();
	}
	
}
