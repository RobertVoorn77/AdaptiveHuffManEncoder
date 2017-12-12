package nl.neurone.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import nl.neurone.component.CharacterEncoder;
import nl.neurone.stream.BitOutputStreamFile;

public class CharacterFileEncoder {
	private CharacterEncoder encoder;

	public static void main(String args[]) {
		String inputFileName = args[0];
		String outputFileName = args[1];
		CharacterFileEncoder fileEncoder = new CharacterFileEncoder();
		fileEncoder.createBitOutputStream(outputFileName);
		fileEncoder.encodeFile(inputFileName);
	}

	private void createBitOutputStream(String outputFileName) {
		BitOutputStreamFile bitOutputStream = new BitOutputStreamFile(outputFileName);
		encoder = new CharacterEncoder(bitOutputStream);
	}

	private void encodeFile(String fileName) {
		try (BufferedReader is = new BufferedReader(new FileReader(fileName))) {
			while (is.ready()) {
				char c = (char)is.read();
				encoder.encodeValue(c);
				System.out.print(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
