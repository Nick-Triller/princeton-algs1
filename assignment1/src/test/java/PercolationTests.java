import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PercolationTests {
    @Test
    public void numberOfOpenSites_ReturnsNumberOfOpenSites_WhenCalled() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        List<Integer> result = new ArrayList<>();
        // Act
        result.add(percolation.numberOfOpenSites());
        for (int y = 1; y <= size; y++) {
            for (int x = 1; x <= size; x++) {
                // Open one cell after the other
                percolation.open(y, x);
                result.add(percolation.numberOfOpenSites());
            }
        }
        // Assert
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i, result.get(i));
        }
    }

    @Test
    public void percolates_ReturnsTrue_WhenOpenTopToBottom() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        for (int y = 1; y <= size; y++) {
            percolation.open(y, 2);
        }
        // Act
        boolean percolates = percolation.percolates();
        // Assert
        assertTrue(percolates);
    }

    @Test
    public void percolates_ReturnsTrue_WhenOpenBottomToTop() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        for (int y = size; y > 0; y--) {
            percolation.open(y, 2);
        }
        // Act
        boolean percolates = percolation.percolates();
        // Assert
        assertTrue(percolates);
    }

    @Test
    public void percolates_ReturnsFalse_WhenNoPercolation() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        List<Boolean> result = new ArrayList<>();
        // Act
        result.add(percolation.percolates());
        for (int y = 1; y <= size; y++) {
            // Open sites diagonally
            percolation.open(y, y);
            result.add(percolation.percolates());
        }
        // Assert
        for (int i = 0; i < result.size(); i++) {
            assertFalse(result.get(i));
        }
    }

    @Test
    public void isFull_ReturnsTrue_WhenFull() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        List<Boolean> result = new ArrayList<>();
        // Act
        for (int y = 1; y <= size; y++) {
            int x = 1;
            percolation.open(y, x);
            result.add(percolation.isFull(y, x));
        }
        // Assert
        for (int i = 0; i < result.size(); i++) {
            assertTrue(result.get(i));
        }
    }

    @Test
    public void isFull_ReturnsFalse_WhenSiteClosed() {
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        List<Boolean> result = new ArrayList<>();
        List<int[]> sitesToOpen = Arrays.asList(
                new int[] {1, 1},
                new int[] {1, 2},
                new int[] {2, 3},
                new int[] {4, 8},
                new int[] {8, 4},
                new int[] {8, 3},
                new int[] {9, 1}
            );
        for (int[] xy : sitesToOpen) { percolation.open(xy[0], xy[1]); }
        // Act
        for (int y = 1; y <= size; y++) {
            for (int x = 1; x <= size; x++) {
                if (percolation.isOpen(y, x)) { continue; }
                result.add(percolation.isFull(y, x));
            }
        }
        // Assert
        for (int i = 0; i < result.size(); i++) {
            assertFalse(result.get(i));
        }
    }

    @Test
    public void isFull_ReturnsFalse_WhenConnectedToTopViaBottom() {
        // Ensure bottom does not bleed connection state
        // Arrange
        int size = 10;
        Percolation percolation = new Percolation(size);
        for (int i = 1; i <= size; i++) {
            percolation.open(i, 1);
        }
        // Act
        percolation.open(size, 5);
        boolean isFull = percolation.isFull(size, 5);
        // Assert
        assertFalse(isFull);
    }
}
