package nl.neurone.component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.neurone.domain.HuffManTree;

public class DecoderTest {
	private Decoder decoder;
	private HuffManTree tree;

	@Before
	public void setup() {
		tree = new HuffManTree();
		tree.addValue("a");
		tree.addValue("b");
		tree.addValue("c");
		decoder = new Decoder(tree);
	}
	
	@Test
	public void testDecodeValue() {
		// when
		Object result = decoder.decodeValue("11");
		
		// then
		assertEquals("b", result);
	}
	
	@Test
	public void testEncodeValue_2() {
		// given
		tree.addValue("d");
		
		// when
		Object result = decoder.decodeValue("01");
		
		// then
		assertEquals("b", result);
	}
}
