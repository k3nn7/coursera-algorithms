package org.coursera.sorting;

public class QuickSort {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int pivot = partition(a, lo, hi);

        sort(a, lo, pivot - 1);
        sort(a, pivot + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        int pivot = lo;

        while (true) {
            while (a[i].compareTo(a[pivot]) < 0 && i < j) i++;
            while (a[j].compareTo(a[pivot]) > 0 && j >= i) j--;

            if (j <= i) break;

            exch(a, i, j);
        }

        exch(a, pivot, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
