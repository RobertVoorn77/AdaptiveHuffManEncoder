package nl.neurone.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStreamFile implements IBitStream {
	private BitOutputStream bitOutputStream;
	private FileOutputStream os;
	
	public BitOutputStreamFile(String filename) {
		try {
			os = new FileOutputStream(new File(filename));
			bitOutputStream = new BitOutputStream(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean readBit() {
		// should not be used for output stream
		throw new RuntimeException("You can not call readBit from an output stream, use BitInputStream instead");
	}
	
	public void writeBit(boolean bit) {
		try {
			bitOutputStream.write(bit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		throw new RuntimeException("You can not call reset from on the output stream");
	}
	
	public boolean isEOF() {
		// should not be used for output stream
		throw new RuntimeException("You can not call isEOF from an output stream, use BitInputStream instead");
	}
}
