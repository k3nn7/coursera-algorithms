import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] openSites;
    private WeightedQuickUnionUF sites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        openSites = new boolean[n * n];
        sites = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        validateCoordinates(row, col);
        openSites[coordinateToIndex(row, col)] = true;

        if (row == 1) {
            sites.union(topSiteIndex(), coordinateToIndex(row, col) + 1);
        }

        if (row == n) {
            sites.union(bottomSiteIndex(), coordinateToIndex(row, col) + 1);
        }

        connectWithNeighbours(row, col);
    }

    public boolean isOpen(int row, int col) {
        validateCoordinates(row, col);
        return openSites[coordinateToIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validateCoordinates(row, col);
        return sites.connected(topSiteIndex(), coordinateToIndex(row, col) + 1);
    }

    public int numberOfOpenSites() {
        int openSitesCount = 0;
        for (boolean o : openSites) if (o) openSitesCount++;

        return openSitesCount;
    }

    public boolean percolates() {
        return sites.connected(topSiteIndex(), bottomSiteIndex());
    }

    private int coordinateToIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int topSiteIndex() {
        return 0;
    }

    private int bottomSiteIndex() {
        return n * n + 1;
    }

    private void connectWithNeighbours(int row, int col) {
        if (row > 1 && isOpen(row - 1, col)) {
            sites.union(coordinateToIndex(row - 1, col) + 1, coordinateToIndex(row, col) + 1);
        }

        if (row < n && isOpen(row + 1, col)) {
            sites.union(coordinateToIndex(row + 1, col) + 1, coordinateToIndex(row, col) + 1);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            sites.union(coordinateToIndex(row, col - 1) + 1, coordinateToIndex(row, col) + 1);
        }

        if (col < n && isOpen(row, col + 1)) {
            sites.union(coordinateToIndex(row, col + 1) + 1, coordinateToIndex(row, col) + 1);
        }
    }

    private void validateCoordinates(int row, int col) {
        if (row > n || row < 1) {
            throw new IllegalArgumentException();
        }

        if (col > n || col < 1) {
            throw new IllegalArgumentException();
        }
    }
}
