import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Stack;

public class KdTree {
    private Node root = null;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    public void insert(Point2D p) {
        if (null == p) throw new IllegalArgumentException();

        root = put(root, p, 0);
    }

    public boolean contains(Point2D p) {
        if (null == p) throw new IllegalArgumentException();

        Node x = root;
        int level = 0;
        while (x != null) {
            if (p.equals(x.point)) return true;
            if (isEven(level)) {
                if (p.x() >= x.point.x()) {
                    x = x.topRight;
                } else {
                    x = x.bottomLeft;
                }
            } else {
                if (p.y() >= x.point.y()) {
                    x = x.topRight;
                } else {
                    x = x.bottomLeft;
                }
            }
            level++;
        }

        return false;
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new IllegalArgumentException();

        Stack<Point2D> result = new Stack<>();

        rangeSearch(rect, root, 0, result);

        return result;
    }

    private void rangeSearch(RectHV rect, Node n, int level, Stack<Point2D> results) {
        if (n == null) return;

        if (isEven(level)) {
            if (rect.xmin() < n.point.x()) {
                rangeSearch(rect, n.bottomLeft, level + 1, results);
            }
            if (rect.xmax() >= n.point.x()) {
                rangeSearch(rect, n.topRight, level + 1, results);
            }
        } else {
           if (rect.ymin() < n.point.y()) {
               rangeSearch(rect, n.bottomLeft, level + 1, results);
           }
           if (rect.ymax() >= n.point.y()) {
               rangeSearch(rect, n.topRight, level + 1, results);
           }
        }

        if (rect.contains(n.point)) results.push(n.point);
    }

    public Point2D nearest(Point2D p) {
        if (null == p) throw new IllegalArgumentException();

        return nearest(p, root, 0);
    }

    private Point2D nearest(Point2D p, Node n, int level) {
        double nearestDistance = n.point.distanceSquaredTo(p);
        Point2D nearestPoint = n.point;

        if (isEven(level)) { // horizontal split
            Point2D closestAlternative = new Point2D(n.point.x(), p.y());
            double closestPossibleDistance = closestAlternative.distanceSquaredTo(p);

            if (p.x() < n.point.x()) {
                if (n.bottomLeft != null) {
                    Point2D child = nearest(p, n.bottomLeft, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestDistance = childDistance;
                        nearestPoint = child;
                    }
                }

                if (nearestDistance > closestPossibleDistance && n.topRight != null) {
                    Point2D child = nearest(p, n.topRight, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestPoint = child;
                    }
                }
            } else {
                if (n.topRight != null) {
                    Point2D child = nearest(p, n.topRight, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestDistance = childDistance;
                        nearestPoint = child;
                    }
                }

                if (nearestDistance > closestPossibleDistance && n.bottomLeft != null) {
                    Point2D child = nearest(p, n.bottomLeft, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestPoint = child;
                    }
                }
            }

        } else { // vertical split
            Point2D closestAlternative = new Point2D(p.x(), n.point.y());
            double closestPossibleDistance = closestAlternative.distanceSquaredTo(p);

            if (p.y() < n.point.y()) {
                if (n.bottomLeft != null) {
                    Point2D child = nearest(p, n.bottomLeft, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestDistance = childDistance;
                        nearestPoint = child;
                    }
                }

                if (nearestDistance > closestPossibleDistance && n.topRight != null) {
                    Point2D child = nearest(p, n.topRight, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestPoint = child;
                    }
                }
            } else {
                if (n.topRight != null) {
                    Point2D child = nearest(p, n.topRight, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestDistance = childDistance;
                        nearestPoint = child;
                    }
                }

                if (nearestDistance > closestPossibleDistance && n.bottomLeft != null) {
                    Point2D child = nearest(p, n.bottomLeft, level + 1);
                    double childDistance = child.distanceSquaredTo(p);
                    if (childDistance < nearestDistance) {
                        nearestPoint = child;
                    }
                }
            }

        }

        return nearestPoint;
    }

    private Node put(Node n, Point2D point, int level) {
        if (null == n) return new Node(point);

        if (point.equals(n.point)) return n;

        if (isEven(level)) {
            if (point.x() < n.point.x()) {
                n.bottomLeft = put(n.bottomLeft, point, level + 1);
            } else {
                n.topRight = put(n.topRight, point, level + 1);
            }
        } else {
            if (point.y() < n.point.y()) {
                n.bottomLeft = put(n.bottomLeft, point, level + 1);
            } else {
                n.topRight = put(n.topRight, point, level + 1);
            }
        }

        n.count = 1 + size(n.topRight) + size(n.bottomLeft);

        return n;
    }

    private class Node {
        Node bottomLeft;
        Node topRight;
        Point2D point;
        int count = 1;

        Node(Point2D point) {
            this.point = point;
            bottomLeft = null;
            topRight = null;
        }
    }

    private static boolean isEven(int n) {
        return (n % 2) == 0;
    }

    private static int size(Node n) {
        if (n == null) return 0;

        return n.count;
    }
}
