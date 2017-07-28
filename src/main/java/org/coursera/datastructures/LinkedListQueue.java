package org.coursera.datastructures;

public class LinkedListQueue implements MyQueue {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(String item) {
        Node node = new Node(item, null);

        if (null != head) {
            head.next = node;
        }
        head = node;

        if (null == tail) {
            tail = node;
        }
        size++;
    }

    @Override
    public String dequeue() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        Node dequeued = tail;
        tail = dequeued.next;
        size--;

        if (null == tail) {
            head = null;
        }

        return dequeued.item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        String item;
        Node next;

        Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
