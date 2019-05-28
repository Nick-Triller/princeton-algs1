import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;


public class KdTree {
    private Node root;
    private int size = 0;

    public KdTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return this.size;
    }

    public void insert(Point2D p) {
        if (p == null) { throw new IllegalArgumentException(); }
        this.root = insert(this.root, null, p, 0, true);
    }

    private Node insert(Node n, Node parent, Point2D p, int level, boolean left) {
        if (n == null) {
            // Found insertion point
            this.size++;
            RectHV limits;
            if (parent == null) {
                limits = new RectHV(0, 0, 1, 1);
            } else {
                if ((level % 2 == 0)) {
                    // Previous was horizontal
                    double xmin = parent.limits.xmin();
                    double ymin = left ? parent.limits.ymin() : parent.point.y();
                    double xmax = parent.limits.xmax();
                    double ymax = left ? parent.point.y() : parent.limits.ymax();
                    limits = new RectHV(xmin, ymin, xmax, ymax);
                } else {
                    // Previous was vertical
                    double xmin = left ? parent.limits.xmin() : parent.point.x();
                    double ymin = parent.limits.ymin();
                    double xmax = left ? parent.point.x() : parent.limits.xmax();
                    double ymax = parent.limits.ymax();
                    limits = new RectHV(xmin, ymin, xmax, ymax);
                }
            }
            return new Node(p, level, limits);
        }
        if (p.equals(n.point)) {
            n.point = p;
            return n;
        }
        int cmp = n.compare(p);
        if (cmp > 0) {
            // Go left
            n.left = insert(n.left, n, p, ++level, true);
            return n;
        } else {
            // Go right
            n.right = insert(n.right, n, p, ++level, false);
            return n;
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) { throw new IllegalArgumentException(); }
        return contains(this.root, p);
    }

    private boolean contains(Node n, Point2D p) {
        if (n == null) return false;
        if (n.point.equals(p)) return true;
        int cmp = n.compare(p);
        if (cmp > 0) {
            // Go left
            return contains(n.left, p);
        } else {
            // Go right
            return contains(n.right, p);
        }
    }

    public void draw() {
        draw(this.root);
    }

    private void draw(Node n) {
        if (n == null) { return; }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(n.point.x(), n.point.y(), 0.005);
        if (n.isVertical()) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), n.limits.ymin(), n.point.x(), n.limits.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.limits.xmin(), n.point.y(), n.limits.xmax(), n.point.y());
        }
        draw(n.left);
        draw(n.right);
    }

    public Iterable<Point2D> range(RectHV target) {
        if (target == null) { throw new IllegalArgumentException(); }
        List<Point2D> result = new ArrayList<>();
        range(this.root, target, result);
        return result;
    }

    private void range(Node n, RectHV target, List<Point2D> collector) {
        if (n == null) { return; }
        // Prune search if subtree can't contain points that are in the target rectangle
        if (!target.intersects(n.limits)) { return; }
        if (target.contains(n.point)) { collector.add(n.point); }
        if (n.isVertical()) {
            range(n.left, target, collector);
            range(n.right, target, collector);
        } else {
            range(n.left, target, collector);
            range(n.right, target, collector);
        }
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) { throw new IllegalArgumentException(); }
        return nearest(this.root, queryPoint, null);
    }

    private Point2D nearest(Node n, Point2D queryPoint, Point2D nearest) {
        // No subtrees left
        if (n == null) { return nearest; }
        // Update nearest
        if (nearest == null || n.point.distanceSquaredTo(queryPoint) < nearest.distanceSquaredTo(queryPoint)) {
            nearest = n.point;
        }
        // Prune search if node can't contain closer point than nearest
        if (n.limits.distanceSquaredTo(queryPoint) >= queryPoint.distanceSquaredTo(nearest)) {
            return nearest;
        }
        // Explore towards query point first
        boolean leftFirst = n.isVertical() ? queryPoint.x() < n.point.x() : queryPoint.y() < n.point.y();
        Node first = leftFirst ? n.left : n.right;
        Node second = leftFirst ? n.right : n.left;
        nearest = nearest(first, queryPoint, nearest);
        nearest = nearest(second, queryPoint, nearest);
        return nearest;
    }

    // Can be static as it doesn't refer to generic key or value of outer
    // class (saves 8 byte inner class object overhead)
    private static class Node {
        private Point2D point;
        private Node left;
        private Node right;
        // Area that is split into two sections by the node
        private RectHV limits;
        private int level;

        public Node(Point2D p, int level, RectHV limits) {
            if (limits == null || p == null) {
                throw new IllegalArgumentException();
            }
            this.point = p;
            this.level = level;
            this.limits = limits;
        }

        public boolean isVertical() {
            return this.level % 2 == 0;
        }

        public int compare(Point2D other) {
            return isVertical() ?
                    Double.compare(point.x(), other.x()) :
                    Double.compare(point.y(), other.y());
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        for (int i = 0; i < 1000000; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
        }
        System.out.println(kdTree.range(new RectHV(0, 0, 0.002, 0.002)));
        System.out.println(kdTree.nearest(new Point2D(0.5, 0.5)));
    }
}
