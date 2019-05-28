import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    public BruteCollinearPoints(Point[] points) {
        if (points == null || hasNull(points)) throw new IllegalArgumentException();
        // Do not modify input array
        Point[] pointsCopy = Arrays.<Point>copyOf(points, points.length);
        // Sort for easier duplicate detection
        Arrays.sort(pointsCopy);
        if (hasDuplicate(pointsCopy)) throw new IllegalArgumentException();
        findCollinear(pointsCopy);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private void findCollinear(Point[] points) {
        List<LineSegment> foundSegments = new ArrayList<>();
        if (points.length < 4) return;
        for (int a = 0; a < points.length - 3; a++) {
            for (int b = a + 1; b < points.length - 2; b++) {
                for (int c = b + 1; c < points.length - 1; c++) {
                    for (int d = c + 1; d < points.length; d++) {
                        if (areCollinear(points[a], points[b], points[c], points[d])) {
                            foundSegments.add(new LineSegment(points[a], points[d]));
                        }
                    }
                }
            }
        }
        this.segments = foundSegments.toArray(this.segments);
    }

    private boolean areCollinear(Point a, Point b, Point c, Point d) {
        return a.slopeTo(b) == b.slopeTo(c) && b.slopeTo(c) == c.slopeTo(d);
    }

    /**
     * Determines if an array of points contains null values.
     *
     * @param points An array of points.
     * @return True if the array contains null.
     */
    private boolean hasNull(Point[] points) {
        for (Point p : points) {
            if (p == null) return true;
        }
        return false;
    }

    /**
     * Determines if an sorted array of points contains a duplicate.
     *
     * @param points Sorted array of points.
     * @return True if a duplicate has been found.
     */
    private boolean hasDuplicate(Point[] points) {
        if (points.length == 0 || points.length == 1) {
            return false;
        }
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) return true;
        }
        return false;
    }
}
