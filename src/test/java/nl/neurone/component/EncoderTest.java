package nl.neurone.component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.neurone.domain.HuffManTree;

public class EncoderTest {
	private Encoder encoder;
	private HuffManTree tree;

	@Before
	public void setup() {
		tree = new HuffManTree();
		tree.addValue("a");
		tree.addValue("b");
		tree.addValue("c");
		encoder = new Encoder(tree);
	}
	
	@Test
	public void testEncodeValue() {
		// when
		String result = encoder.encodeValue("b");
		
		// then
		assertEquals("11", result);
	}
	
	@Test
	public void testEncodeValue_2() {
		// given
		tree.addValue("d");
		
		// when
		String result = encoder.encodeValue("b");
		
		// then
		assertEquals("01", result);
	}
}
