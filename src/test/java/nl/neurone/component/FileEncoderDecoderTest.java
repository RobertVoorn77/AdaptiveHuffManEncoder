package nl.neurone.component;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class FileEncoderDecoderTest {

    @Ignore
    public void testLargeFile() {
        String origFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.txt";
        String compFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.robzip";
        String outFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFile.out";
        try {
            new FileEncoderSimple(origFile, compFile);
        new FileDecoderSimple(compFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void testSmallFile() {
        String origFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.txt";
        String compFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.robzip";
        String outFile = "C:\\Users\\Roba\\IdeaProjects\\AdaptiveHuffManEncoderFromGithub\\testData\\testFileSmall.out";
        try {
            new FileEncoderSimple(origFile, compFile);
            new FileDecoderSimple(compFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
