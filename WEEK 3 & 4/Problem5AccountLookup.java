import java.util.*;

public class Problem5AccountLookup {

    // Linear Search first occurrence
    public static int linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear first " + target + ": index " + i +
                        " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Not found");
        return -1;
    }

    // Binary Search exact match
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1, comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid].equals(target)) {
                System.out.println("Binary " + target + ": index " + mid +
                        " (" + comparisons + " comparisons)");
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Not found");
        return -1;
    }

    // Count duplicates
    public static int countOccurrences(String[] arr, String target) {
        int count = 0;
        for (String s : arr) if (s.equals(target)) count++;
        return count;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        Arrays.sort(logs); // Binary requires sorted input
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        linearSearchFirst(logs, "accB");
        int idx = binarySearch(logs, "accB");
        System.out.println("Count of accB: " + countOccurrences(logs, "accB"));
    }
}
