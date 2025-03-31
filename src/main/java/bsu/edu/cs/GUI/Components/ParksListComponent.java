package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.Controller;
import bsu.edu.cs.GUI.MapManager;
import bsu.edu.cs.Parsers.Park;
import bsu.edu.cs.Parsers.ParkReviewInformation;
import bsu.edu.cs.Parsers.ReviewRetriever;
import bsu.edu.cs.Parsers.Ride;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.TextConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ParksListComponent {
    private final Controller controller = new Controller();
    RidesListComponent ridesListComponent = new RidesListComponent();
    private final ReviewRetriever reviewRetriever = new ReviewRetriever();
    private final ReviewsComponent reviewsComponent = new ReviewsComponent();
    private ParkReviewInformation reviews;

    public VBox createSideBar(Map<String, Park> parksMap, Alert errorPopUp, Label parkTitle, ListView<Ride> ridesList, VBox mainContent, WeatherComponent weatherComponent, MapManager mapManager, Button viewReviewsButton) {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(UIConstants.PADDING));
        sidebar.getStyleClass().add(CSSConstants.CLASS_SIDEBAR);

        TextField searchBar = new TextField();
        searchBar.setPromptText(TextConstants.SEARCH_PROMPT);

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
            parksList.getSelectionModel().clearSelection();
            Collections.sort(filteredList);
            parksList.setItems(filteredList);
        });

        Hyperlink contributionLink = new Hyperlink(TextConstants.QUEUE_TIMES_TEXT);
        contributionLink.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(TextConstants.QUEUE_TIMES_URL));
            } catch (Exception e) {
                errorPopUp.setContentText(TextConstants.LINK_ERROR);
                errorPopUp.showAndWait();
            }
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
                    getStyleClass().add(CSSConstants.CLASS_PARKS_CONTAINER);
                }
            }
        });

        sidebar.getChildren().addAll(searchBar, parksList, contributionLink);

        // Handle Park Selection
        parksList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals(oldValue)) {
                Park park = parksMap.get(newValue);
                mapManager.setCurrentPark(newValue);
                reviews = getReviewsForPark(park);
                viewReviewsButton.setOnAction(event -> reviewsComponent.showReviewsPopup(newValue, reviews));
                parkTitle.setText(newValue + TextConstants.RIDE_SUFFIX);
                ridesList.getItems().clear();
                List<Ride> rideList;
                try {
                    mapManager.createMap(park);
                    rideList = controller.fetchRides(park);

                    if (rideList.isEmpty()) {
                        rideList.add(new Ride(0, TextConstants.NO_RIDE_INFO, false, 0, "N/A"));
                    }

                    mainContent.getChildren().remove(1);
                    HBox weatherUpdated = weatherComponent.createWeatherDisplay(controller.getWeather(park.getLatitude(), park.getLongitude()));
                    weatherUpdated.setMaxWidth(UIConstants.WEATHER_MAX_WIDTH);
                    weatherUpdated.setMaxHeight(UIConstants.WEATHER_MAX_HEIGHT);
                    weatherUpdated.getStyleClass().add("weather-container");
                    mainContent.getChildren().add(1, weatherUpdated);

                } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                    rideList = new ArrayList<>();
                    rideList.add(new Ride(0, TextConstants.ERROR_RETRIEVING_RIDES, false, 0, "N/A"));
                }
                ridesList.setItems(FXCollections.observableArrayList(rideList));
                ridesListComponent.styleRidesList(ridesList, controller, mapManager);

            }
        });
        return sidebar;
    }
    private ParkReviewInformation getReviewsForPark(Park park) {
        try {
            ParkReviewInformation reviewInformation = reviewRetriever.getReviewInformation(park);
            if (reviewInformation == null) {
                System.out.println("No review information available for the park.");
            }
            return reviewInformation;
        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            System.out.println("Error retrieving review information: " + e.getMessage());
            return null;
        }
    }

}

