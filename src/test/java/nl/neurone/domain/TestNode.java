package nl.neurone.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.neurone.domain.Leaf;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;

public class TestNode {

	@Test
	public void testFrequency() {
		// given
		Leaf left = new Leaf("a");
		Leaf right = new Leaf("b");
		Node node = new Node(left, right);
		
		// when
		long resultFrequency = node.getFrequency();
		String resultString = node.getString();
		TreeNode resultLeft = node.getLeft();
		TreeNode resultRight = node.getRight();
		TreeNode resultParent = node.getParent();
		
		// then
		assertEquals(2, resultFrequency);
		assertEquals("( (a, 1) <- 2 -> (b, 1) )", resultString);
		assertEquals(left, resultLeft);
		assertEquals(right, resultRight);
		assertEquals(node, resultLeft.getParent());
		assertEquals(node, resultRight.getParent());
		assertNull(resultParent);
	}
	
	@Test
	public void testNodeCompareTo_frequency() {
		// given
		Leaf left1 = new Leaf("a");
		Leaf right1 = new Leaf("b");
		Node node1 = new Node(left1, right1);
		Leaf left2 = new Leaf("a", 2);
		Leaf right2 = new Leaf("b", 2);
		Node node2 = new Node(left2, right2);
		Leaf left1a = new Leaf("a");
		Leaf right1a = new Leaf("b");
		Node node1a = new Node(left1a, right1a);

		// when
		int comparedToLower = node1.compareTo(node2);
		int comparedToHigher = node2.compareTo(node1);
		int comparedToEqual = node1.compareTo(node1a);
		int comparedToNull = node1.compareTo(null);
		
		// then
		assertEquals(-1, comparedToLower);
		assertEquals(1, comparedToHigher);
		assertEquals(0, comparedToEqual);
		assertEquals(-1, comparedToNull);
	}
}
