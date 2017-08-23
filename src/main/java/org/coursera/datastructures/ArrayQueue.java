package org.coursera.datastructures;

public class ArrayQueue implements MyQueue {
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private String[] items;

    public ArrayQueue() {
        items = new String[2];
    }

    @Override
    public void enqueue(String item) {
        if (size == items.length) resize(size * 2);
        if (head == items.length) head = 0;

        items[head++] = item;
        size++;
    }

    @Override
    public String dequeue() {
        if (isEmpty()) throw new UnderflowException();

        size--;
        if (size > 0 && size == items.length / 4) resize(items.length / 2);
        if (tail == items.length) tail = 0;
        return items[tail++];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        String[] newItems = new String[newSize];

        for (int i = 0; i < size; i++) {
            newItems[i] = items[(i + head) % items.length];
        }

        tail = 0;
        head = size;
        items = newItems;
    }
}
