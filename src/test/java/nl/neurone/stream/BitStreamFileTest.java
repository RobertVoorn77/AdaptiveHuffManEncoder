package nl.neurone.stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitStreamFileTest {
    private BitInputStreamFile bInput;
    private BitOutputStreamFile bOutput;

    @Test
    public void writeByteTest() {
        // when
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            // write byte
            bOutput = new BitOutputStreamFile("./testData/testBytes.dat");
            bOutput.writeByte(b);
            bOutput.close();
            // read byte
            bInput = new BitInputStreamFile("./testData/testBytes.dat");
            byte actualByte = bInput.readByte();
            bInput.close();
            // then assert
            assertEquals(b, actualByte);
        }
    }

    @Test
    public void writeLongTest() {
        // when
        for (long l = Long.MIN_VALUE; l < Long.MAX_VALUE; l++) {
            // write long
            bOutput = new BitOutputStreamFile("./testData/testBytes.dat");
            bOutput.writeLong(l);
            bOutput.close();
            // read long
            bInput = new BitInputStreamFile("./testData/testBytes.dat");
            long actualByte = bInput.readLong();
            bInput.close();
            // then assert
            assertEquals(l, actualByte);
        }
    }
}
