package nl.neurone.component;

import nl.neurone.stream.IBitOutputStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncoderAdvancedTest {

    @Test
    public void encode_firstElement() {
        // given
        BitOutputStreamTester outputStream = new BitOutputStreamTester();
        EncoderAdvanced enc = new EncoderAdvanced(outputStream);
        final char value = (char) 1;

        // when
        enc.encode(value);

        // then
        assertNotNull(enc.tree.getRoot());
        assertEquals(">>1<<", outputStream.getBits());
    }

    @Test
    public void encode_firstUnknownElement() {
        // given
        BitOutputStreamTester outputStream = new BitOutputStreamTester();
        EncoderAdvanced enc = new EncoderAdvanced(outputStream);
        enc.encode('a');

        // when
        enc.encode('b');

        // then
        assertNotNull(enc.tree.getRoot());
        assertEquals(">>97<<1>>98<<", outputStream.getBits());
    }

    @Test
    public void encode_firstKnownElement() {
        // given
        BitOutputStreamTester outputStream = new BitOutputStreamTester();
        EncoderAdvanced enc = new EncoderAdvanced(outputStream);
        enc.encode('a');
        enc.encode('b');

        // when
        enc.encode('b');

        // then
        assertNotNull(enc.tree.getRoot());
        assertEquals(">>97<<1>>98<<0", outputStream.getBits());
    }

    static class BitOutputStreamTester implements IBitOutputStream {
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
        public void close() {
        }

        public String getBits() {
            return bits;
        }
    }

}