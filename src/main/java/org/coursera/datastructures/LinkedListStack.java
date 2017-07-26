package org.coursera.datastructures;

public class LinkedListStack implements MyStack {
    private Node head;
    private int size;

    public LinkedListStack() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(String item) {
        Node oldHead = head;
        head = new Node(item, oldHead);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String pop() {
        if (isEmpty()) throw new StackUnderflowException();

        Node popped = head;
        head = popped.next;
        size--;
        return popped.item;
    }

    private class Node {
        public String item;
        public Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
