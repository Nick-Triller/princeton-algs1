import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class DequeTests {

    /**
     * addLast tests
     */
    @Test
    public void addLast_AddsToEnd() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        int item1 = deque.removeFirst();
        int item2 = deque.removeFirst();
        int item3 = deque.removeFirst();
        // Assert
        assertEquals(1, item1);
        assertEquals(2, item2);
        assertEquals(3, item3);
    }

    @Test
    public void addLast_ItemNull_Throws() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> deque.addLast(null));
    }

    /**
     * addFirst tests
     */
    @Test
    public void addFirst_AddsToFront() {
        // Arrange
        Deque<Integer> deque = new Deque<Integer>();
        // Act
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        int item1 = deque.removeFirst();
        int item2 = deque.removeFirst();
        int item3 = deque.removeFirst();
        // Assert
        assertEquals(3, item1);
        assertEquals(2, item2);
        assertEquals(1, item3);
    }

    @Test
    public void addFirst_ShouldThrow_WhenItemNull() {
        // Arrange
        Deque<Integer> deque = new Deque<Integer>();
        // Act && Assert
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    }

    /**
     * removeFirst tests
     */
    @Test
    public void removeFirst_RemovesFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        int item1 = deque.removeFirst();
        int item2 = deque.removeFirst();
        int item3 = deque.removeFirst();
        // Assert
        assertEquals(1, item1);
        assertEquals(2, item2);
        assertEquals(3, item3);
    }

    @Test
    public void removeFirst_ShouldThrow_WhenEmpty() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act && Assert
        assertThrows(NoSuchElementException.class, deque::removeFirst);
    }

    /**
     * removeLast tests
     */
    @Test
    public void removeLast_RemovesLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        int item1 = deque.removeLast();
        int item2 = deque.removeLast();
        int item3 = deque.removeLast();
        // Assert
        assertEquals(3, item1);
        assertEquals(1, item2);
        assertEquals(2, item3);
    }

    @Test
    public void removeLast_ShouldThrow_WhenEmpty() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act && Assert
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    /**
     * isEmpty tests
     */
    @Test
    public void isEmpty_ShouldReturnTrue_Initially() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Assert
        assertTrue(deque.isEmpty());
    }


    @Test
    public void isEmpty_ShouldReturnTrue_AfterAddFirstRemoveFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addFirst(1);
        deque.removeFirst();
        // Assert
        assertTrue(deque.isEmpty());
    }

    @Test
    public void isEmpty_ShouldReturnTrue_AfterAddLastRemoveLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        deque.removeLast();
        // Assert
        assertTrue(deque.isEmpty());
    }

    @Test
    public void isEmpty_ShouldReturnFalse_AfterAddFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addFirst(1);
        // Assert
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isEmpty_ShouldReturnFalse_AfterAddLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addFirst(1);
        // Assert
        assertFalse(deque.isEmpty());
    }

    /**
     * size tests
     */
    @Test
    void size_ShouldReturnZero_Initially() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act && Assert
        assertEquals(0, deque.size());
    }

    @Test
    public void size_ShouldReturnOne_AfterAddFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addFirst(1);
        // Assert
        assertEquals(1, deque.size());
    }

    @Test
    public void size_ShouldReturnOne_AfterAddLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        // Assert
        assertEquals(1, deque.size());
    }

    @Test
    public void size_ShouldReturnZero_AfterAddFirstRemoveFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addFirst(1);
        deque.removeFirst();
        // Assert
        assertEquals(0, deque.size());
    }

    @Test
    public void size_ShouldReturnZero_AfterAddLastRemoveLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        deque.addLast(1);
        deque.removeLast();
        // Assert
        assertEquals(0, deque.size());
    }

    @Test
    public void size_ShouldReturnZero_AfterRemoveFirst() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        try {
            deque.removeFirst();
        } catch (Exception ignored) {
        }
        // Assert
        assertEquals(0, deque.size());
    }

    @Test
    public void size_ShouldReturnZero_AfterRemoveLast() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        // Act
        try {
            deque.removeLast();
        } catch (Exception ignored) {
        }
        // Assert
        assertEquals(0, deque.size());
    }

    /**
     * iterator tests
     */
    @Test
    public void iterator_ShouldIterateFIFO() {
        // Arrange
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        int[] expected = new int[]{2, 1, 3};
        int[] actual = new int[3];
        int i = 0;
        // Act
        for (int item : deque) {
            actual[i] = item;
            i++;
        }
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    public void iterator_next_ShouldThrow_WhenEmpty() {
        // Arrange
        Iterator<Integer> iterator = new Deque<Integer>().iterator();
        // Act && Assert
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iterator_remove_ShouldThrow() {
        // Arrange
        Iterator<Integer> iterator = new Deque<Integer>().iterator();
        // Act && Assert
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    public void iterator_hasNext_ShouldReturnFalse_WhenNoNext() {
        // Arrange
        Iterator<Integer> iterator = new Deque<Integer>().iterator();
        // Assert
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_hasNext_ShouldReturnTrue_WhenNext() {
        // Arrange
        Deque<Integer> deque = new Deque<Integer>();
        // Act
        deque.addFirst(0);
        Iterator<Integer> iterator = deque.iterator();
        // Assert
        assertTrue(iterator.hasNext());
    }

    @Test
    public void iterator_ShouldNotInfluenceOtherIterators_WhenInstantiatedMultipleTimes() {
        // Arrange
        Deque<Integer> deque = new Deque<Integer>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add(i);
            deque.addLast(i);
        }
        // Act
        List<Integer> items1 = new ArrayList<>();
        List<Integer> items2 = new ArrayList<>();
        for (int item : deque) {
            items1.add(item);
            for (int inner : deque) {
            }
        }
        for (int item : deque) {
            items2.add(item);
            for (int inner : deque) {
            }
        }
        items1.sort(Integer::compareTo);
        items2.sort(Integer::compareTo);
        expected.sort(Integer::compareTo);
        // Assert
        assertEquals(expected, items1);
        assertEquals(expected, items2);
    }
}
