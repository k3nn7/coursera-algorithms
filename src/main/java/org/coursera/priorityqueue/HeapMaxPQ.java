package org.coursera.priorityqueue;

public class HeapMaxPQ<Key extends Comparable<Key>> {
    private int size = 0;
    private int lastIndex = 0;
    private Key[] data;

    public HeapMaxPQ() {
        data = (Key[])new Comparable[16];
    }

    public void insert(Key k) {
        size++;
        data[++lastIndex] = k;
        swim(lastIndex);
    }

    public Key delMax() {
        Key toReturn = getRoot();
        exch(1, lastIndex);
        size--;
        lastIndex--;
        sink(1);
        return toReturn;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Key getRoot() {
        return data[1];
    }

    private int getParentIndex(int node) {
        if (node > 1) return node / 2;
        return 1;
    }

    private int getLeftChildIndex(int node) {
        return node * 2;
    }

    private int getRightChildIndex(int node) {
        return node * 2 + 1;
    }

    private void swim(int index) {
        int k = index;
        while (k > 1 && less(getParentIndex(k), k)) {
            exch(getParentIndex(k), k);
            k = getParentIndex(k);
        }
    }

    private void sink(int index) {
        int i = index;
        while (2 * i <= lastIndex) {
            int j = getLeftChildIndex(i);
            if (j < lastIndex && less(getLeftChildIndex(i), getRightChildIndex(i))) j++;

            if (less(i, j)) {
                exch(i, j);
                i  = j;
            } else {
                break;
            }
        }
    }

    private boolean less (int a, int b) {
        return data[a].compareTo(data[b]) < 0;
    }

    private void exch(int a, int b) {
        Key tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
