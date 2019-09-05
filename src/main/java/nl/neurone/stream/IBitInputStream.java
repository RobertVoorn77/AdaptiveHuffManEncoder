package nl.neurone.stream;

public interface IBitInputStream extends AutoCloseable {
	boolean readBit();
	long readLong();
	void close();
}
