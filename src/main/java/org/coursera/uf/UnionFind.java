package org.coursera.uf;

public interface UnionFind {
    void union(int p, int q);
    boolean connected(int p, int q);
}
