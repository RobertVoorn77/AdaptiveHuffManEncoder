package nl.neurone.stream;

public interface IBitOutputStream extends AutoCloseable {
	void writeBit(boolean bit);
	void writeLong(long l);
	void close();
}
