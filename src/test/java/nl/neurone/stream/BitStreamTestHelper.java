package nl.neurone.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BitStreamTestHelper implements IBitStream {
	private char[] bits = null;
	private int index = 0;
	private List<Character> bitsList = null;
	
	public BitStreamTestHelper(String bits) {
		this.bits = bits.toCharArray();
		bitsList = new LinkedList<Character>();
		for (char bit : this.bits) {
			bitsList.add(bit);
		}
	}
	
	public BitStreamTestHelper() {
		this("");
	}

	@Override
	public boolean readBit() {
		char bit = bits[index++];
		return bit == '1';
	}

	@Override
	public void writeBit(boolean bit) {
		bitsList.add(bit?'1':'0');
		bits = new char[bitsList.size()];
		for (int index = 0; index < bits.length; index++) {
			bits[index] = bitsList.get(index);
		}
	}

	@Override
	public void reset() {
		index = 0;
		bitsList = new LinkedList<Character>();
	}

	@Override
	public boolean isEOF() {
		return index == bitsList.size();
	}

	public String getResultString() {
		String result = "";
		for (Character bit : bitsList) {
			result += bit;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return ", index: " + index + ", array: " + Arrays.toString(bits) + " and List: " + bitsList;
	}
	
}
