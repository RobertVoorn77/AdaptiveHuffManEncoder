package nl.neurone.component;

import nl.neurone.stream.IBitInputStream;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DecoderNormalTest {

    @Test
    public void decode() {
        // given
        Map<String, Character> expectedEncoding = new HashMap<>();
        expectedEncoding.put("00000010", (char) 2);
        expectedEncoding.put("00000011", (char) 3);
        expectedEncoding.put("00000100", (char) 4);
//        expectedEncoding.put("110", (char) 2);
//        expectedEncoding.put("111", (char) 3);
//        expectedEncoding.put("00", (char) 4);

        // when & then
        for (Map.Entry<String, Character> entry : expectedEncoding.entrySet()) {
            final BitInputStreamTester inputStream = new BitInputStreamTester();
            DecoderNormal dec = new DecoderNormal(inputStream);
            inputStream.setBits(entry.getKey());
            Character decoded = dec.decode();
            System.out.println("entry.value: " + entry.getValue());
            assertEquals(entry.getValue(), decoded);
        }
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
            return 0;
        }

        @Override
        public void close() {
        }

        public void setBits(String bits) {
            this.bits = bits;
        }
    }
}