package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.Parsers.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUI extends Application {

    private final Controller controller = new Controller();
    private Map<String,Park> parksMap;
    String temperature = "30°C";
    String windSpeed = "25 km/h";
    String time = "11:32 PM";
    String humidity = "18%";

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
        sidebar.getStyleClass().add("side-bar");

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
        AtomicBoolean isDarkMode = new AtomicBoolean(false);
        Image sunIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sun.png")));
        Image moonIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/moon.png")));
        ImageView toggleIcon = new ImageView(sunIcon);
        toggleIcon.setFitHeight(20);
        toggleIcon.setFitWidth(20);
        Button toggleThemeButton = new Button();
        toggleThemeButton.setGraphic(toggleIcon);
        toggleThemeButton.getStyleClass().add("toggle-button");
        ListView<Ride> ridesList = new ListView<>();
        ridesList.getStyleClass().add("rides-container");
        HBox ridesHeader = new HBox(10);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        ridesHeader.getChildren().addAll(parkTitle, spacer, toggleThemeButton);
        ridesHeader.setAlignment(Pos.CENTER_LEFT);


        HBox weather = getWeather();
        weather.setMaxWidth(220);
        weather.setMaxHeight(400);
        weather.getStyleClass().add("weather-container");

        mainContent.getChildren().addAll(ridesHeader, weather, ridesList);
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
        Scene scene = new Scene(root, 1200, 600);
        toggleThemeButton.setOnAction(e -> {
            String theme = isDarkMode.get() ? "/style.css" : "/dark-mode.css";
            scene.getStylesheets().setAll(Objects.requireNonNull(getClass().getResource(theme)).toExternalForm());
            toggleIcon.setImage(isDarkMode.get() ? sunIcon : moonIcon);
            isDarkMode.set(!isDarkMode.get());
        });
        parksList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String park, boolean empty) {
                super.updateItem(park, empty);
                if (empty || park == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(park);
                    getStyleClass().add("parks-container");
                }
            }
        });
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Theme Park Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getWeather() {
        Label temperatureLabel = new Label(temperature);
        Label timeLabel = new Label(time);
        Label humidityLabel = new Label(humidity);
        Label windSpeedLabel = new Label(windSpeed);
        temperatureLabel.getStyleClass().addAll("white", "temp");
        timeLabel.getStyleClass().addAll("white", "time");
        humidityLabel.getStyleClass().add("white");
        windSpeedLabel.getStyleClass().add("white");

        HBox weatherSpeed = new HBox();
        weatherSpeed.getChildren().addAll(humidityLabel, windSpeedLabel);

        VBox weatherDetails = new VBox();
        weatherDetails.getChildren().addAll(timeLabel, humidityLabel, windSpeedLabel);

        ImageView weatherIcon = new ImageView(new Image("/rain.png"));
        VBox weatherImage = new VBox();
        weatherImage.getChildren().addAll(weatherIcon, temperatureLabel);

        weatherIcon.setFitHeight(100);
        weatherIcon.setFitWidth(100);

        return new HBox(10, weatherImage, weatherDetails);
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
                    getStyleClass().add("black");
                    Label nameLabel = new Label(ride.getName());
                    nameLabel.getStyleClass().add("ride-name");

                    Label waitTimeLabel = new Label();
                    if (ride.getIsOpen()) {
                        waitTimeLabel.setText(controller.convertMinToHours(ride.getWaitTime()));
                        waitTimeLabel.getStyleClass().addAll(controller.getWaitTimeColor(ride.getWaitTime()), "wait-time");
                    }

                    Label statusLabel = new Label(ride.getIsOpen() ? " OPENED" : " CLOSED");
                    statusLabel.getStyleClass().add(ride.getIsOpen() ? "status-open" : "status-closed");

                    HBox spacer = new HBox();
                    HBox.setHgrow(spacer, Priority.ALWAYS); // Acts like flex-grow to push elements apart

                    HBox rideInfoBox = new HBox(40, nameLabel, spacer, waitTimeLabel, statusLabel);
                    rideInfoBox.getStyleClass().add("ride-item");
                    rideInfoBox.setAlignment(Pos.CENTER_LEFT);
                    HBox.setHgrow(rideInfoBox, Priority.ALWAYS);

                    setGraphic(rideInfoBox);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
