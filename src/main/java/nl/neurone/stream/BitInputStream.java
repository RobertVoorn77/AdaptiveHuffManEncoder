package nl.neurone.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class BitInputStream implements IBitInputStream {
    private final InputStream inputStream;
    private int num;
    private int count;

    public BitInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.num = 0;
        this.count = 8;
    }

    @Override
    public boolean readBit() {
        if (this.count == 8){
            try {
                this.num = this.inputStream.read() + 128;
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.count = 0;
        }

        boolean x = (num % 2 == 1);
        num /= 2;
        this.count++;

        return x;
    }

    private byte readByte() {
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
            bytes[i] = readByte();
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
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
