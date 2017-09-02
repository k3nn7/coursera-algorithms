package org.coursera.uf;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickUnionTest {
    private UnionFind unionFind;

    @Before
    public void setup() {
        unionFind = new QuickUnion(10);
    }

    @Test
    public void unionAndCheckConnection() {
        assertFalse(unionFind.connected(0, 1));
        assertFalse(unionFind.connected(2, 4));
        assertFalse(unionFind.connected(3, 8));
        assertFalse(unionFind.connected(7, 9));

        unionFind.union(0, 1);
        unionFind.union(3, 8);

        assertTrue(unionFind.connected(0, 1));
        assertTrue(unionFind.connected(3, 8));
    }
}
