package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF WU;
    private WeightedQuickUnionUF WUU;
    private int N;
    private int number;

    /**
     * create N by N grid, with all sites initially blocked (blocked '0'; open '1')
     *
     * @param N grid sizes
     */
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        WU = new WeightedQuickUnionUF(N * N + 2);
        WUU = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        number = 0;
    }

    public static void main(String[] args) {
    }

    private int position(int row, int col) {
        return row * N + col;
    }

    private int[] coordinates(int position) {
        int[] coordinates = new int[2];
        coordinates[0] = position / N;
        coordinates[1] = position % N;
        return coordinates;
    }

    /**
     * open the site (row, col) if it is not open
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            number++;
            int p = position(row, col);

            if (row == 0) {
                WU.union(p, N * N);
                WUU.connected(p, N * N);
            }

            if (row == N - 1) {
                WU.union(p, N * N + 1);
            }

            if ((col + 1) < N) {
                if (grid[row][col + 1] == 1) {
                    int q = position(row, col + 1);
                    WU.union(p, q);
                    WUU.union(p, q);
                }
            }

            if ((col - 1) >= 0) {
                if (grid[row][col - 1] == 1) {
                    int q = position(row, col - 1);
                    WU.union(p, q);
                    WUU.union(p, q);
                }
            }

            if ((row + 1) < N) {
                if (grid[row + 1][col] == 1) {
                    int q = position(row + 1, col);
                    WU.union(p, q);
                    WUU.union(p, q);
                }
            }

            if ((row - 1) >= 0) {
                if (grid[row - 1][col] == 1) {
                    int q = position(row - 1, col);
                    WU.union(p, q);
                    WUU.union(p, q);
                }
            }
        }
    }

    /**
     * is the site (row, col) open?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] == 1) return true;
        else return false;
    }

    /**
     * is the site (row, col) full? (full: an open site can be connected to an open site in the top row via chain)
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException();
        }

        int p = position(row, col);
        if (WUU.connected(p, N * N)) {
            return true;
        }
        return false;
//        if (isOpen(row, col)) {
//            int p = position(row, col);
//            for (int i = 0; i < N; i++) {
//                if (isOpen(0, i)) {
//                    int q = position(0, i);
//                    if (WU.connected(p, q)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
    }

    /**
     * number of open sites
     *
     * @return
     */
    public int numberOfOpenSites() {
        return number;
    }

    public boolean percolates() {
        if (WU.connected(N * N, N * N+ 1)) return true;
        return false;
    }

}
