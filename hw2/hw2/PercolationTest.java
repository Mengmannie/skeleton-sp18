package hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PercolationTest {
    Percolation percolation = new Percolation(4);

    @Test
    public void OpenTest (){
        percolation.open(0,1);
        percolation.open(1,1);
        assertTrue(percolation.isOpen(0,1));
        assertTrue(percolation.isOpen(1,1));

    }

    @Test
    public void NumberTest () {
        percolation.open(1,2);
        percolation.open(1,1);
        assertEquals(2,percolation.numberOfOpenSites());
        percolation.open(0,1);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(2,1);
        percolation.open(2,2);
        assertEquals(5,percolation.numberOfOpenSites());

    }

    @Test
    public void FullTest () {
        percolation.open(0,1);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(2,1);
        percolation.open(2,2);
        assertTrue(percolation.isOpen(1,1));
        assertTrue(percolation.isFull(2,2));
    }

    @Test
    public void PercolateTest () {
        percolation.open(0,1);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(2,1);
        percolation.open(2,2);
        percolation.open(3,2);
        assertTrue(percolation.percolates());
    }

}
