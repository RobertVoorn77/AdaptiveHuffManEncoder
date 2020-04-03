package nl.neurone.domain;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void testGetters() {
        // given
        Leaf left = new Leaf('l');
        Leaf right = new Leaf('r');

        // when
        Node node = new Node(left, right);
        Node parent = new Node(node, new Leaf('s'));

                // then
        Assert.assertSame(left, node.getLeft());
        Assert.assertSame(right, node.getRight());
        Assert.assertSame(parent, node.getParent());
    }

    @Test
    public void testFrequency() {
        // given
        Leaf left = new Leaf(1 ,'l');
        Leaf right = new Leaf(2, 'r');

        // when
        Node node = new Node(left, right);

        // then
        Assert.assertEquals(3, node.getFrequency());
    }

    @Test
    public void testLeftLessFrequent() {
        // given
        Leaf left = new Leaf(2 ,'l');
        Leaf right = new Leaf(1, 'r');

        // when
        Node node = new Node(left, right);

        // then
        Assert.assertSame(right, node.getLeft());
        Assert.assertSame(left, node.getRight());
    }

    @Test
    public void testIsRoot() {
        // given
        Leaf left = new Leaf(2 ,'l');
        Leaf right = new Leaf(1, 'r');
        Node node = new Node(left, right);

        // when
        boolean isRoot = node.isRoot();

        // then
        Assert.assertTrue(isRoot);
    }

    @Test
    public void testIsNotRoot() {
        // given
        Leaf left = new Leaf(2 ,'l');
        Leaf right = new Leaf(1, 'r');
        Node node = new Node(left, right);
        node.setParent(new Node(left, right));

        // when
        boolean isRoot = node.isRoot();

        // then
        Assert.assertFalse(isRoot);
    }

}
