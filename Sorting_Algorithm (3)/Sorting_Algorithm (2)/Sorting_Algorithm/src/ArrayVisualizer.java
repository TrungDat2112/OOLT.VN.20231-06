    import javafx.scene.layout.HBox;
    import javafx.geometry.Pos;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Rectangle;
    import javafx.scene.control.TextInputDialog;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.application.Platform;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;
    import javafx.scene.Node;

    public class ArrayVisualizer extends HBox {

        private int[] array;
        private SortingAlgorithm sortingAlgorithm;
        private String currentAlgorithmName = "";

        public ArrayVisualizer() {
            setSpacing(2);
            setAlignment(Pos.CENTER);
        }

        public void createArray() {
            int size = 35; // Thay đổi số lượng phần tử ở đây
            this.array = new int[size];

            for(int i = 0; i < size; ++i) {
                this.array[i] = (int)(Math.random() * 100.0);
                Rectangle rect = this.createRectangle(this.array[i], Color.BLUE);
                this.getChildren().add(rect);
            }
        }

        private Optional<String> getUserChoice(String[] choices) {
            List<ButtonType> buttons = Arrays.stream(choices)
                    .map(ButtonType::new)
                    .collect(Collectors.toList());

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Array Creation");
            alert.setHeaderText("Choose array creation method:");
            alert.getButtonTypes().addAll(buttons);

            Optional<ButtonType> result = alert.showAndWait();

            return result.map(ButtonType::getText);
        }

        private void createRandomArray() {
            int size = 20;
            array = new int[size];

            int range = currentAlgorithmName.equals("CountingSort") ? 10 : 100;

            for (int i = 0; i < size; i++) {
                array[i] = (int) (Math.random() * range);
                Rectangle rect = createRectangle(array[i], Color.BLUE);
                getChildren().add(rect);
            }
        }


        private void createUserInputArray() {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("User Input");
            dialog.setHeaderText("Enter array elements separated by spaces:");
            dialog.setContentText("Array:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                String[] inputValues = result.get().split("\\s+");

                try {
                    array = new int[inputValues.length];

                    for (int i = 0; i < inputValues.length; i++) {
                        array[i] = Integer.parseInt(inputValues[i]);
                    }

                    updateArrayView();
                } catch (NumberFormatException e) {
                    showErrorAlert("Invalid Input", "Please enter valid integer values.");
                }
            }
        }

        private void showErrorAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public void startSorting() {
            if (sortingAlgorithm != null) {
                new Thread(() -> {
                    sortingAlgorithm.sort(array, this);
                    Platform.runLater(this::updateArrayView);
                }).start();
            }
        }

        public void setSortingAlgorithm(SortingAlgorithm sortingAlgorithm) {
            this.sortingAlgorithm = sortingAlgorithm;
            this.currentAlgorithmName = sortingAlgorithm.getClass().getSimpleName();
        }
        public void highlight(int index) {
            Rectangle rect = (Rectangle) this.getChildren().get(index);
            rect.setFill(Color.RED);
        }

        public void unhighlight(int index) {
            Rectangle rect = (Rectangle) this.getChildren().get(index);
            rect.setFill(Color.BLUE);
        }

        public void resetColors() {
            Platform.runLater(() -> {
                for (Node child : getChildren()) {
                    if (child instanceof Rectangle) {
                        ((Rectangle) child).setFill(Color.BLUE);
                    }
                }
            });
        }

        public void updateArrayView() {
            Platform.runLater(() -> {
                getChildren().clear();
                for (int value : array) {
                    Rectangle rect = createRectangle(value, chooseColor(value));
                    getChildren().add(rect);
                }
            });
        }
        public void sleep(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


        private Color chooseColor(int value) {
            return value < 50 ? Color.RED : Color.BLUE; // Tùy chỉnh màu sắc tại đây
        }

        private Rectangle createRectangle(int value, Color color) {
            Rectangle rect = new Rectangle(20, value * 5); // Tùy chỉnh kích thước của hình chữ nhật
            rect.setFill(color);
            return rect;
        }
    }
