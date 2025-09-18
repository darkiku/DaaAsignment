import java.util.*;

public class Main {
    // Метрики
    static long comparisons = 0;
    static long swaps = 0;
    static int maxDepth = 0;
    static int currentDepth = 0;

    static void resetMetrics() {
        comparisons = 0;
        swaps = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    static void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    static void exit() {
        currentDepth--;
    }

    // MergeSort
    static void mergeSort(int[] arr, int l, int r) {
        if (r - l <= 1) return;
        enter();
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid, r);
        int[] tmp = new int[r - l];
        int i = l, j = mid, k = 0;
        while (i < mid && j < r) {
            comparisons++;
            if (arr[i] <= arr[j]) tmp[k++] = arr[i++];
            else tmp[k++] = arr[j++];
        }
        while (i < mid) tmp[k++] = arr[i++];
        while (j < r) tmp[k++] = arr[j++];
        for (int t = 0; t < tmp.length; t++) {
            arr[l + t] = tmp[t];
            swaps++;
        }
        exit();
    }

    // QuickSort
    static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        enter();
        int pivot = arr[(l + r) / 2];
        int i = l, j = r;
        while (i <= j) {
            while (arr[i] < pivot) { comparisons++; i++; }
            while (arr[j] > pivot) { comparisons++; j--; }
            if (i <= j) {
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                swaps++;
                i++; j--;
            }
        }
        if (l < j) quickSort(arr, l, j);
        if (i < r) quickSort(arr, i, r);
        exit();
    }

    //  Deterministic Select
    static int select(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }

    //Closest Pair
    static double closestPair(double[] x, double[] y) {
        double best = Double.MAX_VALUE;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double dx = x[i] - x[j];
                double dy = y[i] - y[j];
                double dist = Math.sqrt(dx*dx + dy*dy);
                comparisons++;
                if (dist < best) best = dist;
            }
        }
        return best;
    }

    //Helpers
    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i-1] > arr[i]) return false;
        return true;
    }

    public static void main(String[] args) {
        Random rnd = new Random();

        // MergeSort
        int[] a = rnd.ints(50, 0, 1000).toArray();
        resetMetrics();
        mergeSort(a, 0, a.length);
        System.out.println("MergeSort sorted? " + isSorted(a));
        System.out.println("Comparisons=" + comparisons + " swaps=" + swaps + " depth=" + maxDepth);

        // QuickSort
        a = rnd.ints(50, 0, 1000).toArray();
        resetMetrics();
        quickSort(a, 0, a.length-1);
        System.out.println("QuickSort sorted? " + isSorted(a));
        System.out.println("Comparisons=" + comparisons + " swaps=" + swaps + " depth=" + maxDepth);

        // Select
        a = rnd.ints(20, 0, 100).toArray();
        int k = 10;
        int kth = select(a.clone(), k);
        Arrays.sort(a);
        System.out.println("Select result=" + kth + " correct? " + (kth == a[k]));

        // Closest Pair
        int n = 30;
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = rnd.nextDouble()*100;
            y[i] = rnd.nextDouble()*100;
        }
        resetMetrics();
        double d = closestPair(x, y);
        System.out.println("Closest pair distance=" + d + " comparisons=" + comparisons);
    }
}
