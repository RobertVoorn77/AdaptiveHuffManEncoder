package nl.neurone.component;

import nl.neurone.stream.IBitInputStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecoderAdvancedTest {

    @Test
    public void decode_firstElement() {
        // given
        BitInputStreamTester inputsStream = new BitInputStreamTester();
        inputsStream.setBits(">>97<<");
        DecoderAdvanced dec = new DecoderAdvanced(inputsStream);

        // when
        char firstElement = dec.decode();

        // then
        assertNotNull(dec.tree.getRoot());
        assertEquals('a', firstElement);
    }

    @Test
    public void encode_firstUnknownElement() {
        // given
        BitInputStreamTester inputsStream = new BitInputStreamTester();
        inputsStream.setBits(">>97<<1>>98<<");
        DecoderAdvanced dec = new DecoderAdvanced(inputsStream);
        assertEquals('a', dec.decode());

        // when
        char element = dec.decode();

        // then
        assertNotNull(dec.tree.getRoot());
        assertEquals('b', element);
    }

    @Test
    public void decode_firstKnownElement() {
        // given
        BitInputStreamTester inputStream = new BitInputStreamTester();
        inputStream.setBits(">>97<<1>>98<<0");
        DecoderAdvanced dec = new DecoderAdvanced(inputStream);
        assertEquals('a', dec.decode());
        assertEquals('b', dec.decode());

        // when
        char element = dec.decode();

        // then
        assertNotNull(dec.tree.getRoot());
        assertEquals('b', element);
    }

    static class BitInputStreamTester implements IBitInputStream {
        private String bits;

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

        public void setBits(String bits) {
            this.bits = bits;
        }
    }
}