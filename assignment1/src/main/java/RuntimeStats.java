import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RuntimeStats {
    public static void main(String[] args) {
        int[] sizes = sequence(125, 100, 50);
        int[] trialCounts = {10, 20, 40};

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        for (int si = 0; si < sizes.length; si++) {
            for (int ti = 0; ti < trialCounts.length; ti++) {
                int n = sizes[si];
                int t = trialCounts[ti];
                executor.submit(() -> {
                    Stopwatch stopwatch = new Stopwatch();
                    new PercolationStats(n, t);
                    double elapsed = stopwatch.elapsedTime();
                    StdOut.println(String.format("%d\t%d\t%s", n, t, elapsed));
                });
            }
        }
    }

    private static int[] sequence(int size, int start, int step) {
        int[] seq = new int[size];
        for (int i = 0; i < size; i++) {
            seq[i] = start + i * step;
        }
        return seq;
    }
}
