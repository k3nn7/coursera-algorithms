package org.coursera.sorting;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {
    @Test
    public void sort() {
        Integer[] before = {5, 2, 4, 1, 3};
        Integer[] after = {1, 2, 3, 4, 5};

        MergeSort.sort(before);

        assertArrayEquals(after, before);
    }
}
