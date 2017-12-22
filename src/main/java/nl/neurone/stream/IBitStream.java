package nl.neurone.stream;

public interface IBitStream {
	boolean readBit();
	void writeBit(boolean bit);
	void reset();
	boolean isEOF();
	long size();
	void writeByte(byte b);
	void writeLong(long l);
	byte readByte();
	long readLong();

	void close();
}
