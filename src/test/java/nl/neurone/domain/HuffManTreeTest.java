package nl.neurone.domain;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class HuffManTreeTest {
	
	@Test
	public void addInternalTreeNodeTest_uniqueNodes() {
		// given
		Leaf l = new Leaf("a");
		Leaf r = new Leaf("b");
		Node n = new Node(l, r);
		HuffManTree helper = new HuffManTree();
		helper.addInternalTreeNode(l);
		helper.addInternalTreeNode(r);
		helper.addInternalTreeNode(n);
		
		// when
		Set<TreeNode> resultNodes = helper.getTreeNodes();
		
		// then
		assertEquals(3, resultNodes.size());
		TreeNode[] nodes = resultNodes.toArray(new TreeNode[0]);
		assertEquals(l, nodes[0]);
		assertEquals(r, nodes[1]);
		assertEquals(n, nodes[2]);
	}
	
	@Test
	public void addInternalTreeNodeTest_nonUniqueNodes_1() {
		// given
		Leaf l = new Leaf("a");
		Leaf l2 = new Leaf("a");
		Leaf r = new Leaf("b");
		Leaf r2 = new Leaf("b");
		TreeNode n = new Node(l, r);
		TreeNode n2 = new Node(l2, r2);	
		
		HuffManTree helper = new HuffManTree();
		helper.addInternalTreeNode(l);
		helper.addInternalTreeNode(r);
		helper.addInternalTreeNode(l2);
		helper.addInternalTreeNode(r2);
		helper.addInternalTreeNode(n);
		helper.addInternalTreeNode(n2);
		
		// when
		Set<TreeNode> resultNodes = helper.getTreeNodes();
		
		// then
		assertEquals(3, resultNodes.size());
		TreeNode[] nodes = resultNodes.toArray(new TreeNode[0]);
		assertEquals(l, nodes[0]);
		assertEquals(r, nodes[1]);
		assertEquals(n, nodes[2]);
	}
	
	@Test
	public void addTreeNodeTest() {
		// given
		Leaf l = new Leaf("a");
		Leaf r = new Leaf("b");
		Node n = new Node(l, r);
		HuffManTree helper = new HuffManTree();
		helper.addTreeNode(l);
		helper.addTreeNode(r);
		helper.addTreeNode(n);
		
		// when
		Set<TreeNode> resultNodes = helper.getTreeNodes();
		Set<TreeNode> resultLeafs = helper.getLeafs();
		Object resultValue = helper.getLeafByValue(l.getValue());
		
		// then
		assertEquals(3, resultNodes.size());
		TreeNode[] nodes = resultNodes.toArray(new TreeNode[0]);
		assertEquals(l, nodes[0]);
		assertEquals(r, nodes[1]);
		assertEquals(n, nodes[2]);
		
		assertEquals(2, resultLeafs.size());
		TreeNode[] leafs = resultLeafs.toArray(new TreeNode[0]);
		assertEquals(l, leafs[0]);
		assertEquals(r, leafs[1]);
		
		assertEquals(resultValue, l);
	}
	
	@Test
	public void testRebuildTree() {
		// given
		Leaf l = new Leaf("l");
		Leaf r = new Leaf("r");
		Leaf r1 = new Leaf("r1");
		HuffManTree helper = new HuffManTree();
		helper.addTreeNode(l);
		helper.addTreeNode(r);
		helper.addTreeNode(r1);
		
		// when
		Node root = (Node) helper.rebuildTree();
		
		// then
		assertNotNull(root);
		Leaf leftLeaf = (Leaf) root.getLeft();
		assertEquals(leftLeaf.getValue(), r1.getValue());
		Node rightNode = (Node) root.getRight();
		assertEquals(rightNode.getLeft(), l);
		assertEquals(rightNode.getRight(), r);
	}
	
	@Test
	public void testAddValue() {
		// given
		HuffManTree helper = new HuffManTree();
		helper.addValue("q");
		
		// when
		TreeNode rootLeaf = helper.getRoot();
		
		// then
		assertEquals("q", ((Leaf)rootLeaf).getValue());
	}
}