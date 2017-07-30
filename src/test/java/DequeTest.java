import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {
    private Deque<Integer> d;

    @Before
    public void init() {
        d = new Deque<>();
    }

    @Test
    public void isEmptyAfterInitialization() {
        assertTrue(d.isEmpty());
        assertEquals(0, d.size());
    }

    @Test
    public void notEmptyAfterAddFirst() {
        d.addFirst(10);

        assertFalse(d.isEmpty());
        assertNotEquals(0, d.size());
    }

    @Test
    public void notEmptyAfterAddLast() {
        d.addLast(10);

        assertFalse(d.isEmpty());
        assertNotEquals(0, d.size());
    }

    @Test
    public void emptyAfterAddAndRemove() {
        d.addFirst(10);
        d.removeFirst();

        assertTrue(d.isEmpty());

        d.addLast(10);;
        d.removeLast();

        assertTrue(d.isEmpty());
    }

    @Test
    public void addFirstAndRemoveFirst() {
        d.addFirst(10);
        d.addFirst(20);

        assertEquals(20, (int)d.removeFirst());
        assertEquals(10, (int)d.removeFirst());
    }

    @Test
    public void addLastAndRemoveLast() {
        d.addLast(10);
        d.addLast(20);

        assertEquals(20, (int)d.removeLast());
        assertEquals(10, (int)d.removeLast());
    }

    @Test
    public void addFirstRemoveLast() {
        d.addFirst(10);
        d.addFirst(20);
        d.addFirst(30);

        assertEquals(10, (int)d.removeLast());
        assertEquals(20, (int)d.removeLast());
        assertEquals(30, (int)d.removeLast());
    }

    @Test
    public void addLastRemoveFirst() {
        d.addLast(10);
        d.addLast(20);
        d.addLast(30);

        assertEquals(10, (int)d.removeFirst());
        assertEquals(20, (int)d.removeFirst());
        assertEquals(30, (int)d.removeFirst());
    }

    @Test
    public void addAndRemove() {
        d.addFirst(10);
        d.addLast(-10);
        d.addFirst(20);
        d.addLast(-20);

        assertEquals(20, (int)d.removeFirst());
        assertEquals(10, (int)d.removeFirst());
        assertEquals(-10, (int)d.removeFirst());
        assertEquals(-20, (int)d.removeFirst());
    }

    @Test
    public void removeAllTwice() {
        d.addFirst(10);
        d.addLast(-10);

        d.removeFirst();
        d.removeFirst();

        assertTrue(d.isEmpty());

        d.addLast(-1);
        d.addFirst(1);

        assertEquals(-1, (int)d.removeLast());
        assertEquals(1, (int)d.removeLast());

        assertTrue(d.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddFirstNullItem() {
        d.addFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddLastNullItem() {
        d.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void canNotRemoveFirstFromEmptyDeque() {
        d.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void canNotRemoveLastFromEmptyDeque() {
        d.removeLast();
    }

    @Test
    public void iterator() {
        int[] expectedItems = {20, 10};
        d.addFirst(10);
        d.addFirst(20);

        int i = 0;
        for (Integer item : d) {
            assertEquals(expectedItems[i], (int)item);
            i++;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorShouldThrowExceptionWhenHasNoMoreElements() {
        d.iterator().next();
    }
}
