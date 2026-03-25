import java.util.*;

public class Problem6RiskThresholdLookup {

    // Linear Search in unsorted array
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: threshold=" + target +
                        " found at index " + i +
                        " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear: threshold=" + target +
                " not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Binary Search floor and ceiling in sorted array
    public static void binaryFloorCeiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer floor = null, ceiling = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid] == target) {
                floor = arr[mid];
                ceiling = arr[mid];
                break;
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceiling = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + floor +
                ", ceiling: " + ceiling +
                " (" + comparisons + " comparisons)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        Arrays.sort(risks); // ensure sorted for binary

        // Linear search unsorted (simulate)
        linearSearch(risks, 30);

        // Binary search floor/ceiling
        binaryFloorCeiling(risks, 30);
    }
}
