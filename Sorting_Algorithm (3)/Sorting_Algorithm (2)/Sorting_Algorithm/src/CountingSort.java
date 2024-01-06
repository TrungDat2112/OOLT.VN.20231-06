public class CountingSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, ArrayVisualizer arrayVisualizer) {
        countingSort(array, arrayVisualizer);
    }

    private void countingSort(int[] array, ArrayVisualizer arrayVisualizer) {
        int max = findMax(array);
        int[] count = new int[max + 1];
        int[] result = new int[array.length];

        for (int value : array) {
            count[value]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            result[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        System.arraycopy(result, 0, array, 0, array.length);
        arrayVisualizer.updateArrayView();
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
