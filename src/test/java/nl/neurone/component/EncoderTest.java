package nl.neurone.component;

import nl.neurone.stream.IBitOutputStream;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EncoderTest {

    @Test
    public void encode_simpleHuffmanTree() {
        // given
        Map<Character, String> expectedEncoding = new HashMap<>();
        expectedEncoding.put((char) 2, "00000010");
        expectedEncoding.put((char) 3, "00000011");
        expectedEncoding.put((char) 4, "00000100");
//        expectedEncoding.put((char) 2, "110");
//        expectedEncoding.put((char) 3, "111");
//        expectedEncoding.put((char) 4, "00");

        // when & then
        for (Map.Entry<Character, String> entry : expectedEncoding.entrySet()) {
            final BitOutputStreamTester outputStream = new BitOutputStreamTester();
            Encoder enc = new Encoder(outputStream);
            enc.encode(entry.getKey());
            String encodedBits = outputStream.getBits();
            assertEquals(entry.getValue(), encodedBits);
        }
    }

    static class BitOutputStreamTester implements IBitOutputStream {
        private String bits = "";

        @Override
        public void writeBit(boolean bit) {
            bits += bit ? "1" : "0";
        }

        @Override
        public void writeLong(long l) {
        }

        @Override
        public void close() {
        }

        public String getBits() {
            return bits;
        }
    }
}