package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] sitesNumber;
    private int T;

    /**
     * perform T independent experiments on an N-by-N grid
     *
     * @param N
     * @param T
     * @param pf Percolation object
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        sitesNumber = new double[T];
        for (int i = 0; i < T; i++) {
//            p.initial();
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            sitesNumber[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }


    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(sitesNumber);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        return StdStats.stddev(sitesNumber);
    }

    /**
     * low endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }


    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
