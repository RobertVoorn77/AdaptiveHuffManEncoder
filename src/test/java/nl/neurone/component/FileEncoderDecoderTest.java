package nl.neurone.component;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FileEncoderDecoderTest {

    @Ignore
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
        assertFilesAreIdentical(origFile, outFile);
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
        assertFilesAreIdentical(origFile, outFile);
    }

    private void assertFilesAreIdentical(String origFile, String outFile) {
        try {
            List<String> outResult = Files.readAllLines(Paths.get(outFile));
            List<String> origResult = Files.readAllLines(Paths.get(origFile));
            for (String line : origResult) {
                assertTrue(outResult.contains(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
