package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.Parsers.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class GUI extends Application {

    private final Controller controller = new Controller();
    private Map<String,Park> parksMap;

    @Override
    public void start(Stage primaryStage) {
        Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
        errorPopUp.setTitle("Error!");
        errorPopUp.getDialogPane().getStyleClass().add("alert");

        try {
            parksMap = controller.fetchParks();
        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            errorPopUp.setContentText("There was an error! Please check internet connection");
            errorPopUp.showAndWait();
            return;
        }

        BorderPane root = new BorderPane();

        // Left Sidebar (10%)
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.getStyleClass().add("sidebar");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Parks...");

        ListView<String> parksList = new ListView<>();
        ObservableList<String> sortedList = FXCollections.observableArrayList(parksMap.keySet());
        Collections.sort(sortedList);
        parksList.getItems().addAll(sortedList);

        searchBar.setOnKeyTyped(event -> {
            String searchText = searchBar.getText().toLowerCase();
            ObservableList<String> filteredList = FXCollections.observableArrayList();

            for (String park : parksMap.keySet()) {
                if (park.toLowerCase().startsWith(searchText)) {
                    filteredList.add(park);
                }
            }

            Collections.sort(filteredList);
            parksList.setItems(filteredList);
        });

        Hyperlink contributionLink = new Hyperlink("Powered by Queue-Times.com");
        contributionLink.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://queue-times.com/en-US"));
            } catch (IOException | URISyntaxException e) {
                errorPopUp.setContentText("Can't open this link! Please try again!");
                errorPopUp.showAndWait();
            }
        });

        sidebar.getChildren().addAll(searchBar, parksList,contributionLink);
        root.setLeft(sidebar);

        // Main Display Area (90%)
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(20));
        mainContent.getStyleClass().add("main-content");
        Label parkTitle = new Label("Select a Park");
        parkTitle.getStyleClass().add("park-title");
        ListView<Ride> ridesList = new ListView<>();

        mainContent.getChildren().addAll(parkTitle, ridesList);
        root.setCenter(mainContent);

        // Handle Park Selection
        parksList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                parkTitle.setText(newValue + " Rides");
                ridesList.getItems().clear();
                List<Ride> rideList;
                try {
                    rideList = controller.getRides(parksMap.get(newValue).getId());

                    if(rideList.isEmpty()){
                        rideList.add(new Ride(0,"No ride information available", false, 0, "N/A"));
                    }

                } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                    rideList = new ArrayList<>();
                    rideList.add(new Ride(0,"Error retrieving ride information! Please check internet connection and try again!", false, 0, "N/A"));
                }

                ridesList.setItems(FXCollections.observableArrayList(rideList));
                styleRidesList(ridesList);

            }
        });

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Theme Park Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleRidesList(ListView<Ride> ridesList){
        ridesList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Ride ride, boolean empty) {
                super.updateItem(ride, empty);

                setStyle("");

                if (empty || ride == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nameLabel = new Label(ride.getName());
                    nameLabel.setMinWidth(380);

                    Label waitTimeLabel = new Label();
                    if(ride.getIsOpen()){
                        waitTimeLabel.setText(controller.convertMinToHours(ride.getWaitTime()));
                        waitTimeLabel.getStyleClass().add(controller.getWaitTimeColor(ride.getWaitTime()));
                    }
                    waitTimeLabel.setMinWidth(60);

                    Label statusLabel = new Label(ride.getIsOpen() ? " OPENED" : " CLOSED");
                    statusLabel.getStyleClass().add(ride.getIsOpen() ? "statusOpen" : "statusClosed");
                    statusLabel.setMinWidth(60);

                    HBox hbox = new HBox(10, nameLabel, waitTimeLabel, statusLabel);
                    getStyleClass().add("listElement");

                    setGraphic(hbox);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
