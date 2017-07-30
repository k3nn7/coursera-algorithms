import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {
    Percolation p;

    @Before
    public void init() {
        p = new Percolation(6);
    }

    @Test
    public void itShouldNotPercolateAfterInitialization() {
        assertFalse(p.percolates());
    }

    @Test
    public void itShouldHaveNoOpenSitesAfterInitialization() {
        assertEquals(0, p.numberOfOpenSites());
    }

    @Test
    public void openClosedSites() {
        assertFalse(p.isOpen(5, 5));
        assertFalse(p.isOpen(2, 5));
        assertFalse(p.isOpen(6, 1));

        p.open(5, 5);
        p.open(2, 5);
        p.open(6, 1);

        assertTrue(p.isOpen(5, 5));
        assertTrue(p.isOpen(2, 5));
        assertTrue(p.isOpen(6, 1));

        assertEquals(3, p.numberOfOpenSites());
    }

    @Test
    public void topRowIsFullAfterOpen() {
        assertFalse(p.isFull(1, 1));
        assertFalse(p.isFull(1, 6));

        p.open(1, 1);
        p.open(1, 6);

        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(1, 6));
    }

    @Test
    public void rowsBelowTopAreEmptyAfterOpen() {
        assertFalse(p.isFull(2, 1));
        assertFalse(p.isFull(3, 6));

        p.open(2, 1);
        p.open(3, 6);

        assertFalse(p.isFull(2, 1));
        assertFalse(p.isFull(3, 6));
    }

    @Test
    public void neighboursOfFullSiteGetsFull() {
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 2);
        p.open(3, 1);
        p.open(2, 1);

        assertTrue(p.isFull(2, 1));
    }

    @Test
    public void shouldPercolateWhenTopIsConnectedWithTheBottom() {
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(4, 3);
        p.open(5, 3);

        assertFalse(p.percolates());

        p.open(6, 3);

        assertTrue(p.percolates());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenConstructWithZeroSize() {
        new Percolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenConstructWithNegativeSize() {
        new Percolation(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionOnOpenForInvalidCoordinates() {
        p.open(10, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionOnIsOpenForInvalidCoordinates() {
        p.isOpen(10, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionOnIsFullForInvalidCoordinates() {
        p.isFull(10, -4);
    }
}
