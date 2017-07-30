import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int capacity;
    private Item[] items;

    public RandomizedQueue() {
        size = 0;
        capacity = 1;
        items = (Item[])(new Object[capacity]);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }

        if (size == capacity) {
            resize(capacity * 2);
        }

        items[size++] = item;

        int index = StdRandom.uniform(0, size);
        exchange(items, size - 1, index);
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == capacity / 4) {
            resize(capacity / 2);
        }

        Item item = items[--size];
        items[size] = null;

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return items[StdRandom.uniform(0, size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(items);
    }

    private void exchange(Item[] items, int from, int to) {
        Item tmp = items[to];
        items[to] = items[from];
        items[from] = tmp;
    }

    private void resize(int newCapacity) {
        Item[] newItems = (Item[])(new Object[newCapacity]);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }

        capacity = newCapacity;
        items = newItems;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] items;
        private int current = 0;

        RandomizedQueueIterator(Item[] items) {
            this.items = (Item[])(new Object[size]);

            for (int i = 0; i < size; i++) {
                int r = StdRandom.uniform(0, i + 1);
                this.items[i] = items[i];
                exchange(this.items, i, r);
            }
        }

        @Override
        public boolean hasNext() {
            return current < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}