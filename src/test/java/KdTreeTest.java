import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KdTreeTest {
    KdTree set;

    @Before
    public void setUp() {
        set = new KdTree();
    }

    @Test
    public void shouldBeEmptyAfterConstruction() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void shouldNotBeEmptyAfterInsertingPoint() {
        set.insert(new Point2D(0.1, 0.1));
        assertFalse(set.isEmpty());
        assertEquals(1, set.size());
    }

    @Test
    public void shouldContainValidNumberOfPoints() {
        set.insert(new Point2D(0.1, 0.1));
        set.insert(new Point2D(0.9, 0.4));
        set.insert(new Point2D(0.2, 0.7));
        set.insert(new Point2D(0.6, 0.3));

        assertEquals(4, set.size());
    }

    @Test
    public void shouldCountTheSamePointOnce() {
        set.insert(new Point2D(0.1, 0.1));
        set.insert(new Point2D(0.3, 0.7));
        set.insert(new Point2D(0.1, 0.1));

        assertEquals(2, set.size());
    }

    @Test
    public void shouldContainExistingPoint() {
        set.insert(new Point2D(0.1, 0.1));
        assertTrue(set.contains(new Point2D(0.1, 0.1)));
        assertFalse(set.contains(new Point2D(0.5, 0.5)));
    }

    @Test
    public void shouldContainPointFrom5PointSet() {
        set.insert(new Point2D(0.875, 0));
        set.insert(new Point2D(0.25, 0.625));
        set.insert(new Point2D(0.125, 0.375));
        set.insert(new Point2D(1.0, 0.5));
        set.insert(new Point2D(0.375, 0.25));

        assertTrue(set.contains(new Point2D(0.375, 0.25)));
    }

    @Test
    public void shouldReturnPointsInsideGivenRectangle() {
        set.insert(new Point2D(0.1, 0.1));
        set.insert(new Point2D(0.4, 0.4));
        set.insert(new Point2D(0.3, 0.3));

        Point2D[] expectedPoints = {
                new Point2D(0.3, 0.3),
                new Point2D(0.4, 0.4)
        };

        List<Point2D> points = new ArrayList<>();
        for (Point2D p : set.range(new RectHV(0.2, 0.2, 0.4, 0.4))) {
            points.add(p);
        }

        assertArrayEquals(expectedPoints, points.toArray());
    }

    @Test
    public void shouldReturnNearestPoint() {
        set.insert(new Point2D(1.0, 1.0));
        set.insert(new Point2D(0.5, 0.5));
        set.insert(new Point2D(0.4, 0.4));
        set.insert(new Point2D(0.1, 0.1));

        assertEquals(
                new Point2D(0.4, 0.4),
                set.nearest(new Point2D(0.44, 0.44))
        );
    }

    @Test
    public void shouldReturnNearestPoint2() {
        set.insert(new Point2D(0.372, 0.497));
        set.insert(new Point2D(0.564, 0.413));
        set.insert(new Point2D(0.226, 0.577));
        set.insert(new Point2D(0.144, 0.179));
        set.insert(new Point2D(0.083, 0.51));
        set.insert(new Point2D(0.32, 0.708));
        set.insert(new Point2D(0.417, 0.362));
        set.insert(new Point2D(0.862, 0.825));
        set.insert(new Point2D(0.785, 0.725));
        set.insert(new Point2D(0.499, 0.208));

        assertEquals(
                new Point2D(0.083, 0.51),
                set.nearest(new Point2D(0.18, 0.4))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInsertNull() {
        set.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenContainsNullArgument() {
        set.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRangeNullArgument() {
        set.range(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNearestNullArgument() {
        set.nearest(null);
    }
}
