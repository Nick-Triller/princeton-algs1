import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        double[] trialResults = new double[trials];
        double sites = n * n;
        for (int t = 0; t < trials; t++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            trialResults[t] = percolation.numberOfOpenSites() / sites;
        }
        // Calculate stats
        this.mean = StdStats.mean(trialResults);
        this.stddev = StdStats.stddev(trialResults);
        double CONFIDENCE_95 = 1.96;
        this.confidenceLo = this.mean - CONFIDENCE_95 * this.stddev / Math.sqrt(trials);
        this.confidenceHi = this.mean + CONFIDENCE_95 * this.stddev / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        StdOut.print(new PercolationStats(n, t).toString());
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stddev;
    }

    public double confidenceLo() {
        return this.confidenceLo;
    }

    public double confidenceHi() {
        return this.confidenceHi;
    }

    public String toString() {
        String fmtStr = "Mean: %f\nStddev: %f\nConfidence: [%f; %f]\n";
        return String.format(fmtStr, mean, stddev, confidenceLo, confidenceHi);
    }
}
