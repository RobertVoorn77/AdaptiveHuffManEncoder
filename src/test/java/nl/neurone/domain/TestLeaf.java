package nl.neurone.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.neurone.domain.Leaf;

public class TestLeaf {

	@Test
	public void testFrequency_constructor_Default() {
		// given
		Leaf l = new Leaf("a");
		
		// when
		Object resultValue = l.getValue();
		long resultFrequency = l.getFrequency();
		String resultString = l. getString();
		
		// then
		assertEquals("a", resultValue);
		assertEquals(1, resultFrequency);
		assertEquals("(a, 1)", resultString);
		assertNull(l.getParent());
	}

	@Test
	public void testFrequency_constructor_Frequency() {
		// given
		Leaf l = new Leaf("b", 11);
		
		// when
		Object resultValue = l.getValue();
		long resultFrequency = l.getFrequency();
		String resultString = l. getString();
		
		// then
		assertEquals("b", resultValue);
		assertEquals(11, resultFrequency);
		assertEquals("(b, 11)", resultString);
	}
	
	@Test
	public void testCompareTo_frequency() {
		// given
		Leaf lower = new Leaf("a", 1);
		Leaf higher = new Leaf("a", 2);
		Leaf higher2 = new Leaf("a", 2);
		
		// when
		int compareTo1 = lower.compareTo(higher);
		int compareTo2 = higher.compareTo(lower);
		int compareTo3 = higher.compareTo(higher2);
		
		// then
		assertEquals(-1, compareTo1);
		assertEquals(1, compareTo2);
		assertEquals(0, compareTo3);
	}
	
	@Test
	public void testCompareTo_value() {
		// given
		Leaf lower = new Leaf("a");
		Leaf higher = new Leaf("b");
		
		// when
		int compareTo1 = lower.compareTo(higher);
		int compareTo2 = higher.compareTo(lower);
		int compareToNull = higher.compareTo(null);
		
		// then
		assertEquals(-1, compareTo1);
		assertEquals(1, compareTo2);
		assertEquals(-1, compareToNull);
	}
	
	@Test
	public void testCompareTo_node() {
		// given
		Leaf lower = new Leaf("a");
		Node higher = new Node(lower, new Leaf("b"));
		
		// when
		int compareTo1 = lower.compareTo(higher);
		int compareTo2 = higher.compareTo(lower);
		
		// then
		assertEquals(-1, compareTo1);
		assertEquals(1, compareTo2);
	}
}
