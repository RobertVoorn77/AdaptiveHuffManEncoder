package nl.neurone.component;

import nl.neurone.domain.SimpleHuffmanTree;
import nl.neurone.stream.BitInputStream;

import java.io.*;
import java.nio.charset.Charset;

public class FileDecoderSimple {

    public FileDecoderSimple(String inputFilename, String outputFilename) throws IOException {
        Charset encoding = Charset.defaultCharset();
        final File inputFile = new File(inputFilename);
        BitInputStream bitInputStream = new BitInputStream(new FileInputStream(inputFile));
        Decoder dec = new Decoder(bitInputStream, new SimpleHuffmanTree());
        long fileSize = bitInputStream.readLong();
        PerformanceHelper helper = new PerformanceHelper(fileSize);
        FileOutputStream outputStream = new FileOutputStream(new File(outputFilename));
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, encoding);
        while (fileSize > 0) {
            char c = dec.decode();
            writer.write(c);
            System.out.print(c);
            fileSize--;
        }
        writer.close();
        helper.showPerformance(inputFile.length());
    }

    public static void main(String[] args) {
        try {
            new FileDecoderSimple(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
