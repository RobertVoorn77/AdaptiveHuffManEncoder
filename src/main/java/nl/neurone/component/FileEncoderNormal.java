package nl.neurone.component;

import nl.neurone.stream.BitOutputStream;

import java.io.*;
import java.nio.charset.Charset;

public class FileEncoderNormal {
    private EncoderNormal enc;

    public FileEncoderNormal(String inputFileName, String outputFilename) throws IOException {
        Charset encoding = Charset.defaultCharset();
        final File outputFile = new File(outputFilename);
        final BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(outputFile));
        enc = new EncoderNormal(outputStream);
        File inputFile = new File(inputFileName);
        long fileSize = inputFile.length();
        PerformanceHelper helper = new PerformanceHelper(fileSize);
        outputStream.writeLong(fileSize);
        try (InputStream in = new FileInputStream(inputFile);
             Reader reader = new InputStreamReader(in, encoding);
             Reader buffer = new BufferedReader(reader)) {
                handleCharacters(buffer);
        }
        outputStream.close();
        helper.showPerformance(outputFile.length());
    }

    private void handleCharacters(Reader reader) throws IOException {
        int r;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            enc.encode(ch);
//            System.out.print(ch);
        }
    }

    public static void main(String[] args) {
        try {
            new FileEncoderNormal(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
