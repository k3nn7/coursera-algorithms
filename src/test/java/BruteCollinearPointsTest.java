import org.junit.Test;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {
    @Test
    public void forNoSegments() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 1),
                new Point(1, 0)
        };

        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);

        assertEquals(0, collinearPoints.numberOfSegments());
        assertEquals(0, collinearPoints.segments().length);
    }

    @Test
    public void forOneSegment() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)
        };

        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);

        assertEquals(1, collinearPoints.numberOfSegments());
    }

    @Test
    public void forTwoSegments() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(2, 3),
                new Point(2, 1),
                new Point(2, 0),
        };

        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);

        assertEquals(2, collinearPoints.numberOfSegments());
    }

    @Test(expected = IllegalArgumentException.class)
    public void forNullConstructorArgument() {
        new BruteCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forNullPoint() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                null
        };

        new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forRepeatedPoint() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 0),
        };

        new BruteCollinearPoints(points);
    }
}
