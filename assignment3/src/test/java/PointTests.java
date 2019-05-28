import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointTests {
    @Test
    public void slopeTo_ShouldReturnPositiveInfinity_WhenOnVerticalLine() {
        // Arrange
        Point p = new Point(0, 1);
        Point arg = new Point(0, 0);
        // Act
        double slope = p.slopeTo(arg);
        //
        assertEquals(Double.POSITIVE_INFINITY, slope);
    }

    @Test
    public void slopeTo_ShouldReturnNegativeInfinity_WhenEqual() {
        // Arrange
        Point p = new Point(5, 5);
        Point arg = new Point(5, 5);
        // Act
        double slope = p.slopeTo(arg);
        // Assert
        assertEquals(Double.NEGATIVE_INFINITY, slope);
    }

    @Test
    public void slopeTo_ShouldReturnPositiveZero_WhenHorizontal() {
        // Arrange
        Point p = new Point(0, 0);
        Point arg = new Point(2, 0);
        // Act
        double slope = p.slopeTo(arg);
        // Assert
        assertEquals(+0.0, slope);
    }

    @Test
    public void slopeTo_ShouldReturnSlope_ForGivenPoints() {
        // Arrange
        Point p = new Point(0, 0);
        Point arg = new Point(1, 1);
        // Act
        double slope = p.slopeTo(arg);
        // Assert
        assertEquals(1, slope);
    }

    @Test
    public void slopeTo_ShouldReturnSlope_ForGivenPoints2() {
        // Arrange
        Point p = new Point(1, 3);
        Point arg = new Point(2, 1);
        // Act
        double slope = p.slopeTo(arg);
        // Assert
        assertEquals(-2, slope);
    }

    @Test
    public void compareTo_ShouldReturnZero_WhenPointsEqual() {
        // Arrange
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        // Act
        int result = p1.compareTo(p2);
        // Assert
        assertEquals(0, result);
    }

    @Test
    public void compareTo_ShouldReturnNegative_WhenArgIsGreater() {
        // Arrange
        Point p = new Point(1, 1);
        Point arg = new Point(1, 2);
        // Act
        int result = p.compareTo(arg);
        // Assert
        assertTrue(result < 0);
    }

    @Test
    public void compareTo_ShouldReturnPositive_WhenArgIsLess() {
        // Arrange
        Point p = new Point(1, 1);
        Point arg = new Point(1, 0);
        // Act
        int result = p.compareTo(arg);
        // Assert
        assertTrue(result > 0);
    }

    @Test
    public void compareTo_ShouldReturnPositive_WhenSameYArgIsGreater() {
        // Arrange
        Point p = new Point(1, 1);
        Point arg = new Point(0, 1);
        // Act
        int result = p.compareTo(arg);
        // Assert
        assertTrue(result > 0);
    }

    @Test
    public void compareTo_ShouldReturnNegative_WhenSameYArgIsLess() {
        // Arrange
        Point p = new Point(0, 1);
        Point arg = new Point(1, 1);
        // Act
        int result = p.compareTo(arg);
        // Assert
        assertTrue(result < 0);
    }
}
