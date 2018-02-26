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

    public static void main(String args[]) {
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
        double actualSize = new File(fileName).length();
        System.out.println("Size actual: " + actualSize);
        double encodedSize = 0;
        System.out.println("Start: " + new Date());
        Instant start = Instant.now();
		bitOutputStream.writeLong((long) actualSize);

        try (BufferedReader is = new BufferedReader(new FileReader(fileName))) {

			while (is.ready()) {
				char c = (char)is.read();
				encoder.encodeValue(c);
//				System.out.print(c);
			}
            encodedSize = bitOutputStream.size();
            System.out.println("Size: " + encodedSize);
            Instant end = Instant.now();
            System.out.println("Stopped: " + new Date());
            System.out.println("Took seconds: " + Duration.between(start, end));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Ratio: " + (encodedSize / actualSize) * 100);  // 64,24% on testFile.txt --> Took seconds: PT56M28.696S
	}
}
