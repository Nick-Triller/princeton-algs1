import java.util.Arrays;

public class SelectionSort {
    private static Comparable[] leftItems;

    public static void main(String[] args) {
        Integer[] numbers = new Integer[]{45, 11, 36, 99, 4, 0, 22, 11};
        selectionSort(numbers);
    }

    private static void selectionSort(Comparable[] items) {
        for (int p = 0; p < items.length; p++) {
            // Find smallest in remaining entries
            int min = p;
            for (int i = p + 1; i < items.length; i++) {
                if (less(items[i], items[min])) {
                    min = i;
                }
            }
            // Swap items
            exch(items, p, min);
            checkInvariants(p, items);
        }
    }

    private static void checkInvariants(int pointer, Comparable[] items) {
        Comparable smallestLeft = items[0];
        Comparable biggestLeft = items[pointer];
        for (int i = 0; i <= pointer; i++) {
            if (less(items[i], smallestLeft)) {
                // Entries the left of pointer (including pointer) in ascending order
                throw new RuntimeException("Left of pointer not in ascending order");
            }
        }
        // No entry to right of pointer is smaller than any entry to the left of pointer
        for (int i = pointer + 1; i < items.length; i++) {
            if (less(items[i], biggestLeft)) {
                throw new RuntimeException("Item right of pointer smaller than an element left of pointer");
            }
        }
        // Items left of pointer are fixed
        if (leftItems == null) return; // no items left of pointer in first iteration
        for (int i = 0; i < leftItems.length; i++) {
            if (leftItems[i] != items[i]) {
                throw new RuntimeException("Items left of pointer should be fixed");
            }
        }
        leftItems = Arrays.copyOf(items, pointer);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
