import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class ArrayVisualizer extends HBox {

    private int[] array;
    private SortingAlgorithm sortingAlgorithm;

    public ArrayVisualizer() {
        setSpacing(2);
        setAlignment(Pos.CENTER);
    }

    public void createArray() {
        int size = 10;
        array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100);
            Rectangle rect = createRectangle(array[i], Color.BLUE);
            getChildren().add(rect);
        }
    }

    public void startSorting() {
        if (sortingAlgorithm != null) {
            sortingAlgorithm.sort(array, this);
        }
    }

    public void setSortingAlgorithm(SortingAlgorithm sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public void updateArrayView() {
        getChildren().clear();
        for (int value : array) {
            StackPane rectangleWithLabel = createRectangleWithLabel(value, Color.BLUE);
            getChildren().add(rectangleWithLabel);
        }
    }
    public void promptForArraySize() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Array Size Input");
        dialog.setHeaderText("Enter the size of the array:");
        dialog.setContentText("Size:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(size -> {
            int arraySize = Integer.parseInt(size);
            promptForArrayValues(arraySize);
        });
    }
    private StackPane createRectangleWithLabel(int value, Color color) {
        Rectangle rect = new Rectangle(20, value * 5);
        rect.setFill(color);

        Text text = new Text(String.valueOf(value));
        text.setFill(Color.WHITE); // Chọn màu sắc sao cho dễ đọc trên màu của hình chữ nhật

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER); // Căn giữa Text trên Rectangle
        stackPane.getChildren().addAll(rect, text);

        return stackPane;
    }
    public void promptForArrayValues(int size) {
        this.array = new int[size];

        for (int i = 0; i < size; i++) {
            final int index = i; // Final variable for use in lambda

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Array Value Input");
            dialog.setHeaderText("Enter value for position " + (index + 1) + ":");
            dialog.setContentText("Value:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(value -> {
                // Use the final variable 'index' here instead of 'i'
                this.array[index] = Integer.parseInt(value);
            });
        }

        updateArrayView();
    }


    public HBox getVisualization() {
        return this; // You can adjust this based on your actual visualization components
    }

    private Rectangle createRectangle(int value, Color color) {
        Rectangle rect = new Rectangle(20, value * 5);
        rect.setFill(color);
        return rect;
    }
}
