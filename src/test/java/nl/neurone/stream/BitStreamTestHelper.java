package nl.neurone.stream;

import java.util.LinkedList;
import java.util.List;

public class BitStreamTestHelper implements IBitStream {
	private char[] bits = null;
	private int index = 0;
	private List<Character> bitsList = new LinkedList<Character>();
	
	public BitStreamTestHelper(String bits) {
		this.bits = bits.toCharArray();
		bitsList = new LinkedList<Character>();
		for (char bit : this.bits) {
			bitsList.add(bit);
		}
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
		int index = 0;
		for (char bitLoop : bits) {
			bits[index++] = bitLoop;
		}
	}

	@Override
	public char readCharAt(int i) {
		return bitsList.get(i);
	}

}
