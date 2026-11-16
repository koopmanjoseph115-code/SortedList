import java.util.ArrayList;

public class SortedList {
    private ArrayList<String> list;

    public SortedList() {
        list = new ArrayList<>();
    }

    /**
     * Custom Binary Search implementation.
     * @param target The String to search for.
     * @return If found, returns the index (>= 0).
     *         If not found, returns (-(insertion point) - 1).
     */
    public int binarySearch(String target) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midVal = list.get(mid);
            int cmp = midVal.compareTo(target);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // Key found
            }
        }
        return -(low + 1); // Key not found; returns encoded insertion point
    }

    /**
     * Adds a String to the list in sorted ascending order using binary search.
     * @param item The String to add.
     * @return The index where the item was inserted.
     */
    public int add(String item) {
        int index = binarySearch(item);
        if (index < 0) {
            // Decode the insertion point
            index = -(index + 1);
        }
        list.add(index, item);
        return index;
    }

    public String get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("[").append(i).append("] ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }
}
