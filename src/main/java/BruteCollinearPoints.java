import java.util.LinkedList;

public class BruteCollinearPoints {
    private LinkedList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        segments = new LinkedList<>();

        for (int i = 0; i < points.length; i++) {
            Point a = points[i];
            if (a == null) throw new IllegalArgumentException();

            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;;
                Point b = points[j];
                if (b == null) throw new IllegalArgumentException();
                double aSlopeToB = a.slopeTo(b);
                int aCompareToB = a.compareTo(b);

                if (a != b && aCompareToB == 0) throw new IllegalArgumentException();

                for (int k = 0; k < points.length; k++) {
                    if (k == j || k == i) continue;;
                    Point c = points[k];
                    if (c == null) throw new IllegalArgumentException();
                    double bSlopeToC = b.slopeTo(c);
                    int bCompareToC = b.compareTo(c);
                    if (aSlopeToB != bSlopeToC) continue;

                    for (int l = 0; l < points.length; l++) {
                        if (l == k || l == j || l == i) continue;;
                        Point d = points[l];
                        if (d == null) throw new IllegalArgumentException();
                        double cSlopeToD = c.slopeTo(d);
                        int cCompareToD = c.compareTo(d);

                        boolean sameSlopes = aSlopeToB == bSlopeToC && bSlopeToC == cSlopeToD;
                        boolean sameDirection = aCompareToB > 0 && bCompareToC > 0 && cCompareToD > 0;
                        boolean notSamePoint = aSlopeToB > Double.NEGATIVE_INFINITY;

                        if (sameSlopes && sameDirection && notSamePoint) {
                            segments.add(new LineSegment(a, d));
                        }
                    }
                }
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