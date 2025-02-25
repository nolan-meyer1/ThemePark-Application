package bsu.edu.cs.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class GUI extends Application {
    private final Map<String, List<String>> parkRides = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        fetchParksAndRides();
        BorderPane root = new BorderPane();

        // Left Sidebar (10%)
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #271A89; -fx-padding: 20px;");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Parks...");

        ListView<String> parksList = new ListView<>();
        parksList.getItems().addAll(parkRides.keySet());

        sidebar.getChildren().addAll(searchBar, parksList);
        root.setLeft(sidebar);

        // Main Display Area (90%)
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(20));
        Label parkTitle = new Label("Select a Park");
        parkTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> ridesList = new ListView<>();

        mainContent.getChildren().addAll(parkTitle, ridesList);
        root.setCenter(mainContent);

        // Handle Park Selection
        parksList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                parkTitle.setText(newValue + " Rides");
                ridesList.getItems().clear();
                ridesList.getItems().addAll(parkRides.getOrDefault(newValue, Collections.emptyList()));
            }
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("National Parks Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void fetchParksAndRides() {
        parkRides.put("Yellowstone", Arrays.asList("Old Faithful Geyser - 20 min", "Grand Prismatic - 15 min"));
        parkRides.put("Yosemite", Arrays.asList("Half Dome Hike - 120 min", "Glacier Point - 30 min"));
        parkRides.put("Grand Canyon", Arrays.asList("Skywalker - 25 min", "Rim Trail - 40 min"));
        parkRides.put("Zion", Arrays.asList("Angels Landing - 90 min", "The Narrows - 60 min"));
        parkRides.put("Great Smoky Mountains", Arrays.asList("Cling mans Dome - 35 min", "Codes Cove Loop - 45 min"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
