package org.coursera.sorting;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {
    @Test
    public void sort() {
        Integer[] before = {3, 5, 1, 4, 2};
        Integer[] after = {1, 2, 3, 4, 5};

        QuickSort.sort(before);

        assertArrayEquals(after, before);
    }
}
