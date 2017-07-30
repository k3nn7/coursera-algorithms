import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;

    public Deque() {
        size = 0;
        head = new Node(null, null, null);
        tail = new Node(null, null, null);

        tail.next = head;
        tail.prev = head;

        head.next = tail;
        head.prev = tail;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }

        Node node = new Node(item, head, head.prev);
        head.prev.next = node;
        head.prev = node;

        size++;
    }

    public void addLast(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }

        Node node = new Node(item, tail.next, tail);
        tail.next.prev = node;
        tail.next = node;

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        size--;

        Node removed = head.prev;
        head.prev = head.prev.prev;
        head.prev.next = head;

        return removed.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        size--;

        Node removed = tail.next;
        tail.next = tail.next.next;
        tail.next.prev = tail;

        return removed.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(head.prev);
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        DequeIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.prev;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
