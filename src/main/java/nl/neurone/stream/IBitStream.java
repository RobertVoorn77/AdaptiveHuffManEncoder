package nl.neurone.stream;

public interface IBitStream {

	public boolean readBit();
	public void writeBit(boolean bit);
	public char readCharAt(int i);
	
}
