public class ArrayDeque<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.25f;
    private T[] array;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    public int getCapacity() {
        return capacity;
    }

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void copyArray(T[] newArray) {
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        array = newArray;
    }

    private void expand() {
        if (size == capacity) {
            T[] newArray = (T[]) new Object[capacity * 2];
            copyArray(newArray);
            capacity *= 2;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    private void shrink() {
        if ((capacity != INITIAL_CAPACITY) && ((double) size / (double) capacity < LOAD_FACTOR)) {
            T[] newArray = (T[]) new Object[capacity / 2];
            copyArray(newArray);
            capacity /= 2;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    public void addFirst(T value) {
        expand();

        array[nextFirst] = value;
        if (nextFirst == 0) {
            nextFirst = capacity - 1;
        } else {
            nextFirst--;
        }

        size++;
    }

    public void addLast(T value) {
        expand();

        array[nextLast] = value;
        if (nextLast == capacity - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }

        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (nextFirst == capacity - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T value = array[nextFirst];
        size--;

        shrink();

        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast == 0) {
            nextLast = capacity - 1;
        } else {
            nextLast--;
        }
        T value = array[nextLast];
        size--;

        shrink();

        return value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int i = (nextFirst + index + 1) % capacity;
        return array[i];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }
}
