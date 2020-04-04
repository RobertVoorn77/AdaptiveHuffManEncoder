package nl.neurone.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class BitOutputStream implements IBitOutputStream {
    private final OutputStream outputStream;
    private final boolean[] buffer;
    private int count;

    public BitOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.buffer = new boolean[8];
        this.count = 0;
    }

    @Override
    public void writeBit(boolean bit) {
        this.count++;
        this.buffer[8-this.count] = bit;
        if (this.count == 8) {
            flushBitBuffer();
        }
    }

    private void flushBitBuffer() {
        int num = 0;
        for (int index = 0; index < 8; index++) {
            num = 2 * num + (this.buffer[index] ? 1 : 0);
        }

        try {
            this.outputStream.write(num - 128);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.count = 0;
    }

    private void writeByte(byte b) {
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
            writeByte(b);
        }
    }

    @Override
    public void close() {
        try {
            flushBitBuffer();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
