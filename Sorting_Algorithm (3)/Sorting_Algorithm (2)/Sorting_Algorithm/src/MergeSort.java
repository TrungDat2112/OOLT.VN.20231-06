import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MergeSort implements SortingAlgorithm {

    private ArrayVisualizer arrayVisualizer;


    private PauseTransition pause = new PauseTransition(Duration.millis(100));
    private static class MergeState {
        int i, j, k;

        MergeState(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
    }

    public MergeSort(ArrayVisualizer arrayVisualizer) {
        this.arrayVisualizer = arrayVisualizer;
    }

    public void sort(int[] array, ArrayVisualizer arrayVisualizer) {
        mergeSort(array, 0, array.length - 1, arrayVisualizer);
    }

    private void mergeSort(int[] array, int left, int right, ArrayVisualizer arrayVisualizer) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid, arrayVisualizer);
            mergeSort(array, mid + 1, right, arrayVisualizer);
            merge(array, left, mid, right, arrayVisualizer); // Gọi đúng với 5 tham số
        }
    }

    private void merge(int[] array, int left, int mid, int right, ArrayVisualizer arrayVisualizer) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        MergeState state = new MergeState(0, 0, left);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.5), event -> {
            if (!(state.i < n1 && state.j < n2)) {
                while (state.i < n1) {
                    array[state.k] = leftArray[state.i];
                    state.i++;
                    state.k++;
                }
                while (state.j < n2) {
                    array[state.k] = rightArray[state.j];
                    state.j++;
                    state.k++;
                }
                arrayVisualizer.updateArrayView();
                timeline.stop();
            } else {
                if (state.i < n1 && (state.j >= n2 || leftArray[state.i] <= rightArray[state.j])) {
                    array[state.k] = leftArray[state.i];
                    state.i++;
                } else if (state.j < n2) {
                    array[state.k] = rightArray[state.j];
                    state.j++;
                }
                state.k++;
                arrayVisualizer.updateArrayView();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }



}
