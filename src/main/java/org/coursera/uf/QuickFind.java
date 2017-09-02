package org.coursera.uf;

public class QuickFind implements UnionFind {
    private int[] data;

    public QuickFind(int n) {
        data = new int[n];
        for (int i = 0; i < n; i++) data[i] = i;
    }

    @Override
    public void union(int p, int q) {
        int pComponent = data[p];
        int qComponent = data[q];

        for (int i = 0; i < data.length; i++)
            if (data[i] == qComponent) data[i] = pComponent;
    }

    @Override
    public boolean connected(int p, int q) {
        return data[p] == data[q];
    }
}
