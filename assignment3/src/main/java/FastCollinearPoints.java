import java.util.*;

public class FastCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    public FastCollinearPoints(Point[] points) {
        if (points == null || hasNull(points)) throw new IllegalArgumentException();
        // Do not modify input array
        Point[] copy = points.clone();
        // Sort for easier duplicate detection
        Arrays.sort(copy);
        if (hasDuplicate(copy)) throw new IllegalArgumentException();
        findCollinear(copy);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private void findCollinear(Point[] points) {
        List<LineSegment> foundSegments = new ArrayList<>();
        Point[] bySlope = points.clone();

        for (Point pivot : points) {
            Arrays.sort(bySlope, pivot.slopeOrder());
            // x = 0 is the pivot because x.slopeTo(x) == Double.NEGATIVE_INFINITY
            int x = 1;
            while (x < bySlope.length) {
                double currentSlope = pivot.slopeTo(bySlope[x]);
                LinkedList<Point> collinear = new LinkedList<>();
                do collinear.add(bySlope[x++]);
                while (x < bySlope.length && pivot.slopeTo(bySlope[x]) == currentSlope);
                Collections.sort(collinear);
                if (collinear.size() >= 3 && pivot.compareTo(collinear.peek()) < 0) {
                    foundSegments.add(new LineSegment(pivot, collinear.getLast()));
                }
            }
        }
        this.segments = foundSegments.toArray(this.segments);
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
