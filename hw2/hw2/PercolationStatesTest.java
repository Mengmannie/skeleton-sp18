package hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercolationStatesTest {
    PercolationStats percolationStats;
    PercolationFactory pf = new PercolationFactory();
    int N;
    int T;
     @Test
    public void meanTest () {
         N = 100;
         T = 40;
         percolationStats = new PercolationStats(N, T, pf);
         assertEquals(0.59,percolationStats.mean(),0.01);

     }
}
