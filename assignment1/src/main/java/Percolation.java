import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     * Tracks cell connections.
     */
    private final WeightedQuickUnionUF wquf;
    /**
     * siteStatus tracks which cells are open (for each cell) and which component is connected to
     * top and bottom (tracked for root of a given wquf component). Bit 1 denotes closed (0) and
     * open (1). Bit 2 denotes connected to top (1) or not (0). Bit 3 denotes connected to top (1)
     * or not (0).
     */
    private final byte[] siteStatus;
    private final int size;

    private int numberOfOpenSites = 0;
    private boolean percolates = false;

    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.siteStatus = new byte[size * size];
        // last two nodes are artificial nodes (top and bottom)
        this.wquf = new WeightedQuickUnionUF(size * size + 2);
    }

    public void open(int row, int col) {
        if (!isValidCell(row, col)) {
            throw new IllegalArgumentException();
        }
        int cellIndex = coordsToIndex(row, col);
        // Do nothing if already open
        if (isOpen(cellIndex)) {
            return;
        }

        // Open the cell
        this.siteStatus[cellIndex] |= 1;
        this.numberOfOpenSites++;

        byte newConnectionStatus = 1;
        if (row == 1) {
            // Set second bit, connected to top, to 1
            newConnectionStatus |= (1 << 2);
        }
        if (row == size) {
            // Set thrid bit, connected to bottom, to 1
            newConnectionStatus |= (1 << 3);
        }

        // Process neighbors
        int[] neighbors = {
                row - 1, col, // above
                row + 1, col, // below
                row, col - 1, // left
                row, col + 1  // right
        };
        for (int i = 0; i < 4; i++) {
            // Use coordinate pairs
            int neighborRow = neighbors[i * 2];
            int neighborCol = neighbors[i * 2 + 1];
            newConnectionStatus |= processNeighbor(cellIndex, neighborRow, neighborCol);
        }

        // Update connection status for root of new component
        int root = this.wquf.find(cellIndex);
        this.siteStatus[root] |= newConnectionStatus;

        if (isConnectedToTop(root) && isConnectedToBottom(root)) {
            this.percolates = true;
        }
    }

    private int processNeighbor(int cellIndex, int neighborRow, int neighborColumn) {
        // Ignore out of range and closed neighbors
        int neighborIndex = coordsToIndex(neighborRow, neighborColumn);
        if (!isValidCell(neighborRow, neighborColumn) || !isOpen(neighborIndex)) {
            return 0;
        }
        // Remember original status before union
        int status = this.siteStatus[this.wquf.find(neighborIndex)];
        this.wquf.union(cellIndex, neighborIndex);
        return status;
    }

    public boolean isOpen(int row, int col) {
        if (!isValidCell(row, col)) {
            throw new IllegalArgumentException();
        }
        int cellIndex = coordsToIndex(row, col);
        return isOpen(cellIndex);
    }

    private boolean isOpen(int index) {
        return (this.siteStatus[index] & 1) != 0;
    }

    public boolean isFull(int row, int col) {
        if (!isValidCell(row, col)) {
            throw new IllegalArgumentException();
        }
        int cellIndex = this.coordsToIndex(row, col);
        int root = this.wquf.find(cellIndex);
        return isConnectedToTop(root);
    }

    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    public boolean percolates() {
        return this.percolates;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 1 && row <= this.size && col >= 1 && col <= this.size;
    }

    private int coordsToIndex(int row, int col) {
        return (row - 1) * this.size + col - 1;
    }

    private boolean isConnectedToTop(int rootIndex) {
        return (this.siteStatus[rootIndex] & (1 << 2)) != 0;
    }

    private boolean isConnectedToBottom(int rootIndex) {
        return (this.siteStatus[rootIndex] & (1 << 3)) != 0;
    }
}
