import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final Random rand;
    private Item[] items;
    private int nextSlot;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        this.items = (Item[]) new Object[32];
        this.nextSlot = 0;
        this.rand = new Random();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return nextSlot == 0;
    }

    /**
     * Return the number of items in the queue.
     *
     * @return Number of items.
     */
    public int size() {
        return nextSlot;
    }

    /**
     * Add an item to the queue.
     *
     * @param item The item to add.
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (nextSlot == items.length) grow();
        items[nextSlot++] = item;
    }

    /**
     * Remove and return a random item.
     *
     * @return A random item.
     */
    public Item dequeue() {
        if (nextSlot == 0) throw new NoSuchElementException();
        // Pick random item
        int index = rand.nextInt(nextSlot);
        // Remember picked item
        Item item = this.items[index];
        // Move last item to picked item
        items[index] = items[--nextSlot];
        // Shrink if necessary
        if ((double) nextSlot / items.length <= 0.25) shrink();
        // Return item
        return item;
    }

    /**
     * Return a random item without removing it.
     *
     * @return A random item.
     */
    public Item sample() {
        if (nextSlot == 0) throw new NoSuchElementException();
        int index = rand.nextInt(nextSlot);
        return this.items[index];
    }

    /**
     * Return an independent iterator over items in random order.
     *
     * @return Iterator.
     */
    public Iterator<Item> iterator() {
        Item[] itemsCopy = Arrays.copyOf(items, nextSlot);
        return new Iterator<Item>() {
            private final Item[] items = itemsCopy;
            private int count = nextSlot;

            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Item next() {
                if (count == 0) throw new NoSuchElementException();
                int index = rand.nextInt(count);
                Item next = items[index];
                items[index] = items[--count];
                return next;
            }
        };
    }

    /**
     * Grow the array backing the randomized queue.
     */
    private void grow() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    /**
     * Shrink the array backing the randomized queue.
     */
    private void shrink() {
        items = Arrays.copyOf(items, items.length / 2 + 1);
    }
}
