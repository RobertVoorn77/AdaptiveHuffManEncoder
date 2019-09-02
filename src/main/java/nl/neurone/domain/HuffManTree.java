package nl.neurone.domain;

import java.util.*;

/**
 * Generic HuffManTree implementation that will be used by the generic Encoder and Decoder. It provides some methods for adding values
 * as nodes in the tree.
 * TODO: This implementation is suboptimal and quite slow (rebuildTree solution) and should be improved once the experiment has been
 * proven to work
 * 
 *  The main part of this class is the RebuildTree method.
 * @author Robert Voorn
 *
 */
public class HuffManTree {
	private final Set<TreeNode> treeNodes;
	private final Set<TreeNode> leafs;
	private final Map<Object, Leaf> leafsMap;
	private TreeNode root; 
	
	public HuffManTree() {
		treeNodes = new TreeSet<>();
		leafs = new TreeSet<>();
		leafsMap = new TreeMap<>();
	}
	
	void addInternalTreeNode(TreeNode node) {
		treeNodes.add(node);
	}

	void addTreeNode(TreeNode node) {
		if (node instanceof Leaf) {
			leafs.add(node);
			Leaf leaf = (Leaf) node;
			leafsMap.put(leaf.getValue(), leaf);
		}
		addInternalTreeNode(node);
	}
	
	Set<TreeNode> getTreeNodes() {
		return treeNodes;
	}
	
	Set<TreeNode> getLeafs() {
		return leafs;
	}
	
	public TreeNode getLeafByValue(Object value) {
		return leafsMap.get(value);
	}
	
	/**
	 * As mentioned above, this is the most important part of this class. The goal is to combine all the know leafs (values) in such a way that a balanced tree is created based
	 * on the frequency of the leafs/nodes. The algorithm works as follows:
	 * 
	 * 1. make a list of all leafs
	 * 2. sort the list based on the frequencies
	 * 3. take the 2 elements with the lowest frequency
	 * 4. combine this leafs/nodes into a TreeNode (left child always has the lowest frequency)
	 * 5. add the new node to the list
	 * 6. repeat until the list contains just one node (this will be the root node of the binary tree)
	 * 
	 * @return root node of the binary tree
	 */
	TreeNode rebuildTree() {
		List<TreeNode> tree = new ArrayList<>(leafs);
		while (tree.size() > 1) {
            // sort all nodes on frequency (ascending)
            sortTree(tree);
			// get the first 2 nodes (lowest frequency)
			Iterator<TreeNode> it = tree.iterator();
			TreeNode left = it.next();
			it.remove();
			TreeNode right = it.next();
			it.remove();
			// combine lowest frequencies and add them to the list of nodes
			Node newNode = new Node(left, right);
			tree.add(newNode);
//			addSorted(tree, newNode);
		}
		Iterator<TreeNode> it = tree.iterator();
		root = it.next();
//		System.out.println("Tree: " + methodName + " --> " + root.getString());
		return root;
	}

//    private void addSorted(List<TreeNode> tree, Node newNode) {
//	    if (tree.size() == 0) {
//	        // empty list is already sorted , so just add the new node!
//            tree.add(newNode);
//	        return;
//        }
//	    Iterator it = tree.iterator();
//	    int index = 0;
////	    while (it.hasNext() && index<tree.size()) {
//        while (it.hasNext()) {
//	        TreeNode current = (TreeNode) it.next();
//	        index++;
//	        if (newNode.compareTo(current) == 0) {
//	            // stop when an equal is found and add new node there!
//                tree.add(index, newNode);
//                return;
//            }
//        }
//    }

    private void sortTree(List<TreeNode> tree) {
		tree.sort(Comparator.comparing(TreeNode::getFrequency));
	}

	public TreeNode getRoot() {
		return root;
	}

	public void addValue(Object value) {
		addTreeNode(new Leaf(value));
		rebuildTree();
	}

	/**
	 * Increment the frequency for a specific value (Leaf) and recalculate the tree so the changes are propagated through the HuffManTree
	 * 
	 * @param leaf the Leaf containing the value which frequency should be incremented
	 */
	public void incrementFrequency(Leaf leaf) {
		leaf.incrementFrequency();
		rebuildTree();
	}
}