import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KdTreeTests {
    @Test
    public void isEmpty_ReturnsTrue_WhenEmpty() {
        // Arrange
        KdTree kdTree = new KdTree();
        List<Boolean> result = new ArrayList<>();
        // Act
        result.add(kdTree.isEmpty());
        result.add(kdTree.isEmpty());
        // Assert
        for (Boolean b : result) {
            assertTrue(b);
        }
    }

    @Test
    public void isEmpty_ReturnsFalse_WhenNotEmpty() {
        // Arrange
        KdTree kdTree = new KdTree();
        List<Boolean> result = new ArrayList<>();
        // Act
        kdTree.insert(new Point2D(0, 0));
        result.add(kdTree.isEmpty());
        result.add(kdTree.isEmpty());
        kdTree.insert(new Point2D(0, 0));
        result.add(kdTree.isEmpty());
        result.add(kdTree.isEmpty());
        // Assert
        for (Boolean b : result) {
            assertFalse(b);
        }
    }

    @Test
    public void size_ReturnsSize_WhenCalled() {
        // Arrange
        KdTree kdTree = new KdTree();
        List<Integer> result = new ArrayList<>();
        int insertCount = 20;
        // Act
        result.add(kdTree.size());
        for (int i = 1; i < insertCount; i++) {
            Point2D p = new Point2D(i*0.001, i*0.001);
            kdTree.insert(p);
            result.add(kdTree.size());
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
        KdTree kdTree = new KdTree();
        for (Point2D p : points) { kdTree.insert(p); }
        List<Set<Point2D>> result = new ArrayList<>();
        // Act
        for (RectHV rect : rects) {
            Set<Point2D> resultSet = new TreeSet<>();
            for (Point2D p : kdTree.range(rect)) {
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
        KdTree kdTree = new KdTree();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> kdTree.range(null));
    }

    @Test
    public void nearest_ReturnsNull_WhenEmpty() {
        // Arrange
        KdTree kdTree = new KdTree();
        // Act
        Point2D result = kdTree.nearest(new Point2D(0, 0));
        // Assert
        assertNull(result);
    }

    @Test
    public void nearest_Throws_WhenArgNull() {
        // Arrange
        KdTree kdTree = new KdTree();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> kdTree.nearest(null));
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
        KdTree kdTree = new KdTree();
        for (Point2D p : points) { kdTree.insert(p); }
        List<Point2D> result = new ArrayList<>();
        // Act
        for (Point2D queryPoint : queryPoints) {
            result.add(kdTree.nearest(queryPoint));
        }
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void nearest_ReturnsNearest_WhenCalled2() {
        // Arrange
        List<Point2D> points = Arrays.asList(
                new Point2D(0.372, 0.497),
                new Point2D(0.564, 0.413),
                new Point2D(0.226, 0.577),
                new Point2D(0.144, 0.179),
                new Point2D(0.083, 0.51),
                new Point2D(0.32, 0.708),
                new Point2D(0.417, 0.362),
                new Point2D(0.862, 0.825),
                new Point2D(0.785, 0.725),
                new Point2D(0.499, 0.208)
        );
        List<Point2D> queryPoints = Arrays.asList(
                new Point2D(0.6, 0.22)
        );
        List<Point2D> expected = Arrays.asList(
                new Point2D(0.499, 0.208)
        );
        KdTree kdTree = new KdTree();
        for (Point2D p : points) { kdTree.insert(p); }
        List<Point2D> result = new ArrayList<>();
        // Act
        for (Point2D queryPoint : queryPoints) {
            result.add(kdTree.nearest(queryPoint));
        }
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void insert_Throws_WhenArgNull() {
        // Arrange
        KdTree kdTree = new KdTree();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> kdTree.insert(null));
    }

    @Test
    public void contains_Throws_WhenArgNull() {
        // Arrange
        KdTree kdTree = new KdTree();
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> kdTree.contains(null));
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
        KdTree kdTree = new KdTree();
        for (Point2D p : points) { kdTree.insert(p); }
        List<Boolean> result = new ArrayList<>();
        // Act
        for (Point2D p : points) {
            result.add(kdTree.contains(p));
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
        KdTree kdTree = new KdTree();
        for (Point2D p : points) { kdTree.insert(p); }
        List<Point2D> queryPoints = Arrays.asList(
                new Point2D(0.1, 0.11),
                new Point2D(0.31, 0.34),
                new Point2D(0.999, 0.999),
                new Point2D(1, 1)
        );
        List<Boolean> result = new ArrayList<>();
        // Act
        for (Point2D p : queryPoints) {
            result.add(kdTree.contains(p));
        }
        // Assert
        for (Boolean contains : result) {
            assertFalse(contains);
        }
    }

    @Test
    public void contains_ReturnsFalse_WhenEmpty() {
        // Arrange
        KdTree kdTree = new KdTree();
        // Act
        boolean contains = kdTree.contains(new Point2D(0, 0));
        // Assert
        assertFalse(contains);
    }
}
