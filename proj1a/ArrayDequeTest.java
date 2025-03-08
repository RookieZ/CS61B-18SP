import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testArrayDequeConstructor() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        // assertEquals(8, deque.getCapacity());
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.get(0));
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        // 4 3 2 1
        deque.printDeque();
        assertFalse(deque.isEmpty());
        assertEquals(4, deque.size());
        // assertEquals(8, deque.getCapacity());
        assertEquals(4, (int) deque.get(0));
        assertEquals(2, (int) deque.get(2));
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        // 1 2 3 4
        deque.printDeque();
        assertFalse(deque.isEmpty());
        assertEquals(4, deque.size());
        // assertEquals(8, deque.getCapacity());
        assertEquals(1, (int) deque.get(0));
        assertEquals(3, (int) deque.get(2));
    }

    @Test
    public void testRemoveAndAdd() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        // 3 2 1 4 5 6
        deque.printDeque();
        assertFalse(deque.isEmpty());
        assertEquals(6, deque.size());
        assertEquals(4, (int) deque.get(3));
        assertEquals(3, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(6, (int) deque.removeLast());
        assertEquals(5, (int) deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(4, (int) deque.get(1));
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addLast(9);
        deque.addLast(10);
        // 8 7 1 4 9 10
        deque.printDeque();
        assertEquals(6, deque.size());
        assertEquals(10, (int) deque.get(5));
    }

    @Test
    public void testAddFirstExpand() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        // assertEquals(8, deque.getCapacity());

        deque.addFirst(8);
        // 8 7 6 5 4 3 2 1 0
        deque.printDeque();
        // assertEquals(16, deque.getCapacity());
        assertEquals(8, (int) deque.get(0));
    }

    @Test
    public void testAddLastExpand() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        // assertEquals(8, deque.getCapacity());

        deque.addLast(8);
        // 0 1 2 3 4 5 6 7 8
        deque.printDeque();
        // assertEquals(16, deque.getCapacity());
        assertEquals(8, (int) deque.get(8));
    }

    @Test
    public void testRemoveFirstShrink() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            deque.addFirst(i);
        }
        // assertEquals(16, deque.getCapacity());

        for (int i = 0; i < 5; i++) {
            deque.removeFirst();
        }
        // assertEquals(16, deque.getCapacity());

        deque.removeFirst();
        // assertEquals(8, deque.getCapacity());
        deque.removeFirst();
        // assertEquals(8, deque.getCapacity());
        assertEquals(1, (int) deque.get(0));
    }

    @Test
    public void testRemoveLastShrink() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            deque.addLast(i);
        }
        // assertEquals(16, deque.getCapacity());

        for (int i = 0; i < 5; i++) {
            deque.removeLast();
        }
        // assertEquals(16, deque.getCapacity());

        deque.removeLast();
        // assertEquals(8, deque.getCapacity());
        deque.removeLast();
        // assertEquals(8, deque.getCapacity());
        assertEquals(0, (int) deque.get(0));
    }
}
