package org.coursera.sorting;

public class MergeSort {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = (hi - lo) / 2 + lo;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);

        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) aux[i] = a[i];

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) < 0) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }
}
