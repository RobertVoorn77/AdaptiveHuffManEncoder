package nl.neurone.component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.neurone.domain.HuffManTree;
import nl.neurone.stream.BitStreamTestHelper;
import nl.neurone.stream.IBitStream;

public class EncodeDecoderTest {
	private IBitStream bitStream;
	private HuffManTree tree;
	private Decoder decoder;
	private Encoder encoder;

	@Before
	public void setup() {
		tree = new HuffManTree();
		tree.addValue("a");
		tree.addValue("b");
		tree.addValue("c");
		System.out.println("Setting up the tree: " + tree.getRoot().getString());
		bitStream = new BitStreamTestHelper();
		decoder = new Decoder(tree);
		encoder = new Encoder(tree, bitStream);
	}

	@Test
	public void testEncodeDecodeValue() {
		Object[] values = new Object[] {"a", "b", "c" };
		for (Object value : values) {
			// given
			setup();
			System.out.println("Encoding: " + value);
			encoder.encodeValue(value);
			
			// when
			Object result = decoder.decodeValue(bitStream);
	
			// then
			assertEquals(value, result);
			HuffManTree encoderTree = encoder.getTree();
			String encoderTreeStr = encoderTree.getRoot().getString();
			HuffManTree decoderTree = decoder.getTree();
			String decoderTreeStr = decoderTree.getRoot().getString();
			System.out.println("encoderTreeStr for '" + value + "': " + encoderTreeStr);
			System.out.println("decoderTreeStr for '" + value + "': " + decoderTreeStr);
			assertEquals(encoderTreeStr, decoderTreeStr);
		}
	}

	@Test
	public void testEncodeDecodeValues() {
		setup();
		// given
		Object[] values = new Object[] {"a", "b", "c" };
		encoder.encodeValues(values);

		// when
		Object[] result = decoder.decodeValues(bitStream);

		// then
		int index = 0;
		for (Object expectedValue : values) {
			assertEquals(expectedValue, result[index++]);
		}
		HuffManTree encoderTree = encoder.getTree();
		String encoderTreeStr = encoderTree.getRoot().getString();
		HuffManTree decoderTree = decoder.getTree();
		String decoderTreeStr = decoderTree.getRoot().getString();
		System.out.println("encoderTreeStr: " + encoderTreeStr);
		System.out.println("decoderTreeStr: " + decoderTreeStr);
		assertEquals(encoderTreeStr, decoderTreeStr);
	}
}
