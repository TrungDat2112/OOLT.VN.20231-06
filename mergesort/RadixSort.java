//import java.util.Arrays;
//
//public class RadixSort {
//
//    private int[] array;
//    private int comparisons = 0;
//    private int arrayAccesses = 0;
//
//    public RadixSort(int[] array) {
//        this.array = Arrays.copyOf(array, array.length);
//    }
//
////    public void sort() {
////        int max = getMax(array);
////
////        for (int exp = 1; max / exp > 0; exp *= 10) {
////            countSort(array, exp, panel);
////        }
////    }
////    private void visualize(int[] arr, int exp, SortingPanel panel) {
////        panel.setArray(arr); // Update the array in the panel to the current state
////        panel.setHighlightedDigit(exp); // Update the digit being considered
////        panel.repaint(); // Repaint the panel
////        try {
////            Thread.sleep(35); // Delay for visualization, adjust as needed
////        } catch (InterruptedException e) {
////            Thread.currentThread().interrupt();
////        }
////    }
//    private void countSort(int[] arr, int exp) {
//        int n = arr.length;
//        int[] output = new int[n];
//        int[] count = new int[10];
//        Arrays.fill(count, 0);
//
//        for (int i = 0; i < n; i++) {
//            arr[i] = output[i];
//            arrayAccesses++;
//            visualize(arr, exp, panel);
//        }
//        for (int i = 1; i < 10; i++)
//            count[i] += count[i - 1];
//
//        for (int i = n - 1; i >= 0; i--) {
//            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
//            count[(arr[i] / exp) % 10]--;
//            arrayAccesses += 2;
//        }
//
//        System.arraycopy(output, 0, arr, 0, n);
//        arrayAccesses += n;
//    }
//
//    private int getMax(int[] arr) {
//        int mx = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] > mx)
//                mx = arr[i];
//            comparisons++;
//            arrayAccesses++;
//        }
//        return mx;
//    }
//
//    public int getComparisons() {
//        return comparisons;
//    }
//
//    public int getArrayAccesses() {
//        return arrayAccesses;
//    }
//
//    public int[] getArray() {
//        return array;
//    }
//}
