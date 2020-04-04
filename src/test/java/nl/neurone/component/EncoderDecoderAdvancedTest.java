package nl.neurone.component;

import nl.neurone.stream.IBitInputStream;
import nl.neurone.stream.IBitOutputStream;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EncoderDecoderAdvancedTest {

    @Test
    public void encodeDecode() {
        // given
        BitInputOutputStreamTester inputOutputStream = new BitInputOutputStreamTester();
        EncoderAdvanced enc;
        DecoderAdvanced dec;
        final Random random = new Random();
        char[] randomChars = new char[100];
        for (int i = 0; i < randomChars.length; i++) {
            randomChars[i] = (char) random.nextInt(255);
        }

        // when
        for (char c : randomChars) {
            enc = new EncoderAdvanced(inputOutputStream);
            enc.encode(c);
        }

        // then
        for (char c : randomChars) {
            dec = new DecoderAdvanced(inputOutputStream);
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
            bits += ">>" + l + "<<";
        }

        @Override
        public boolean readBit() {
            char lastBit = bits.charAt(0);
            bits = bits.substring(1);
            return lastBit == '1';
        }

        @Override
        public long readLong() {
            if (bits.indexOf(">>") != 0) {
                fail("er wordt hier geen long verwacht");
            }
            int i = bits.indexOf("<<");
            String value = bits.substring(2, i);
            long l = Long.parseLong(value);
            char c = (char)l;
            bits = bits.substring(i + 2);
            return c;
        }

        @Override
        public void close() {
        }
    }
}