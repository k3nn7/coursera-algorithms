package org.coursera.uf;

import java.util.Random;

public class PerformanceTest {
    public static void main(String[] args) {
        measureQuickUnionPerformace();
    }

    private static void measureQuickFindPerformace() {
        measurePerformance(new QuickFind(8000), 8000);
        measurePerformance(new QuickFind(16000), 16000);
        measurePerformance(new QuickFind(32000), 32000);
        measurePerformance(new QuickFind(64000), 64000);
        measurePerformance(new QuickFind(128000), 128000);
    }

    private static void measureQuickUnionPerformace() {
        measurePerformance(new QuickUnion(8000), 8000);
        measurePerformance(new QuickUnion(16000), 16000);
        measurePerformance(new QuickUnion(32000), 32000);
        measurePerformance(new QuickUnion(64000), 64000);
        measurePerformance(new QuickUnion(128000), 128000);
    }

    private static void measurePerformance(UnionFind unionFind, int n) {
        Random random = new Random();
        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);

            if (!unionFind.connected(p, q)) unionFind.union(p, q);
        }

        long elapsed = System.currentTimeMillis() - start;

        System.out.printf("Elapsed time for %d elements: %d\n", n, elapsed);
    }
}
