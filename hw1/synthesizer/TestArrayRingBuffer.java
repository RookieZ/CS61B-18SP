package synthesizer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    private ArrayRingBuffer<Integer> buffer;

    @Before
    public void setUp() {
        buffer = new ArrayRingBuffer<>(5);
    }

    @Test
    public void testArrayRingBuffer() {
        assertEquals(5, buffer.capacity());
        assertEquals(0, buffer.fillCount());
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        assertEquals(3, buffer.fillCount());
        assertEquals(1, (int) buffer.dequeue());
        assertEquals(2, (int) buffer.dequeue());
        assertEquals(1, buffer.fillCount());
        buffer.enqueue(4);
        buffer.enqueue(5);
        assertEquals(3, buffer.fillCount());
        assertEquals(3, (int) buffer.dequeue());
    }

    @Test
    public void testArrayRingBufferException() {
        try {
            buffer.dequeue();
        } catch (RuntimeException e) {
            assertEquals("Ring Buffer Underflow", e.getMessage());
        }

        try {
            buffer.peek();
        } catch (RuntimeException e) {
            assertEquals("Ring Buffer Underflow", e.getMessage());
        }

        for (int i = 0; i < buffer.capacity; i++) {
            buffer.enqueue(i);
        }

        try {
            buffer.enqueue(1);
        } catch (RuntimeException e) {
            assertEquals("Ring Buffer Overflow", e.getMessage());
        }
    }

    @Test
    public void testArrayRingBufferIteration() {
        for (int i = 0; i < buffer.capacity; i++) {
            buffer.enqueue(i);
        }

        int expected = 0;
        for (Integer i : buffer) {
            assertEquals(expected, (int) i);
            expected++;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    //    public static void main(String[] args) {
    //        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    //    }
} 
