package nl.neurone.component;

import org.junit.Test;

import java.io.IOException;

public class FileEncoderDecoderTest {

    @Test
    public void testLargeFile() {
        String origFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.txt";
        String compFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.robzip";
        String outFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.out";
        try {
            new FileEncoder(origFile, compFile);
        new FileDecoder(compFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSmallFile() {
        String origFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.txt";
        String compFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.robzip";
        String outFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.out";
        try {
            new FileEncoder(origFile, compFile);
            new FileDecoder(compFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
