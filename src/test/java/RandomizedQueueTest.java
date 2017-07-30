import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    private RandomizedQueue<Integer> r;

    @Before
    public void init() {
        r = new RandomizedQueue<>();
    }

    @Test
    public void shouldBeEmptyAfterInitialization() {
        assertTrue(r.isEmpty());
        assertEquals(0, r.size());
    }

    @Test
    public void shouldNotBeEmptyAfterEnqueue() {
        r.enqueue(10);

        assertFalse(r.isEmpty());
        assertNotEquals(0, r.size());
    }

    @Test
    public void enqueueAndDequeue() {
        r.enqueue(10);

        assertEquals(10, (int)r.dequeue());
    }

    @Test
    public void enqueueAndDequeueMany() {
        r.enqueue(10);
        r.enqueue(20);
        r.enqueue(30);

        r.dequeue();
        r.dequeue();
        r.dequeue();

        r.enqueue(55);
        assertEquals(55, (int)r.dequeue());
    }

    @Test
    public void sample() {
        r.enqueue(10);

        assertEquals(10, (int)r.sample());
        assertEquals(10, (int)r.sample());

        assertFalse(r.isEmpty());
    }

    @Test
    public void iterator() {
        r.enqueue(10);
        r.enqueue(20);
        r.enqueue(30);

        int itemsCount = 0;
        for (Integer item : r) {
            itemsCount++;
        }

        assertEquals(3, itemsCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenEnqueueNullItem() {
        r.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenDequeueEmptyQueue() {
        r.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenSampleFromEmptyQueue() {
        r.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwExceptionWhenRemoveFromIterator() {
        r.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenCallNextWhenNoNextItem() {
        r.iterator().next();
    }
}
