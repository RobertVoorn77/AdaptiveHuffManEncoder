package nl.neurone.component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.neurone.domain.HuffManTree;
import nl.neurone.stream.BitStreamTestHelper;
import nl.neurone.stream.IBitStream;

public class EncoderTest {
	private Encoder encoder;
	private HuffManTree tree;
	private IBitStream bitStream;

	@Before
	public void setup() {
		tree = new HuffManTree();
		tree.addValue("a");
		tree.addValue("b");
		tree.addValue("c");
		bitStream = new BitStreamTestHelper();
		encoder = new Encoder(tree, bitStream);
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
	
	@Test
	public void testEncodeValues() {
		// when
		encoder.encodeValues(new Object[] {"a", "c", "a", "b"});
		
		// then
		String result = ((BitStreamTestHelper)bitStream).getResultString();
		assertEquals("1001011", result);
	}
}
