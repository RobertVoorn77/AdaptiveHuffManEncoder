package nl.neurone.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

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
		// should not be used for input stream
		throw new RuntimeException("You can not call writeBit from an input stream, use BitOutputStream instead");
	}
	
	public void reset() {
		try {
			is.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** TODO: make a some rigorous test cases for this method
	 * works for my current small test files, but implementation does certainly not work for all files
	 * as it does not take the all the bits into account (the bits in the last byte could be skipped and the file terminated too early 
	 */
	public boolean isEOF() {
		try {
			return is.available() == 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long size() {
		try {
			return is.getChannel().size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void writeByte(byte b) {
		// should not be used for input stream
		throw new RuntimeException("You can not call writeByte from an input stream, use BitOutputStream instead");
	}

	@Override
	public void writeLong(long l) {
		// should not be used for input stream
		throw new RuntimeException("You can not call writeLong from an input stream, use BitOutputStream instead");
	}

	@Override
	public byte readByte() {
		int result = 0;
		int mask = 1;
		for (int i = 0; i < 8; i++) {
			boolean bit = readBit();
			if (bit) {
				result += mask;
			}
			mask *= 2;
		}

		return (byte) (result + Byte.MIN_VALUE);
	}

	@Override
	public long readLong() {
		byte[] bytes = new byte[Long.BYTES];
		for (int i = 0; i < Long.BYTES; i++) {
			byte b = readByte();
//			System.out.println("read byte[" + i + "]=" + b);
			bytes[i] = b;
		}
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        // not sure why, but according to some article on stack overflow, this needs a flip!
        buffer.flip();
		return buffer.getLong();
	}

    @Override
    public void close() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
