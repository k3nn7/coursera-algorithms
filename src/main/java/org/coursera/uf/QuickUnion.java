package org.coursera.uf;

public class QuickUnion implements UnionFind {
    private int[] data;
    private int[] sizes;

    public QuickUnion(int n) {
        data = new int[n];
        sizes = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = i;
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);

        if (sizes[pRoot] < sizes[qRoot]) {
            data[qRoot] = data[pRoot];
            sizes[qRoot]++;
        } else {
            data[pRoot] = data[qRoot];
            sizes[pRoot]++;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        if (data[p] == p) return p;

        return root(data[p]);
    }
}
