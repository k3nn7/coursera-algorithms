import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Stack;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (null == p) throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (null == p) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new IllegalArgumentException();

        Stack<Point2D> result = new Stack<>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                result.push(p);
            }
        }

        return result;
    }

    public Point2D nearest(Point2D p) {
        if (null == p) throw new IllegalArgumentException();

        double nearestDistance = Double.POSITIVE_INFINITY;
        Point2D nearest = null;

        for (Point2D neighbor : points) {
            double distance = p.distanceSquaredTo(neighbor);
            if (nearestDistance > distance) {
                nearest = neighbor;
                nearestDistance = distance;
            }
        }

        return nearest;
    }
}
