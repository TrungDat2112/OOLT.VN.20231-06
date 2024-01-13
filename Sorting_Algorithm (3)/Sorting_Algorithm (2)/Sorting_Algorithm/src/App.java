    // App.java
    import javafx.application.Application;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;

    public class App extends Application {

        private Stage primaryStage;
        private VBox mainMenu;
        private VBox demoMenu;
        private ArrayVisualizer arrayVisualizer;

        @Override
        public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Sorting Algorithm Demo");

            createMainMenu();
            createDemoMenu();

            Scene scene = new Scene(mainMenu, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void createMainMenu() {
            mainMenu = new VBox(10);
            mainMenu.setAlignment(Pos.CENTER);

            Label title = new Label("Sorting Algorithm Demo");
            title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

            Button mergeSortBtn = new Button("Merge Sort");
            mergeSortBtn.setOnAction(e -> startDemo(new MergeSort()));

            Button countingSortBtn = new Button("Counting Sort");
            countingSortBtn.setOnAction(e -> startDemo(new CountingSort()));

            Button radixSortBtn = new Button("Radix Sort");
            radixSortBtn.setOnAction(e -> startDemo(new RadixSort()));

            Button helpBtn = new Button("Help");
            helpBtn.setOnAction(e -> showHelp());

            Button quitBtn = new Button("Quit");
            quitBtn.setOnAction(e -> askForConfirmation());

            mainMenu.getChildren().addAll(title, mergeSortBtn, countingSortBtn, radixSortBtn, helpBtn, quitBtn);
        }

        private void createDemoMenu() {
            demoMenu = new VBox(10);
            demoMenu.setAlignment(Pos.CENTER);

            arrayVisualizer = new ArrayVisualizer();

            Button createArrayBtn = new Button("Create Array");
            createArrayBtn.setOnAction(e -> arrayVisualizer.createArray());

            Button startSortBtn = new Button("Start Sorting");
            startSortBtn.setOnAction(e -> arrayVisualizer.startSorting());

            Button backBtn = new Button("Back to Main Menu");
            backBtn.setOnAction(e -> backToMainMenu());

            demoMenu.getChildren().addAll(arrayVisualizer, createArrayBtn, startSortBtn, backBtn);
        }


        private void startDemo(SortingAlgorithm sortingAlgorithm) {
            arrayVisualizer.setSortingAlgorithm(sortingAlgorithm);
            primaryStage.setScene(new Scene(demoMenu, 800, 600));
        }

        private void showHelp() {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("This is a sorting algorithm demonstration application.\n"
                    + "Choose a sorting algorithm from the main menu and follow the steps to visualize the sorting process.");

            alert.showAndWait();
        }

        private void askForConfirmation() {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Quit Application");
            alert.setContentText("Are you sure you want to quit?");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == yesButton) {
                    primaryStage.close();
                }
            });
        }

        private void backToMainMenu() {
            primaryStage.setScene(new Scene(mainMenu, 400, 300));
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
