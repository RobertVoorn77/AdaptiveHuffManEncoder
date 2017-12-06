package nl.neurone.app;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import nl.neurone.component.CharacterDecoder;
import nl.neurone.stream.BitInputStreamFile;

public class CharacterFileDecoder {
	private CharacterDecoder decoder;
	private BufferedWriter os;
	private BitInputStreamFile bitInputStream;

	public static void main(String args[]) {
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		CharacterFileDecoder fileEncoder = new CharacterFileDecoder();
		fileEncoder.createBitInputStream(inputFileName);
		fileEncoder.decodeFile(outputFileName);
	}

	private void createBitInputStream(String inputFileName) {
		bitInputStream = new BitInputStreamFile(inputFileName);
		decoder = new CharacterDecoder(bitInputStream);
	}

	private void decodeFile(String fileName) {
		try {
			os = new BufferedWriter(new FileWriter(fileName));
			while (!bitInputStream.isEOF()) {
				char c = (char) decoder.decodeValue(bitInputStream);
				os.write(c);
				System.out.print(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
