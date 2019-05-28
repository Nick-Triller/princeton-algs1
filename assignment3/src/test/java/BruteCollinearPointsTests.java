import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BruteCollinearPointsTests {
    @Test
    public void construct_ShouldThrow_WhenPointRepeated() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(1, 2)
        };
        // Act & assert
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
    }

    @Test
    public void construct_ShouldThrow_WhenPointsContainsNull() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 2),
                null,
                new Point(5, 7)
        };
        // Act & assert
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
    }

    @Test
    public void construct_ShouldThrow_WhenPointsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
    }

    @Test
    public void construct_ShouldNotFindSegments_WhenOnePoint() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 2),
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(0, brute.numberOfSegments());
    }

    @Test
    public void construct_ShouldNotFindSegments_WhenTwoPoints() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(5, 7)
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(0, brute.numberOfSegments());
    }

    @Test
    public void construct_ShouldNotFindSegments_WhenThreePoints() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(5, 7),
                new Point(5, 8),
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(0, brute.numberOfSegments());
    }

    @Test
    public void construct_ShouldFindSegment_WhenHorizontalSegment() {
        // Arrange
        Point[] points = new Point[]{
                new Point(5, 5),
                new Point(50, 5),
                new Point(10, 5),
                new Point(20, 5),
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(1, brute.numberOfSegments());
    }

    @Test
    public void construct_ShouldFindSegment_WhenVerticalSegment() {
        // Arrange
        Point[] points = new Point[]{
                new Point(50, -10),
                new Point(50, 0),
                new Point(50, 10),
                new Point(50, 20),
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(1, brute.numberOfSegments());
    }

    @Test
    public void construct_ShouldFindSegments_WhenInput40() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1000, 17000),
                new Point(14000, 24000),
                new Point(26000, 8000),
                new Point(10000, 28000),
                new Point(18000, 5000),
                new Point(1000, 27000),
                new Point(14000, 14000),
                new Point(11000, 16000),
                new Point(29000, 17000),
                new Point(5000, 21000),
                new Point(19000, 26000),
                new Point(28000, 21000),
                new Point(25000, 24000),
                new Point(30000, 10000),
                new Point(25000, 14000),
                new Point(31000, 16000),
                new Point(5000, 12000),
                new Point(1000, 31000),
                new Point(2000, 24000),
                new Point(13000, 17000),
                new Point(1000, 28000),
                new Point(14000, 16000),
                new Point(26000, 26000),
                new Point(10000, 31000),
                new Point(12000, 4000),
                new Point(9000, 24000),
                new Point(28000, 29000),
                new Point(12000, 20000),
                new Point(13000, 11000),
                new Point(4000, 26000),
                new Point(8000, 10000),
                new Point(15000, 12000),
                new Point(22000, 29000),
                new Point(7000, 15000),
                new Point(10000, 4000),
                new Point(2000, 29000),
                new Point(17000, 17000),
                new Point(3000, 15000),
                new Point(4000, 29000),
                new Point(19000, 2000),
        };
        // Act
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        // Assert
        assertEquals(4, brute.numberOfSegments());
    }
}
