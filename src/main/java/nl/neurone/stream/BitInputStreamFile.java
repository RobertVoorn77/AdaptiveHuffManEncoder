package nl.neurone.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BitInputStreamFile implements IBitStream {
	private BitInputStream bitInputStream;
	private FileInputStream is;
	
	public BitInputStreamFile(String filename) {
		try {
			is = new FileInputStream(new File(filename));
			bitInputStream = new BitInputStream(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean readBit() {
		try {
			return bitInputStream.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void writeBit(boolean bit) {
		// should not be used for output stream
		throw new RuntimeException("You can not call writeBit from an output stream, use BitOutputStream instead");
	}
	
	public void reset() {
		try {
			is.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: make a test case for this method (works for small files, but certainly not for all files as it does not take the bits into account)
	public boolean isEOF() {
		try {
			return is.available() == 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
