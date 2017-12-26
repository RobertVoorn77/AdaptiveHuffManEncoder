package nl.neurone.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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

    public long size() {
		try {
			return os.getChannel().size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void writeByte(byte b) {
        int mask = 1;
        // turn the signed 8 bit byte into a unsigned integer
        int bi = b - Byte.MIN_VALUE;
        for (int i = 0; i < 8; i++) {
            boolean bit = (bi & mask) > 0;
            writeBit(bit);
            mask *= 2;
        }
	}

	@Override
	public void writeLong(long l) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(l);
        for (int i = 0 ; i < Long.BYTES; i++) {
            byte b = buffer.get(i);
//            System.out.println("byte[" + i + "]=" + b);
            writeByte(b);
        }
    }

	@Override
	public byte readByte() {
        // should not be used for output stream
        throw new RuntimeException("You can not call readByte from an output stream, use BitInputStream instead");
	}

	@Override
	public long readLong() {
        // should not be used for output stream
        throw new RuntimeException("You can not call readLong from an output stream, use BitInputStream instead");
	}

	@Override
	public void close() {
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
