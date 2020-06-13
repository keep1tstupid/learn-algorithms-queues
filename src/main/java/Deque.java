import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Item getData() {
            return item;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        // initialize pointer to head of the list for iteration
        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item data = current.getData();
            current = current.getNext();
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Halp!");
        }

        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = null;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Halp!");
        }

        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.prev = null;
            last.next = null;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            last.next = null;
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        Item itemDel = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return itemDel;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        Item itemDel = last.item;
        if (size == 1) {
            first = null;
            last = first;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;

        return itemDel;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(0);
        testDeque.addFirst(1);
        testDeque.addFirst(2);
        testDeque.addFirst(3);
        testDeque.addLast(10);
        testDeque.addLast(11);

        System.out.println("Items are: ");
        for (Integer i: testDeque) {
            System.out.print(i + " ");
        }

        testDeque.removeFirst();
        testDeque.removeLast();

        System.out.println("\nAfter deleting 1st and last items are: ");
        for (Integer i: testDeque) {
            System.out.print(i + " ");
        }
    }
}
