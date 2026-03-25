import java.util.*;

class Asset {
    String symbol;
    double returnRate;
    double volatility;

    Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return symbol + ":" + returnRate + "%";
    }
}

public class Problem4PortfolioReturnSort {

    // Merge Sort ascending by returnRate
    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (assets[i].returnRate <= assets[j].returnRate) {
                temp[k++] = assets[i++];
            } else {
                temp[k++] = assets[j++];
            }
        }
        while (i <= mid) temp[k++] = assets[i++];
        while (j <= right) temp[k++] = assets[j++];

        System.arraycopy(temp, 0, assets, left, temp.length);
    }

    // Quick Sort descending by returnRate, tie-break volatility ascending
    public static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate &&
                            assets[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 0.2),
                new Asset("TSLA", 8, 0.3),
                new Asset("GOOG", 15, 0.1)
        };

        Asset[] mergeCopy = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeCopy, 0, mergeCopy.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeCopy));

        Asset[] quickCopy = Arrays.copyOf(assets, assets.length);
        quickSort(quickCopy, 0, quickCopy.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickCopy));
    }
}
