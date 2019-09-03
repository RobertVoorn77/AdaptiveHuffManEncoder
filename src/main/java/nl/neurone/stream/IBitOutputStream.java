package nl.neurone.stream;

public interface IBitOutputStream {
	void writeBit(boolean bit);
	void reset();
	boolean isEOF();

	void writeByte(byte b);
	void writeLong(long l);

	void close();
}
