package org.coursera.datastructures;

public interface MyQueue {
    void enqueue(String item);
    String dequeue();
    boolean isEmpty();
    int size();
}
