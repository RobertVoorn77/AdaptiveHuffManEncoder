package nl.neurone.stream;

public interface IBitStream {

	public boolean readBit();
	public void writeBit(boolean bit);
	public void reset();
	public boolean isEOF();
	
}
