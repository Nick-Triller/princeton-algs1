import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PointSETTests {
    @Test
    public void isEmpty_ReturnsTrue_WhenEmpty() {
        // Arrange
        PointSET pointSET = new PointSET();
        List<Boolean> result = new ArrayList<>();
        // Act
        result.add(pointSET.isEmpty());
        result.add(pointSET.isEmpty());
        // Assert
        for (Boolean b : result) {
            assertTrue(b);
        }
    }

    @Test
    public void isEmpty_ReturnsFalse_WhenNotEmpty() {
        // Arrange
        PointSET pointSET = new PointSET();
        List<Boolean> result = new ArrayList<>();
        // Act
        pointSET.insert(new Point2D(0, 0));
        result.add(pointSET.isEmpty());
        result.add(pointSET.isEmpty());
        pointSET.insert(new Point2D(0, 0));
        result.add(pointSET.isEmpty());
        result.add(pointSET.isEmpty());
        // Assert
        for (Boolean b : result) {
            assertFalse(b);
        }
    }

    @Test
    public void size_ReturnsSize_WhenCalled() {
        // Arrange
        PointSET pointSET = new PointSET();
        List<Integer> result = new ArrayList<>();
        int insertCount = 20;
        // Act
        result.add(pointSET.size());
        for (int i = 1; i < insertCount; i++) {
            Point2D p = new Point2D(i, i);
            pointSET.insert(p);
            result.add(pointSET.size());
        }
        // Assert
        for (int i = 0; i < insertCount; i++) {
            assertEquals(i, result.get(i));
        }
    }

    @Test
    public void range_ReturnsItems_WhenInRange() {
        // Arrange
        List<Point2D> points = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.2, 0.2),
                new Point2D(0.3, 0.3),
                new Point2D(0.4, 0.4),
                new Point2D(0.1, 0.2),
                new Point2D(0.9, 0.7)
        );
        List<RectHV> rects = Arrays.asList(
                new RectHV(0, 0, 0.15, 0.15),
                new RectHV(0, 0, 1, 1)
        );
        List<Set<Point2D>> expected = Arrays.asList(
                Stream.of(points.get(0)).collect(Collectors.toSet()),
                new HashSet<>(points)
        );
        PointSET pointSET = new PointSET();
        for (Point2D p : points) { pointSET.insert(p); }
        List<Set<Point2D>> result = new ArrayList<>();
        // Act
        for (RectHV rect : rects) {
            Set<Point2D> resultSet = new TreeSet<>();
            for (Point2D p : pointSET.range(rect)) {
                resultSet.add(p);
            }
            result.add(resultSet);
        }
        // Assert
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void range_Throws_WhenArgNull() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> pointSET.range(null));
    }

    @Test
    public void nearest_ReturnsNull_WhenEmpty() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        Point2D result = pointSET.nearest(new Point2D(0, 0));
        // Assert
        assertNull(result);
    }

    @Test
    public void nearest_Throws_WhenArgNull() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> pointSET.nearest(null));
    }

    @Test
    public void nearest_ReturnsNearest_WhenCalled() {
        // Arrange
        List<Point2D> points = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.2, 0.2),
                new Point2D(0.3, 0.3),
                new Point2D(0.4, 0.4),
                new Point2D(0.1, 0.2),
                new Point2D(0.9, 0.7)
        );
        List<Point2D> queryPoints = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.31, 0.34),
                new Point2D(0.999, 0.999),
                new Point2D(1, 1)
        );
        List<Point2D> expected = Arrays.asList(
                points.get(0),
                points.get(2),
                points.get(5),
                points.get(5)
        );
        PointSET pointSET = new PointSET();
        for (Point2D p : points) { pointSET.insert(p); }
        List<Point2D> result = new ArrayList<>();
        // Act
        for (Point2D queryPoint : queryPoints) {
            result.add(pointSET.nearest(queryPoint));
        }
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void insert_Throws_WhenArgNull() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> pointSET.insert(null));
    }

    @Test
    public void contains_Throws_WhenArgNull() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> pointSET.contains(null));
    }

    @Test
    public void contains_ReturnsTrue_WhenContains() {
        // Arrange
        List<Point2D> points = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.2, 0.2),
                new Point2D(0.3, 0.3),
                new Point2D(0.4, 0.4),
                new Point2D(0.1, 0.2),
                new Point2D(0.9, 0.7)
        );
        PointSET pointSET = new PointSET();
        for (Point2D p : points) { pointSET.insert(p); }
        List<Point2D> queryPoints = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.9, 0.7),
                new Point2D(0.3, 0.3),
                new Point2D(0.2, 0.2)
        );
        List<Boolean> result = new ArrayList<>();
        // Act
        for (Point2D p : queryPoints) {
            result.add(pointSET.contains(p));
        }
        // Assert
        for (Boolean contains : result) {
            assertTrue(contains);
        }
    }

    @Test
    public void contains_ReturnsFalse_WhenNotContains() {
        // Arrange
        List<Point2D> points = Arrays.asList(
                new Point2D(0.1, 0.1),
                new Point2D(0.2, 0.2),
                new Point2D(0.3, 0.3),
                new Point2D(0.4, 0.4),
                new Point2D(0.1, 0.2),
                new Point2D(0.9, 0.7)
        );
        PointSET pointSET = new PointSET();
        for (Point2D p : points) { pointSET.insert(p); }
        List<Point2D> queryPoints = Arrays.asList(
                new Point2D(0.1, 0.11),
                new Point2D(0.31, 0.34),
                new Point2D(0.999, 0.999),
                new Point2D(1, 1)
        );
        List<Boolean> result = new ArrayList<>();
        // Act
        for (Point2D p : queryPoints) {
            result.add(pointSET.contains(p));
        }
        // Assert
        for (Boolean contains : result) {
            assertFalse(contains);
        }
    }

    @Test
    public void contains_ReturnsFalse_WhenEmpty() {
        // Arrange
        PointSET pointSET = new PointSET();
        // Act
        boolean contains = pointSET.contains(new Point2D(0, 0));
        // Assert
        assertFalse(contains);
    }
}
