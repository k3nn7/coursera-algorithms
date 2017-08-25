import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void compareTo() {
        Point origin = new Point(0, 0);

        assertEquals(0, origin.compareTo(origin));
        assertEquals(0, origin.compareTo(new Point(0, 0)));
        assertEquals(1, origin.compareTo(new Point(-1 ,-1)));
        assertEquals(-1, origin.compareTo(new Point(1, 1)));
        assertEquals(1, origin.compareTo(new Point(-1, 0)));
        assertEquals(-1, origin.compareTo(new Point(1, 0)));
        assertEquals(1, origin.compareTo(new Point(0, -1)));
    }

    @Test
    public void slopeTo() {
        Point origin = new Point(0, 0);

        assertEquals(Double.NEGATIVE_INFINITY, origin.slopeTo(origin), 0.0);;
        assertEquals(Double.NEGATIVE_INFINITY, origin.slopeTo(new Point(0, 0)), 0.0);
        assertEquals(1.0, origin.slopeTo(new Point(1, 1)), 0.0);
        assertEquals(0.5, origin.slopeTo(new Point(2, 1)), 0.0);
        assertEquals(1.0, origin.slopeTo(new Point(-1, -1)), 0.0);
        assertEquals(0.0 / Double.POSITIVE_INFINITY, origin.slopeTo(new Point(1, 0)), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, origin.slopeTo(new Point(0, 1)), 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void slopeToNull() {
        Point origin = new Point(0, 0);
        origin.slopeTo(null);
    }

    @Test
    public void slopeOrder() {
        Comparator<Point> slopeOrder = (new Point(0, 0)).slopeOrder();

        assertEquals(1, slopeOrder.compare(new Point(1 ,1), new Point(2, 1)));
        assertEquals(-1, slopeOrder.compare(new Point(2 ,1), new Point(1, 1)));
        assertEquals(0, slopeOrder.compare(new Point(1 ,1), new Point(1, 1)));
    }
}
