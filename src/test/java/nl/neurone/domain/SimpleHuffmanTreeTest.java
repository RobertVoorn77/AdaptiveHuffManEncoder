package nl.neurone.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleHuffmanTreeTest {
    @Test
    public void testGetRoot() {
        // given
        HuffmanTree tree = new SimpleHuffmanTree();
        tree.addValue('t');

        // then
        TreeNode root = tree.getRoot();

        // then
        assertEquals('t', ((Leaf)root).getValue());
    }

    @Test
    public void testAddValue() {
        // given
        HuffmanTree tree = new SimpleHuffmanTree();

        // when
        tree.addValue('s');
        tree.addValue('t');
        tree.addValue('d');
        TreeNode root = tree.getRoot();

        // then
        assertNotNull(root);
        assertNull(root.getParent());
        assertEquals(3, root.getFrequency());
        Node r = (Node) root;
        final TreeNode left = r.getLeft();
        assertTrue(left instanceof Leaf);
        assertEquals('d', ((Leaf) left).getValue());
        assertTrue(r.getRight() instanceof Node);
        final Node right = (Node) r.getRight();
        assertNotNull(right);
        assertEquals(2, right.getFrequency());
        assertEquals('s', ((Leaf)right.getLeft()).getValue());
        assertEquals('t', ((Leaf)right.getRight()).getValue());
    }
}