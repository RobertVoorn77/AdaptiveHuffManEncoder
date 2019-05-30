package nl.neurone.stream;

import java.io.IOException;
import java.io.InputStream;

/**
 * 'Borrowed' due to time constraints 
 * TODO: implement my own version and add unit tests for that 
 * from the Internet https://stackoverflow.com/questions/4220917/is-it-possible-to-read-write-bits-from-a-file-using-java
 *
 */
class BitInputStream implements AutoCloseable {

    private final InputStream in;
    private int num = 0;
    private int count = 8;

    public BitInputStream(InputStream in) {
        this.in = in;
    }

    public boolean read() throws IOException {
        if (this.count == 8){
            this.num = this.in.read() + 128;
            this.count = 0;
        }

        boolean x = (num%2 == 1);
        num /= 2;
        this.count++;

        return x;
    }

    public void close() throws IOException {
        this.in.close();
    }

}