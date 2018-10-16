/**
 * Create linked list deque.
 * @author Sherry Nie
 * @version 1.2
 * @param <T> generic item type
 */

public class LinkedListDeque<T> {

    public class IntNode {
        public T item;
        private IntNode prev;
        private IntNode next;

        private IntNode(T x, IntNode n, IntNode p) {
            item = x;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    /**
     * create an empty LinkedListDeque.
     */

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T x) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Adds x to the front of the list.
     */

    public void addFirst(T x) {

        size += 1;
        sentinel.next = new IntNode(x, sentinel.next, sentinel);
        if (sentinel.prev == sentinel) sentinel.prev = sentinel.next.next;

    }

    public void addLast(T x) {
        size += 1;
        sentinel.prev.next = new IntNode(x, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;

    }


    public T get(int x) {
        IntNode p;
        p = sentinel.next;
        for (int i = 0; i < x; i++) {
            p = p.next;
        }
//        System.out.println("list of "+x +" is "+ p.item);
        return p.item;
    }

    private IntNode Recursive(int x) {
        if (x == 0) return sentinel.next;
        else {
            return Recursive(x - 1).next;
        }
    }

    public T getRecursize(int x) {
        return Recursive(x).item;

    }

    public T removeLast() {
        size -= 1;
        IntNode p = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return p.item;
    }

    public T removeFirst() {
        size -= 1;
//
        IntNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        return p.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(" ");
    }
}
