package nl.neurone.domain;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class HuffManTree {
	private Set<TreeNode> treeNodes = null; 
	private Set<TreeNode> leafs = null; 
	private Map<Object, Leaf> leafsMap = null;
	private TreeNode root; 
	
	public HuffManTree() {
		treeNodes = new TreeSet<>();
		leafs = new TreeSet<>();
		leafsMap = new TreeMap<>();
	}
	
	public void addInternalTreeNode(TreeNode node) {
		treeNodes.add(node);
	}

	public void addTreeNode(TreeNode node) {
		if (node instanceof Leaf) {
			leafs.add(node);
			Leaf leaf = (Leaf) node;
			leafsMap.put(leaf.getValue(), leaf);
		}
		addInternalTreeNode(node);
	}
	
	public Set<TreeNode> getTreeNodes() {
		return treeNodes;
	}
	
	public Set<TreeNode> getleafs() {
		return leafs;
	}
	
	public Leaf getLeafByValue(Object value) {
		return leafsMap.get(value);
	}
	
	public TreeNode rebuildTree() {
		List<TreeNode> tree = new Vector<>();
		tree.addAll(leafs);
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
		}
		Iterator<TreeNode> it = tree.iterator();
		root = it.next();
		return root;
	}

	private void sortTree(List<TreeNode> tree) {
		tree.sort(new Comparator<TreeNode>() {

			@Override
			public int compare(TreeNode a, TreeNode b) {
				return a.getFrequency().compareTo(b.getFrequency());
			}
			
		});
	}

	public TreeNode getRoot() {
		return root;
	}

	public void addValue(Object value) {
		addTreeNode(new Leaf(value));
		rebuildTree();
	}
}