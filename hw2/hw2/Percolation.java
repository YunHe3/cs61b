package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int bound;
    private WeightedQuickUnionUF model;
    private int blocked[][];
    private int openCount;
    private void validate(int n) {
        if (n >= bound || n < 0) {
            throw new IndexOutOfBoundsException("parameter out of range.");
        }
    }
    // try to connect the new open grid with those already opened.
    private void connect(int row, int col) {
        int loc = row * bound + col;
        int upper = (row - 1) * bound + col;
        int lower = (row + 1) * bound + col;
        int right = row * bound + col + 1;
        int left = row * bound + col - 1;
        // try connect
        if (row != 0 && isOpen(row - 1, col)) {
            model.union(loc, upper);
        }
        if (row != bound - 1 && isOpen(row + 1, col)) {
            model.union(loc, lower);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            model.union(loc, left);
        }
        if (col != bound - 1 && isOpen(row, col + 1)) {
            model.union(loc, right);
        }
    }
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        // argument check
        if (N <= 0) {
            throw new IllegalArgumentException("Can't create a model whose row and col less then 1.");
        }
        bound = N;
        model = new WeightedQuickUnionUF(N*N);
        blocked = new int[N][N]; // 0 for blocked, 1 for opened
        openCount = 0;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (isOpen(row, col)) return;
        blocked[row][col] = 1;
        openCount++;
        connect(row, col);
    }
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return blocked[row][col] == 1;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        int loc = row * bound + col;
        for (int i = 0; i < bound; i++) {
            if (model.connected(loc, i)) {
                return true;
            }
        }
        return false;
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return openCount;
    }          // number of open sites
    public boolean percolates() {
        for (int i = 0; i < bound; i++) {
            if (isFull(bound - 1, i)) return true;
        }
        return false;
    }             // does the system percolate?
    public static void main(String[] args) {
        Percolation p = new Percolation(100);
        System.out.println(p.percolates());
        for (int i = 0; i < 100; i++) p.open(i, 0);
        System.out.println(p.percolates());
    }  // use for unit testing (not required)
}
