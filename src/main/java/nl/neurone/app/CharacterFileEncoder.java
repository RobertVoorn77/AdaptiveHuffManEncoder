package nl.neurone.app;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import nl.neurone.component.CharacterEncoder;
import nl.neurone.stream.BitOutputStreamFile;

class CharacterFileEncoder {
    private CharacterEncoder encoder;
    private BitOutputStreamFile bitOutputStream;

    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];
        CharacterFileEncoder fileEncoder = new CharacterFileEncoder();
        fileEncoder.createBitOutputStream(outputFileName);
        fileEncoder.encodeFile(inputFileName);
    }

    private void createBitOutputStream(String outputFileName) {
        bitOutputStream = new BitOutputStreamFile(outputFileName);
        encoder = new CharacterEncoder(bitOutputStream);
    }

    private void encodeFile(String fileName) {
        double actualSize = scanActualSize(new File(fileName));
        long counter = 0;
        System.out.println("Started encoding file: " + fileName);
        System.out.println("Size actual: " + actualSize);
        System.out.println("Size from filesystem: " + new File(fileName).length());

        double encodedSize = 0;
        int quarterSize = (int) (encodedSize / 4);
        System.out.println("Start: " + new Date());
        Instant start = Instant.now();
        bitOutputStream.writeLong((long) actualSize);

        try (BufferedReader is = new BufferedReader(new FileReader(fileName));
            BufferedWriter os = new BufferedWriter((new FileWriter(fileName + ".copy")))) {

            int decounter = (int) actualSize;
            while (decounter-- > 0) {
                char c = (char)is.read();
                encoder.encodeValue(c);
                counter++;
                os.write(c);
            }
            encodedSize = bitOutputStream.size();
            System.out.println("Size: " + encodedSize);
            Instant end = Instant.now();
            System.out.println("Stopped: " + new Date());
            System.out.println("Took seconds: " + Duration.between(start, end));
            System.out.println("Difference in length: " + (actualSize - counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Ratio: %.2f", (encodedSize / actualSize) * 100.0));  // 64,24% on testFile.txt --> Took seconds: PT56M28.696S
    }

    private double scanActualSize(File file) {
        double size = 0.0;
        try (BufferedReader is = new BufferedReader(new FileReader(file))) {

            while (is.ready()) {
                is.read();
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size;
    }
}
