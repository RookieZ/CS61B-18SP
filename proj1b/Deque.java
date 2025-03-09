public interface Deque<T> {
    public void addFirst(T value);
    public void addLast(T value);
    public T removeFirst();
    public T removeLast();
    public boolean isEmpty();
    public int size();
    public T get(int index);
    public void printDeque();
}
