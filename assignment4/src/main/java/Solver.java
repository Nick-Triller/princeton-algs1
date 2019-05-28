import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private final DistanceType distanceType = DistanceType.MANHATTAN;
    private List<Board> solution = null;

    /**
     * Find a solution to the initial board using the A* algorithm
     *
     * @param initialBoard The start board.
     */
    public Solver(Board initialBoard) {
        if (initialBoard == null) throw new IllegalArgumentException();

        Comparator<SearchNode> comparator = getComparator();
        MinPQ<SearchNode> pq = new MinPQ<>(comparator);
        MinPQ<SearchNode> pqTwin = new MinPQ<>(comparator);

        SearchNode initialNode = new SearchNode(initialBoard, null, 0);
        pq.insert(initialNode);

        Board initialTwinBoard = initialBoard.twin();
        SearchNode initialNodeTwin = new SearchNode(initialTwinBoard, null, 0);
        pqTwin.insert(initialNodeTwin);

        while (true) {
            SearchNode solution = solveStep(pq);
            if (solution != null) {
                // Found solution
                List<Board> reversePath = new ArrayList<>();
                SearchNode current = solution;
                reversePath.add(current.board);
                while (current.predecessor != null) {
                    current = current.predecessor;
                    reversePath.add(current.board);
                }
                this.solution = new ArrayList<>();
                for (int i = reversePath.size() - 1; i >= 0; i--) {
                    this.solution.add(reversePath.get(i));
                }
                return;
            }
            solution = solveStep(pqTwin);
            if (solution != null) {
                // Given puzzle has no solution as twin has one
                return;
            }
        }
    }

    /**
     * Test client to read a puzzle from a file (specified as a command-line argument)
     *
     * @param args
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private SearchNode solveStep(MinPQ<SearchNode> pq) {
        SearchNode current = pq.delMin();
        if (current.isGoal) {
            // Found solution
            return current;
        }
        // Explore further moves
        for (Board neighbor : current.board.neighbors()) {
            // Do not re-enqueue predecessor state
            if (current.predecessor != null && neighbor.equals(current.predecessor.board)) {
                continue;
            }
            pq.insert(new SearchNode(neighbor, current, current.moves + 1));
        }
        return null;
    }

    /**
     * @return Is the initial board solvable?
     */
    public boolean isSolvable() {
        return this.solution != null;
    }

    /**
     * @return Min number of boards in a shortest solution; -1 if unsolvable
     */
    public int moves() {
        return solution == null ? -1 : solution.size() - 1;
    }

    /**
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return this.solution;
    }

    private Comparator<SearchNode> getComparator() {
        if (this.distanceType.equals(DistanceType.MANHATTAN)) {
            return new ManhattanPriorityComparator();
        }
        if (this.distanceType.equals(DistanceType.HAMMING)) {
            return new HammingPriorityComparator();
        }
        throw new RuntimeException();
    }

    private enum DistanceType {
        MANHATTAN, HAMMING
    }

    private class SearchNode {
        private final Board board;
        private final SearchNode predecessor;
        private final int moves;
        private final boolean isGoal;
        private final int distance;

        public SearchNode(Board board, SearchNode predecessor, int moves) {
            this.board = board;
            this.predecessor = predecessor;
            this.moves = moves;
            this.isGoal = board.isGoal();
            this.distance = getDistance(board);
        }

        private int getDistance(Board board) {
            if (distanceType.equals(DistanceType.MANHATTAN)) {
                return board.manhattan();
            }
            if (distanceType.equals(DistanceType.HAMMING)) {
                return board.hamming();
            }
            throw new RuntimeException();
        }
    }

    private class HammingPriorityComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            int priorityN1 = n1.moves + n1.distance;
            int priorityN2 = n2.moves + n2.distance;
            return Integer.compare(priorityN1, priorityN2);
        }
    }

    private class ManhattanPriorityComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            int priorityN1 = n1.moves + n1.distance;
            int priorityN2 = n2.moves + n2.distance;
            return Integer.compare(priorityN1, priorityN2);
        }
    }
}
