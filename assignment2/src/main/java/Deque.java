import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private DllNode head;
    private DllNode tail;
    private int size;

    /**
     * Construct an empty dequeue.
     */
    public Deque() {
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Return the number of items in the queue. Does not allow null.
     *
     * @return Number of items.
     */
    public int size() {
        return size;
    }

    /**
     * Add an item to the front of the queue. Does not allow null.
     *
     * @param item The item to add.
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;
        DllNode oldHead = head;
        head = new DllNode();
        head.item = item;
        if (oldHead == null) {
            // New item is only item in queue
            // The item is the first and last at the same time
            tail = head;
            return;
        }
        head.next = oldHead;
        oldHead.prev = head;
    }

    /**
     * Add an item to the end of the queue.
     *
     * @param item The item to add.
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;
        DllNode oldTail = tail;
        tail = new DllNode();
        tail.item = item;
        if (oldTail == null) {
            // New item is only item in queue
            // The item is the first and last at the same time
            head = tail;
            return;
        }
        tail.prev = oldTail;
        oldTail.next = tail;
    }

    /**
     * Remove and return the item from the front.
     *
     * @return The removed item.
     */
    public Item removeFirst() {
        if (head == null) throw new NoSuchElementException();
        size--;
        DllNode oldHead = head;
        head = oldHead.next;
        // Only one item
        if (oldHead == tail) {
            head = null;
            tail = null;
            return oldHead.item;
        }
        head.prev = null;
        // Allow garbage collection
        Item item = oldHead.item;
        oldHead.next = null;
        oldHead.item = null;
        return item;
    }

    /**
     * Remove and return the item from the end.
     *
     * @return The removed item.
     */
    public Item removeLast() {
        if (tail == null) throw new NoSuchElementException();
        size--;
        Item item = tail.item;
        // Only one item
        if (head == tail) {
            head = null;
            tail = null;
            return item;
        }
        DllNode oldTail = tail;
        tail = oldTail.prev;
        tail.next = null;
        // Allow garbage collection
        oldTail.prev = null;
        oldTail.item = null;
        return item;
    }

    /**
     * Return an iterator over items in order from front to end.
     *
     * @return Iterator.
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            DllNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    /**
     * Doubly linked list node.
     */
    private class DllNode {
        DllNode next;
        DllNode prev;
        Item item;
    }
}
