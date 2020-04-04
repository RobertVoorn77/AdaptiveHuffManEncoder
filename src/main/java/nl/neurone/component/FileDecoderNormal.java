package nl.neurone.component;

import nl.neurone.stream.BitInputStream;

import java.io.*;
import java.nio.charset.Charset;

public class FileDecoderNormal {

    public FileDecoderNormal(String inputFilename, String outputFilename) throws IOException {
        Charset encoding = Charset.defaultCharset();
        final File inputFile = new File(inputFilename);
        BitInputStream bitInputStream = new BitInputStream(new FileInputStream(inputFile));
        DecoderNormal dec = new DecoderNormal(bitInputStream);
        long fileSize = bitInputStream.readLong();
        PerformanceHelper helper = new PerformanceHelper(fileSize);
        FileOutputStream outputStream = new FileOutputStream(new File(outputFilename));
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, encoding);
        while (fileSize > 0) {
            char c = dec.decode();
            writer.write(c);
//            System.out.print(c);
            fileSize--;
        }
        writer.close();
        helper.showPerformance(inputFile.length());
    }

    public static void main(String[] args) {
        try {
            new FileDecoderNormal(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
