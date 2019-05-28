import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] blocks;
    private final int N;

    /**
     * Construct a board
     *
     * @param blocks n-by-n array containing n*n integers between 0 and n*n − 1, where 0 represents the blank square.
     */
    public Board(int[][] blocks) {
        this.N = blocks.length;
        this.blocks = cloneBlocks(blocks);
    }

    /**
     * @return Board dimension n
     */
    public int dimension() {
        return this.N;
    }


    /**
     * The Hamming distance is the number of blocks in the wrong position.
     *
     * @return Number of blocks out of place
     */
    public int hamming() {
        int distance = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (this.blocks[y][x] == 0) continue;
                ;
                if (!isBlockCorrect(x, y)) distance++;
            }
        }
        return distance;
    }

    /**
     * The Manhattan distance is the sum of the vertical and horizontal distance.
     *
     * @return Sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int distance = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int block = this.blocks[y][x];
                if (block == 0) continue;
                ;
                int expectedY = (block - 1) / this.N;
                int expectedX = (block - 1) - this.N * expectedY;
                int distX = Math.abs(x - expectedX);
                int distY = Math.abs(y - expectedY);
                distance += distX + distY;
            }
        }
        return distance;
    }

    /**
     * @return Is this board the goal board?
     */
    public boolean isGoal() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!isBlockCorrect(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Creates a twin board with one pair of blocks exchanged. Note empty is not a block.
     *
     * @return A board that is obtained by exchanging any pairs of blocks
     */
    public Board twin() {
        int[][] twinBlocks = cloneBlocks(this.blocks);
        // Pick blocks to exchange (ensure not empty)
        int x1 = 0, y1 = 0;
        if (twinBlocks[y1][x1] == 0) y1 = 1;
        int x2 = 1, y2 = 0;
        if (twinBlocks[y2][x2] == 0) y2 = 1;
        exchangeBlocks(twinBlocks, x1, y1, x2, y2);
        return new Board(twinBlocks);
    }

    private boolean isBlockCorrect(int x, int y) {
        int block = blocks[y][x];
        if (block == 0) return true;
        return block == y * this.N + x + 1;
    }

    /**
     * Does this board equal y?
     *
     * @param o
     * @return True if equal
     */
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board b = (Board) o;
        return Arrays.deepEquals(this.blocks, b.blocks);
    }

    /**
     * Calculates all boards that can be reached with one move.
     *
     * @return All neighboring boards
     */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        // Find 0
        int zX = 0;
        int zY = 0;
        outerloop:
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (this.blocks[y][x] == 0) {
                    zX = x;
                    zY = y;
                    break outerloop;
                }
            }
        }
        // If not left border
        if ((zX) % this.N != 0) {
            int[][] newBlocks = cloneBlocks(blocks);
            exchangeBlocks(newBlocks, zX, zY, zX - 1, zY);
            neighbors.add(new Board(newBlocks));
        }
        // If not right border
        if ((zX + 1) % this.N != 0) {
            int[][] newBlocks = cloneBlocks(blocks);
            exchangeBlocks(newBlocks, zX, zY, zX + 1, zY);
            neighbors.add(new Board(newBlocks));
        }
        // If not top border
        if (zY != 0) {
            int[][] newBlocks = cloneBlocks(blocks);
            exchangeBlocks(newBlocks, zX, zY, zX, zY - 1);
            neighbors.add(new Board(newBlocks));
        }
        // If not bottom border
        if (zY < this.N - 1) {
            int[][] newBlocks = cloneBlocks(blocks);
            exchangeBlocks(newBlocks, zX, zY, zX, zY + 1);
            neighbors.add(new Board(newBlocks));
        }
        return neighbors;
    }

    /**
     * @return String representation
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.N);
        sb.append("\n");
        for (int y = 0; y < this.N; y++) {
            for (int x = 0; x < this.N; x++) {
                int block = this.blocks[y][x];
                if (block < 10) sb.append(" ");
                sb.append(block);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private int[][] cloneBlocks(int[][] blocks) {
        int[][] clone = blocks.clone();
        for (int y = 0; y < clone.length; y++) {
            clone[y] = clone[y].clone();
        }
        return clone;
    }

    private void exchangeBlocks(int[][] blocks, int x1, int y1, int x2, int y2) {
        int tmp = blocks[y1][x1];
        blocks[y1][x1] = blocks[y2][x2];
        blocks[y2][x2] = tmp;
    }
}
