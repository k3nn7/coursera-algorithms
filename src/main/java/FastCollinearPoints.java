import java.util.*;

public class FastCollinearPoints {
    private LinkedList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        Point[] sortedPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[i] == null) throw new IllegalArgumentException();
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }

            sortedPoints[i] = points[i];
        }

        segments = new LinkedList<>();

        for (Point p : points) {
            Comparator<Point> comparator = p.slopeOrder();
            LinkedList<Point> collinearPoints = new LinkedList<>();
            Arrays.sort(sortedPoints, comparator);

            double slope = p.slopeTo(sortedPoints[0]);
            for (int i = 0; i < sortedPoints.length; i++) {
                double currentSlope = p.slopeTo(sortedPoints[i]);
                if (currentSlope != slope) {
                    if (collinearPoints.size() > 2) {
                        Point[] collinearArray = collinearPoints.toArray(new Point[collinearPoints.size()]);
                        Arrays.sort(collinearArray);
                        segments.add(new LineSegment(p, collinearArray[collinearArray.length - 1]));
                    }

                    collinearPoints.clear();
                    slope = currentSlope;
                }

                if (p.compareTo(sortedPoints[i]) < 0) collinearPoints.addLast(sortedPoints[i]);;
            }


            if (collinearPoints.size() > 2) {
                Point[] collinearArray = collinearPoints.toArray(new Point[collinearPoints.size()]);
                Arrays.sort(collinearArray);
                segments.add(new LineSegment(p, collinearArray[collinearArray.length - 1]));
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}