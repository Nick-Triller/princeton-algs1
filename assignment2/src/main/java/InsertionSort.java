public class InsertionSort {
    public static void main(String[] args) {
        Integer[] numbers = new Integer[]{45, 11, 36, 99, 4, 0, 22, 11};
        insertionSort(numbers);
    }

    private static void insertionSort(Comparable[] items) {
        for (int p = 1; p < items.length; p++) {
            for (int i = p; i > 0; i--) {
                if (less(items[i], items[i - 1])) {
                    exch(items, i, p);
                } else break;
            }
        }
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