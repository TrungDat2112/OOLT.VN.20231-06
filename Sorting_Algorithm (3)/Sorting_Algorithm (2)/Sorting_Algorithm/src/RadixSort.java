import java.util.Arrays;
import javafx.application.Platform;

public class RadixSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, ArrayVisualizer arrayVisualizer) {
        radixSort(array, arrayVisualizer);
    }

    private void radixSort(int[] array, ArrayVisualizer arrayVisualizer) {
        int max = findMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp, arrayVisualizer);
            updateAndSleep(arrayVisualizer); // Độ trễ sau mỗi lần sắp xếp theo chữ số
        }
    }

    private void countingSortByDigit(int[] array, int exp, ArrayVisualizer arrayVisualizer) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int index = (array[i] / exp) % 10;
            count[index]++;
            arrayVisualizer.highlight(i); // Làm nổi bật phần tử đang được xử lý
        }
        updateAndSleep(arrayVisualizer); // Cập nhật giao diện sau khi đếm

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        updateAndSleep(arrayVisualizer); // Cập nhật giao diện sau khi cộng dồn

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
            arrayVisualizer.highlight(i); // Làm nổi bật phần tử đang được xử lý
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            arrayVisualizer.unhighlight(i); // Bỏ làm nổi bật khi đã xếp vào vị trí đúng
        }
        updateAndSleep(arrayVisualizer); // Cập nhật giao diện sau khi sắp xếp
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

    private void updateAndSleep(ArrayVisualizer arrayVisualizer) {
        Platform.runLater(arrayVisualizer::updateArrayView);
        arrayVisualizer.sleep(500); // Độ trễ 0.5 giây để quan sát
    }
}
