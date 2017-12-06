package nl.neurone.component;

import static org.junit.Assert.*;

import java.util.Random;

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
		decoder = new Decoder();
		decoder.setHuffManTree(tree);
		encoder = new Encoder(bitStream);
		encoder.setHuffManTree(tree);
	}

	@Test
	public void testEncodeDecodeValue() {
		Object[] values = getRandomInputObjects();
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

	private Object[] getRandomInputObjects() {
		int size = 30;
		Object[] result = new Object[size];
		Random random = new Random();
		Object[] choices = new Object[] {"a", "b", "c" };
		for (int i = 0; i < size; i++) {
			result[i] = choices[random.nextInt(choices.length)];
		}
		return result;
	}

	@Test
	public void testEncodeDecodeValues() {
		setup();
		// given
		Object[] values = getRandomInputObjects();
		encoder.encodeValues(values);

		// when
		Object[] result = decoder.decodeValues(bitStream);

		// then
		int index = 0;
		for (Object expectedValue : values) {
			assertEquals(expectedValue, result[index++]);
		}
		System.out.println("Encoder state: " + encoder.toString());
		HuffManTree encoderTree = encoder.getTree();
		String encoderTreeStr = encoderTree.getRoot().getString();
		HuffManTree decoderTree = decoder.getTree();
		String decoderTreeStr = decoderTree.getRoot().getString();
		System.out.println("encoderTreeStr: " + encoderTreeStr);
		System.out.println("decoderTreeStr: " + decoderTreeStr);
		assertEquals(encoderTreeStr, decoderTreeStr);
	}
}
