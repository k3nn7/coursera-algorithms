package org.coursera.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {
    private MyStack stack;

    @Before
    public void init() {
        stack = new ArrayStack();
    }

    @Test
    public void itShouldBeEmptyAfterInitialization() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void itShouldNotBeEmptyAfterPush() {
        stack.push("foo");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    public void itShouldBeEmptyAfterPushAndPop() {
        stack.push("abc");
        stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void pushAndPop() {
        String item = "foo";
        stack.push(item);

        assertEquals(item, stack.pop());
    }

    @Test
    public void isLIFO() {
        String item1 = "item1", item2 = "item2";

        stack.push(item1);
        stack.push(item2);

        assertEquals(item2, stack.pop());
        assertEquals(item1, stack.pop());
    }

    @Test(expected = UnderflowException.class)
    public void stackUnderflow() {
        stack.pop();
    }
}
