package nl.neurone.component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.neurone.domain.HuffManTree;
import nl.neurone.stream.BitStreamTestHelper;
import nl.neurone.stream.IBitStream;

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
		IBitStream bitStream = new BitStreamTestHelper("11");
		Object result = decoder.decodeValue(bitStream);
		
		// then
		assertEquals("b", result);
	}
	
	@Test
	public void testEncodeValue_2() {
		// given
		tree.addValue("d");
		
		// when
		IBitStream bitStream = new BitStreamTestHelper("01");
		Object result = decoder.decodeValue(bitStream);
		
		// then
		assertEquals("b", result);
	}
}
