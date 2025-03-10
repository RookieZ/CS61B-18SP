import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testAddFirst() {
        StudentArrayDeque<Integer> stuArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();

        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            int num = StdRandom.uniform(30);

            if (numberBetweenZeroAndOne < 0.5) {
                stuArrayDeque.addFirst(num);
                arrayDequeSolution.addFirst(num);
                stringBuilder.append("addFirst(");
                stringBuilder.append(num);
                stringBuilder.append(")");
                stringBuilder.append("\n");
            } else {
                stuArrayDeque.addLast(num);
                arrayDequeSolution.addLast(num);
                stringBuilder.append("addLast(");
                stringBuilder.append(num);
                stringBuilder.append(")");
                stringBuilder.append("\n");
            }
        }

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                stringBuilder.append("removeFirst()\n");
                assertEquals(stringBuilder.toString(), arrayDequeSolution.removeFirst(), stuArrayDeque.removeFirst());
            } else {
                stringBuilder.append("removeLast()\n");
                assertEquals(stringBuilder.toString(),  arrayDequeSolution.removeLast(), stuArrayDeque.removeLast());
            }
        }
    }
}
