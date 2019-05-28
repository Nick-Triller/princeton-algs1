import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> set = new TreeSet<>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    public int size() {
        return this.set.size();
    }

    public void insert(Point2D p) {
        if (p == null) { throw new IllegalArgumentException(); }
        this.set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) { throw new IllegalArgumentException(); }
        return this.set.contains(p);
    }

    public void draw() {
        for (Point2D p : this.set) {
            StdDraw.filledCircle(p.x(), p.y(), 0.004d);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) { throw new IllegalArgumentException(); }
        List<Point2D> result = new ArrayList<>();
        for (Point2D p : this.set) {
            if (rect.contains(p)) {
                result.add(p);
            }
        }
        return result;
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) { throw new IllegalArgumentException(); }
        Point2D nearest = null;
        for (Point2D p : this.set) {
            if (nearest == null || queryPoint.distanceSquaredTo(p) < queryPoint.distanceSquaredTo(nearest)) {
                nearest = p;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.4, 0.4));
        pointSET.draw();
    }
}
