package nl.neurone.stream;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class BitStreamTestHelperTest {

	@Test
	public void testReadBit() {
		// given
		String bitStreamString = "";
		Random random = new Random();
		for (int i=0; i < 20; i++) {
		    bitStreamString += random.nextBoolean() ? "1" : "0";
		}
//		String bitStreamString = "100110010110";
		IBitStream bitStream = new BitStreamTestHelper(bitStreamString);
		char[] bitArray = bitStreamString.toCharArray();
		
		// then when
		for (char bit : bitArray) {
			Boolean bitBool = (bit == '1');
			boolean readBit = bitStream.readBit();
			System.out.println("comparing " + bitBool + " with " + readBit);
			assertEquals(bitBool, readBit);
		}
	}
	
}
