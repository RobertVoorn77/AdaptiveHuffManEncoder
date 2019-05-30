package nl.neurone.app;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestApp {

    @Test
    public void testSmallFile() {
        final String inpF = "testData/testFileSmall.txt";
        final String outFile = "testData/testFileSmall.txt.robzip";
        final String reverseFile = "testData/testFileSmall.reverse";
        LocalDateTime start = LocalDateTime.now();
        CharacterFileEncoder.main(new String[] {inpF, outFile});
        CharacterFileDecoder.main(new String[] {outFile, reverseFile});
        LocalDateTime stop = LocalDateTime.now();
        Duration d = Duration.between(start, stop);
        System.out.println("Duration: " + d);
        assertFileEqual(inpF, reverseFile);
        try {
            Files.deleteIfExists(Paths.get(outFile));
            Files.deleteIfExists(Paths.get(reverseFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLargeFile() {
        final String inpF = "testData/testFile.txt";
        final String outFile = "testData/testFile.txt.robzip";
        final String reverseFile = "testData/testFile.reverse";
        LocalDateTime start = LocalDateTime.now();
        CharacterFileEncoder.main(new String[] {inpF, outFile});
        CharacterFileDecoder.main(new String[] {outFile, reverseFile});
        LocalDateTime stop = LocalDateTime.now();
        Duration d = Duration.between(start, stop);
        System.out.println("Duration: " + d);
        assertFileEqual(inpF, reverseFile);
        try {
            Files.deleteIfExists(Paths.get(outFile));
            Files.deleteIfExists(Paths.get(reverseFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assertFileEqual(String inpF, String reverseFile) {
        try {
            String expected = Files.readAllLines(Paths.get(inpF)).stream().reduce(String::concat).get();
            String actual = Files.readAllLines(Paths.get(reverseFile)).stream().reduce(String::concat).get();
            assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
