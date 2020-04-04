package nl.neurone.component;

import java.util.concurrent.TimeUnit;

public class PerformanceHelper {
    private long start;
    private long origsize;

    public PerformanceHelper(long origsize) {
        this.start = System.currentTimeMillis();
        this.origsize = origsize;
    }

    public void showPerformance(long compsize) {
        long end = System.currentTimeMillis();
        final long duration = end - start;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        System.out.println("\n\n\n--------------------");
        System.out.println("Duration: " + seconds + " sec");
        double origKB = (double)origsize / 1024;
        System.out.println("Speed: " + origKB / seconds + " kb/s");
        final double ratio = (double)compsize / (double)origsize;
        System.out.println("Ratio: " + ratio * 100.0);
    }
}
