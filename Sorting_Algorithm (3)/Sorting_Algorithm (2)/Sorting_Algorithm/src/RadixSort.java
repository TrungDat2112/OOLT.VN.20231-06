public class RadixSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, ArrayVisualizer arrayVisualizer) {
        radixSort(array, arrayVisualizer);
    }

    private void radixSort(int[] array, ArrayVisualizer arrayVisualizer) {
        int max = findMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp, arrayVisualizer);
        }
    }

    private void countingSortByDigit(int[] array, int exp, ArrayVisualizer arrayVisualizer) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
        arrayVisualizer.updateArrayView();
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;}
}
   

