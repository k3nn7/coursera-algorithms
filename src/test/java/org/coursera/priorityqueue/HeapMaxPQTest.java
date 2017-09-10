package org.coursera.priorityqueue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeapMaxPQTest {
    private HeapMaxPQ<Integer> pq;

    @Before
    public void setUp() {
        pq = new HeapMaxPQ<>();
    }

    @Test
    public void shouldBeEmptyAfterInitialization() {
        assertTrue(pq.isEmpty());
    }

    @Test
    public void shouldNotBeEmptyAfterInsertingElement() {
        pq.insert(10);
        assertFalse(pq.isEmpty());
    }

    @Test
    public void shouldReturnElementsInSortedOrder() {
        pq.insert(10);
        pq.insert(50);
        pq.insert(20);
        pq.insert(40);
        pq.insert(30);

        assertEquals(50, (int)pq.delMax());
        assertEquals(40, (int)pq.delMax());
        assertEquals(30, (int)pq.delMax());
        assertEquals(20, (int)pq.delMax());
        assertEquals(10, (int)pq.delMax());
    }
}
