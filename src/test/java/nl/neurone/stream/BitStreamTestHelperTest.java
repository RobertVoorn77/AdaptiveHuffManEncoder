package nl.neurone.stream;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class BitStreamTestHelperTest {

	@Test
	public void testReadBit() {
		// given
		String bitStreamString = getRandomBinaryString();
		IBitStream bitStream = new BitStreamTestHelper(bitStreamString);
		char[] bitArray = bitStreamString.toCharArray();
		
		// then when
		for (char bit : bitArray) {
			Boolean bitBool = (bit == '1');
			boolean readBit = bitStream.readBit();
			assertEquals(bitBool, readBit);
		}
	}

	private String getRandomBinaryString() {
		StringBuilder bitStreamString = new StringBuilder();
		Random random = new Random();
		for (int i=0; i < 20; i++) {
		    bitStreamString.append(random.nextBoolean() ? "1" : "0");
		}
		return bitStreamString.toString();
	}
	
	@Test
	public void testWriteBits() {
		// given
		String bitStreamString = getRandomBinaryString();
		IBitStream bitStream = new BitStreamTestHelper("");
		
		// when
		char[] bitCharArray = bitStreamString.toCharArray();
		for (Character bit : bitCharArray) {
			boolean bitBool = bit == '1';
			bitStream.writeBit(bitBool );
		}
		
		// then
		bitStream.reset();
		for (char bit : bitCharArray) {
			boolean bitBool = bit == '1';
			assertEquals(bitBool, bitStream.readBit());
		}
	}
}
