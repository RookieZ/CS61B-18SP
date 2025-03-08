public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private final T value;
        private Node prev;
        private Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    private int size;
    private final Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        first.prev = null;
        first.next = null;

        size--;

        return first.value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        last.prev = null;
        last.next = null;

        size--;

        return last.value;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index--;
        }

        return current.value;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node current) {
        if (index >= size || index < 0) {
            return null;
        }

        if (index == 0) {
            return current.value;
        }

        return getRecursiveHelper(index - 1, current.next);
    }

    @Override
    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}
