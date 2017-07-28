package org.coursera.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListQueueTest {
    private MyQueue queue;

    @Before
    public void init() {
        queue = new LinkedListQueue();
    }

    @Test
    public void itShouldBeEmptyAfterInitialization() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void itShouldNotBeEmptyAfterPush() {
        queue.enqueue("foo");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    public void itShouldBeEmptyAfterPushAndPop() {
        queue.enqueue("abc");
        queue.dequeue();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void enqueueAndDequeue() {
        String item = "foo";
        queue.enqueue(item);

        assertEquals(item, queue.dequeue());

        queue.enqueue("bar");
        assertEquals("bar", queue.dequeue());
    }

    @Test
    public void isFIFO() {
        String item1 = "item1", item2 = "item2";

        queue.enqueue(item1);
        queue.enqueue(item2);

        assertEquals(item1, queue.dequeue());
        assertEquals(item2, queue.dequeue());
    }

    @Test(expected = UnderflowException.class)
    public void queueUnderflow() {
        queue.dequeue();
    }
}
