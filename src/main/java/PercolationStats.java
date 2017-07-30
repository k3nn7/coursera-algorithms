import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] x;
    private int trials;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        x = new double[trials];

        for (int t = 0; t < trials; t++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                p.open(
                        StdRandom.uniform(n) + 1,
                        StdRandom.uniform(n) + 1
                ) ;
            }

            x[t] = (double)p.numberOfOpenSites() / (n * n);
        }

        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(trials);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        System.out.printf("mean                    = %f\n", stats.mean());
        System.out.printf("stddev                  = %f\n", stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}
