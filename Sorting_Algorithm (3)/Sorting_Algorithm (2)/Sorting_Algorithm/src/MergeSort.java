public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, ArrayVisualizer arrayVisualizer) {
        if (array == null || array.length == 0) {
            return;
        }
        mergeSort(array, 0, array.length - 1, arrayVisualizer);
    }

    private void mergeSort(int[] array, int left, int right, ArrayVisualizer arrayVisualizer) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid, arrayVisualizer);
            mergeSort(array, mid + 1, right, arrayVisualizer);
            merge(array, left, mid, right, arrayVisualizer);
        }
    }

    private void merge(int[] array, int left, int mid, int right, ArrayVisualizer arrayVisualizer) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arrayVisualizer.highlight(k);
            arrayVisualizer.sleep(100); // Đặt độ trễ để quan sát
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            arrayVisualizer.unhighlight(k);
            k++;
        }

        // Copy remaining elements
        while (i < n1) {
            arrayVisualizer.highlight(k);
            arrayVisualizer.sleep(100);
            array[k] = leftArray[i];
            arrayVisualizer.unhighlight(k);
            i++;
            k++;
        }
        while (j < n2) {
            arrayVisualizer.highlight(k);
            arrayVisualizer.sleep(100);
            array[k] = rightArray[j];
            arrayVisualizer.unhighlight(k);
            j++;
            k++;
        }
        arrayVisualizer.updateArrayView(); // Cập nhật giao diện người dùng sau mỗi lần gộp
    }
}
