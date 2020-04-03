package nl.neurone.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeafTest {
    private static final char VALUE = 'v';
    private static final char OTHER_VALUE = 'o';
    private Leaf leaf;
    private Leaf otherLeaf;

    @Before
    public void setUp() {
        leaf = new Leaf(VALUE);
        otherLeaf = new Leaf(OTHER_VALUE);
    }

    @Test
    public void getFrequency() {
        assertEquals(1, leaf.getFrequency());
    }

    @Test
    public void setParent() {
        // given
        final Leaf parent = new Leaf('p');

        // when
        leaf.setParent(parent);

        // then
        assertEquals(parent, leaf.getParent());
    }

    @Test
    public void compareTo_frequencyEqual() {
        // when
        int result = leaf.compareTo(otherLeaf);

        // then
        assertEquals(0, result);
    }

    @Test
    public void compareTo_frequencyLower() {
        // given
        Leaf lowerLeaf = new Leaf(0, OTHER_VALUE);

        // when
        int result = leaf.compareTo(lowerLeaf);

        // then
        assertEquals(1, result);
    }

    @Test
    public void compareTo_frequencyHigher() {
        // given
        Leaf lowerLeaf = new Leaf(2, OTHER_VALUE);

        // when
        int result = leaf.compareTo(lowerLeaf);

        // then
        assertEquals(-1, result);
    }

    @Test
    public void compareTo_notALeaf() {
        // when
        int result = leaf.compareTo("notALeaf");

        // then
        assertEquals(-1, result);
    }

    @Test
    public void getValue() {
        // when
        Object value = leaf.getValue();

        // then
        assertEquals(VALUE, value);
    }

    @Test
    public void setValue() {
        // when
        leaf.setValue(OTHER_VALUE);

        // then
        assertNotEquals(VALUE, leaf.getValue());
    }

    @Test
    public void testIsRoot() {
        assertFalse(new Leaf((char)1).isRoot());
    }
}