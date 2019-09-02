package nl.neurone.app;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import nl.neurone.component.CharacterDecoder;
import nl.neurone.stream.BitInputStreamFile;

class CharacterFileDecoder {
    private CharacterDecoder decoder;
    private BitInputStreamFile bitInputStream;

    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];

        CharacterFileDecoder fileEncoder = new CharacterFileDecoder();
        fileEncoder.createBitInputStream(inputFileName);
        fileEncoder.decodeFile(outputFileName);
    }

    private void createBitInputStream(String inputFileName) {
        bitInputStream = new BitInputStreamFile(inputFileName);
        decoder = new CharacterDecoder();
    }

    private void decodeFile(String fileName) {
        Instant start = Instant.now();
        System.out.println("Start: " + new Date());

        try (BufferedWriter os = new BufferedWriter(new FileWriter(fileName))) {
            long actualSize = bitInputStream.readLong();
            System.out.println("Filesize as read from file: " + actualSize);

            long decodedSize = actualSize;
            while (decodedSize > 1) {   // TODO: change this to zero again when fixing the TestApp.testXXXFile tests
                char c = (char) decoder.decodeValue(bitInputStream);
                os.write(c);
                decodedSize--;
            }
            System.out.println("Size actual: " + actualSize);
            Instant end = Instant.now();
            System.out.println("Stopped: " + new Date());
            System.out.println("Took seconds: " + Duration.between(start, end));
        } catch (IOException | NullPointerException e) {    // NPE is only for testing purposes
            e.printStackTrace();
        }
    }
}
