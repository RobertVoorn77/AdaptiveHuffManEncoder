package nl.neurone.stream;

import java.io.IOException;
import java.io.OutputStream;

/**
 *  'Borrowed' due to time constraints 
 *  TODO: implement my own version and add unit tests for that 
 *  from the Internet https://stackoverflow.com/questions/4220917/is-it-possible-to-read-write-bits-from-a-file-using-java
 *
 */
class BitOutputStream implements AutoCloseable {

    private final OutputStream out;
    private final boolean[] buffer = new boolean[8];
    private int count = 0;

    public BitOutputStream(OutputStream out) {
        this.out = out;
    }

    public void write(boolean x) throws IOException {
        this.count++;
        this.buffer[8-this.count] = x;
        if (this.count == 8){
            int num = 0;
            for (int index = 0; index < 8; index++){
                num = 2*num + (this.buffer[index] ? 1 : 0);
            }

            this.out.write(num - 128);

            this.count = 0;
        }
    }

    public void close() throws IOException {
        int num = 0;
        for (int index = 0; index < 8; index++){
            num = 2*num + (this.buffer[index] ? 1 : 0);
        }

        this.out.write(num - 128);

        this.out.close();
    }

}