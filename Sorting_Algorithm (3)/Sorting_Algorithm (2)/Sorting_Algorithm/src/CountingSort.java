import javafx.application.Platform;

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
            arrayVisualizer.highlight(value);
            updateAndSleep(arrayVisualizer, 50);
        }

        // Cộng dồn giá trị
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
            updateAndSleep(arrayVisualizer, 50);
        }

        // Sắp xếp
        for (int i = array.length - 1; i >= 0; i--) {
            arrayVisualizer.highlight(i);
            result[count[array[i]] - 1] = array[i];
            count[array[i]]--;
            updateAndSleep(arrayVisualizer, 50);
        }

        System.arraycopy(result, 0, array, 0, array.length);
        updateAndSleep(arrayVisualizer, 1000); // Cập nhật giao diện cuối cùng
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

    private void updateAndSleep(ArrayVisualizer arrayVisualizer, int delay) {
        Platform.runLater(() -> arrayVisualizer.updateArrayView());
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

