package org.coursera;

import org.coursera.datastructures.ArrayStack;
import org.coursera.datastructures.LinkedListStack;
import org.coursera.datastructures.MyStack;

public class Client {
    private static MyStack stack;

    public static void main(String[] args) {
        System.out.println("Testing LinkedListStack performance");

        stack = new ArrayStack();

        measureStackPerformance(40000);

    }

    private static void measureStackPerformance(int itemsCount) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < itemsCount; i++) {
            stack.push("foo");
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("%d pushes took %d ms", itemsCount, elapsedTime);
    }
}
