package org.coursera.datastructures;

public class ArrayStack implements MyStack {
    private String[] stack;
    private int head;
    private int capacity;

    public ArrayStack() {
        capacity = 1;
        stack = new String[capacity];
        head = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    @Override
    public void push(String item) {
        resize(capacity + 1);
        stack[head++] = item;
    }

    @Override
    public String pop() {
        if (isEmpty()) throw new StackUnderflowException();

        String item = stack[--head];
        stack[head] = null;
        resize(capacity - 1);

        return item;
    }

    @Override
    public int size() {
        return head;
    }

    private void resize(int newCapacity) {
        String[] newStack = new String[newCapacity];
        for (int i = 0; i < capacity; i++) {
            newStack[i] = stack[i];
        }

        capacity = newCapacity;
        stack = newStack;
    }
}
