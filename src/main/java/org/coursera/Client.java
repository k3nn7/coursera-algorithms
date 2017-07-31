package org.coursera;

import org.coursera.datastructures.LinkedListQueue;
import org.coursera.datastructures.MyQueue;

public class Client {
    private static MyQueue queue;

    public static void main(String[] args) {
        System.out.println("Testing LinkedListStack performance");

        queue = new LinkedListQueue();

        measureQueuePerformance(40000);

    }

    private static void measureQueuePerformance(int itemsCount) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < itemsCount; i++) {
            queue.enqueue("foo");
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("%d pushes took %d ms", itemsCount, elapsedTime);
    }
}
