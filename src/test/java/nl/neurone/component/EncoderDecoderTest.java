package nl.neurone.component;

import nl.neurone.stream.IBitInputStream;
import nl.neurone.stream.IBitOutputStream;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class EncoderDecoderTest {

    @Test
    public void encodeDecode() {
        // given
        BitInputOutputStreamTester inputOutputStream = new BitInputOutputStreamTester();
        Encoder enc;
        Decoder dec;
        final Random random = new Random();
        char[] randomChars = new char[100];
        for (int i = 0; i < randomChars.length; i++) {
            randomChars[i] = (char) random.nextInt(255);
        }

        // when
        for (char c : randomChars) {
            enc = new Encoder(inputOutputStream);
            enc.encode(c);
        }

        // then
        for (char c : randomChars) {
            dec = new Decoder(inputOutputStream);
            assertEquals(c, dec.decode());
        }
    }

    static class BitInputOutputStreamTester implements IBitOutputStream, IBitInputStream {
        private String bits = "";

        @Override
        public void writeBit(boolean bit) {
            bits += bit ? "1" : "0";
        }

        @Override
        public void writeLong(long l) {
        }

        @Override
        public boolean readBit() {
            char lastBit = bits.charAt(0);
            bits = bits.substring(1);
            return lastBit == '1';
        }

        @Override
        public long readLong() {
            return 0;
        }

        @Override
        public void close() {
        }
    }
}