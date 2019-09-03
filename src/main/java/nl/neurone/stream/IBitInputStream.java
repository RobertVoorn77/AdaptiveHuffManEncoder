package nl.neurone.stream;

public interface IBitInputStream {
	boolean readBit();
	boolean isEOF();

	byte readByte();
	long readLong();

	void close();
}
