package nl.neurone.stream;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class BitStreamTest {
    private Random random = new Random();
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testWriteAndReadBitsAndLongs() {
        // given
        boolean[] bits = initializeBits(3);
        long[] longs = initializeLongs(2);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        IBitOutputStream bitOutputStream = new BitOutputStream(byteOutputStream);

        // when
        for (boolean bit : bits) {
            bitOutputStream.writeBit(bit);
        }
        for (long l : longs) {
            bitOutputStream.writeLong(l);
        }
        for (int i = bits.length; i > 0; i--) {
            bitOutputStream.writeBit(bits[i - 1]);
        }
        // flush to byteArrayStream
        bitOutputStream.close();

        // then
        byte[] bytes = byteOutputStream.toByteArray();
        InputStream byteInputStream = new ByteArrayInputStream(bytes);
        try (IBitInputStream bitInputStream = new BitInputStream(byteInputStream)) {
            for (boolean bit : bits) {
                assertEquals(bit, bitInputStream.readBit());
            }
            for (long l : longs) {
                assertEquals(l, bitInputStream.readLong());
            }
            for (int i = bits.length; i > 0; i--) {
                assertEquals(bits[i - 1], bitInputStream.readBit());
            }
        }
    }

    @Test
    public void testClose() throws IOException {
        // given
        final InputStream inputStream = new FileInputStream("testData/testFile.txt");
        BitInputStream bitInputStream = new BitInputStream(inputStream);
        inputStream.close();

        // when
        bitInputStream.readLong();

        //then
        expectedException.expect(IOException.class);
    }

    private long[] initializeLongs(int amount) {
        long[] longs = new long[amount];
        for (int i = 0 ; i < amount; i++) {
            longs[i] = random.nextLong();
        }
        return longs;
    }

    private boolean[] initializeBits(int amount) {
        boolean[] bits = new boolean[amount];
        for (int i = 0; i<amount; i++) {
            bits[i] = random.nextBoolean();
        }
        return bits;
    }
}
