package org.coursera.datastructures;

public class ArrayStack implements MyStack {
    private String[] stack;
    private int head;

    public ArrayStack() {
        stack = new String[1];
        head = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    @Override
    public void push(String item) {
        if (head == stack.length) {
            resize(stack.length * 2);
        }

        stack[head++] = item;
    }

    @Override
    public String pop() {
        if (isEmpty()) throw new StackUnderflowException();

        if (head > 0 && head <= stack.length / 4) {
            resize(stack.length / 2);
        }

        String item = stack[--head];
        stack[head] = null;

        return item;
    }

    @Override
    public int size() {
        return head;
    }

    private void resize(int newCapacity) {
        String[] newStack = new String[newCapacity];
        for (int i = 0; i < head; i++) {
            newStack[i] = stack[i];
        }

        stack = newStack;
    }
}
