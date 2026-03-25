import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class Problem3TradeVolumeAnalysis {

    // Merge Sort (ascending)
    public static void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    private static void merge(Trade[] trades, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (trades[i].volume <= trades[j].volume) {
                temp[k++] = trades[i++];
            } else {
                temp[k++] = trades[j++];
            }
        }
        while (i <= mid) temp[k++] = trades[i++];
        while (j <= right) temp[k++] = trades[j++];

        System.arraycopy(temp, 0, trades, left, temp.length);
    }

    // Quick Sort (descending)
    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }

    private static int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot) { // descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort ascending
        Trade[] mergeCopy = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeCopy, 0, mergeCopy.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeCopy));

        // Quick Sort descending
        Trade[] quickCopy = Arrays.copyOf(trades, trades.length);
        quickSort(quickCopy, 0, quickCopy.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickCopy));

        // Merge two lists (simulate morning + afternoon)
        int totalVolume = Arrays.stream(trades).mapToInt(t -> t.volume).sum();
        System.out.println("Merged total volume: " + totalVolume);
    }
}
