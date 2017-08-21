package org.coursera.datastructures;

public class ArrayQueue implements MyQueue {
    private int head = 0;
    private int tail = 0;
    private int size = 1;
    private String[] items;

    public ArrayQueue() {
        items = new String[size];
    }

    @Override
    public void enqueue(String item) {
        if (head == size) resize(size * 2);

        items[head++] = item;

    }

    @Override
    public String dequeue() {
        return items[tail++];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return head - tail;
    }

    private void resize(int newSize) {
        String[] newItems = new String[newSize];

        for (int i = 0; i < head; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }
}
