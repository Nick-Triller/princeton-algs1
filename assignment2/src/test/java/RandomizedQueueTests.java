import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RandomizedQueueTests {
    /**
     * enqueue tests
     */
    @Test
    public void enqueue_ShouldThrow_WhenItemNull() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> randQueue.enqueue(null));
    }

    @Test
    public void enqueue_ShouldNotFail_WhenAddingManyItems() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        for (int i = 0; i < 2000; i++) {
            randQueue.enqueue(i);
        }
        // Assert
        assertEquals(2000, randQueue.size());
    }

    @Test
    public void enqueue_ShouldAddItem_WhenCalled() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        randQueue.enqueue(1);
        // Assert
        assertEquals(1, randQueue.size());
    }

    /**
     * dequeue tests
     */
    @Test
    public void dequeue_ShouldThrow_WhenEmpty() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act && Assert
        assertThrows(NoSuchElementException.class, randQueue::dequeue);
    }

    @Test
    public void dequeue_ShouldNotFail_WhenRemovingManyItems() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        for (int i = 0; i < 2000; i++) {
            randQueue.enqueue(i);
        }
        for (int i = 0; i < 2000; i++) {
            randQueue.dequeue();
        }
        // Assert
        assertEquals(0, randQueue.size());
    }

    /**
     * sample tests
     */
    @Test
    public void sample_ShouldThrow_WhenEmpty() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act && Assert
        assertThrows(NoSuchElementException.class, randQueue::sample);
    }

    @Test
    public void sample_ShouldNotRemoveItem_WhenCalled() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        int expected = 1;
        // Act
        randQueue.enqueue(expected);
        int item = randQueue.sample();
        // Assert
        assertEquals(expected, item);
    }

    /**
     * isEmpty tests
     */
    @Test
    public void isEmpty_ShouldReturnTrue_Initially() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Assert
        assertTrue(randQueue.isEmpty());
    }

    @Test
    public void isEmpty_ShouldReturnTrue_AfterEnqueueDeque() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        randQueue.enqueue(1);
        randQueue.dequeue();
        // Assert
        assertTrue(randQueue.isEmpty());
    }

    @Test
    public void isEmpty_ShouldWork_AfterQueueWasEmptiedMultipleTimes() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        for (int z = 0; z < 10; z++) {
            for (int i = 0; i < 1000; i++) {
                randQueue.enqueue(z);
            }
            for (int i = 0; i < 1000; i++) {
                randQueue.dequeue();
            }
        }
        // Assert
        assertTrue(randQueue.isEmpty());
    }

    /**
     * iterator tests
     */
    @Test
    public void iterator_remove_ShouldThrow() {
        // Arrange
        Iterator<Integer> iterator = new RandomizedQueue<Integer>().iterator();
        // Act && Assert
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    public void iteratorNext_ShouldThrow_WhenEmpty() {
        // Arrange
        Iterator<Integer> iterator = new RandomizedQueue<Integer>().iterator();
        // Act && Assert
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iteratorNext_ShouldReturnItems_WhenCalled() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        List<Integer> expected = new ArrayList<>();
        int itemCount = 10;
        for (int i = 0; i < itemCount; i++) {
            randQueue.enqueue(i);
            expected.add(i);
        }
        // Act
        List<Integer> dequeued = new ArrayList<>();
        for (int item : randQueue) {
            dequeued.add(item);
        }
        // Assert
        dequeued.sort(Integer::compareTo);
        assertIterableEquals(expected, dequeued);
    }

    @Test
    public void iteratorNext_ShouldNotDequeue_WhenCalled() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        int itemCount = 10;
        for (int i = 0; i < itemCount; i++) {
            randQueue.enqueue(1);
        }
        // Act
        Iterator<Integer> iterator = randQueue.iterator();
        iterator.next();
        // Assert
        assertEquals(itemCount, randQueue.size());
    }

    @Test
    public void iteratorHasNext_ShouldReturnFalse_WhenEmpty() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        boolean hasNext = randQueue.iterator().hasNext();
        // Assert
        assertFalse(hasNext);
    }

    @Test
    public void iteratorHasNext_ShouldReturnTrue_WhenNext() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        // Act
        randQueue.enqueue(1);
        boolean hasNext = randQueue.iterator().hasNext();
        // Assert
        assertTrue(hasNext);
    }

    @Test
    public void iterator_ShouldNotInfluenceOtherIterators_WhenInstantiatedMultipleTimes() {
        // Arrange
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add(i);
            randQueue.enqueue(i);
        }
        // Act
        List<Integer> items1 = new ArrayList<>();
        List<Integer> items2 = new ArrayList<>();
        for (int item : randQueue) {
            items1.add(item);
            for (int inner : randQueue) {
            }
        }
        for (int item : randQueue) {
            items2.add(item);
            for (int inner : randQueue) {
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
