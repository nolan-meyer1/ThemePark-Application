package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.ParkConnection;
import bsu.edu.cs.InternetConnections.RideConnection;
import bsu.edu.cs.Parsers.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class GUI extends Application {
    private final ParkConnection parkConnection = new ParkConnection();
    private ParkParser parkParser;
    private Map<String, Park> parksMap;
    private final RideConnection rideConnection = new RideConnection();
    private RideParser rideParser;

    @Override
    public void start(Stage primaryStage) throws noItemFoundException, networkErrorException, openInputStreamException {
        fetchParks();
        BorderPane root = new BorderPane();

        // Left Sidebar (10%)
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.getStyleClass().add("sidebar");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Parks...");

        ListView<String> parksList = new ListView<>();
        ObservableList<String> sortedList = FXCollections.observableArrayList(parksMap.keySet());
        alphabeticalSort(sortedList);
        parksList.getItems().addAll(sortedList);

        searchBar.setOnKeyTyped(event -> {
            String searchText = searchBar.getText().toLowerCase();
            ObservableList<String> filteredList = FXCollections.observableArrayList();

            for (String park : parksMap.keySet()) {
                if (park.toLowerCase().startsWith(searchText)) {
                    filteredList.add(park);
                }
            }

            alphabeticalSort(filteredList);
            parksList.setItems(filteredList);
        });


        sidebar.getChildren().addAll(searchBar, parksList);
        root.setLeft(sidebar);

        // Main Display Area (90%)
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(20));
        mainContent.getStyleClass().add("main-content");
        Label parkTitle = new Label("Select a Park");
        parkTitle.getStyleClass().add("park-title");
        ListView<String> ridesList = new ListView<>();

        mainContent.getChildren().addAll(parkTitle, ridesList);
        root.setCenter(mainContent);

        // Handle Park Selection
        parksList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                parkTitle.setText(newValue + " Rides");
                ridesList.getItems().clear();
                try {
                   rideParser = new RideParser(new ApiInputStream(rideConnection.search(parksMap.get(newValue).getId())));
                   List<Ride> rideList = rideParser.parse();

                   for(Ride ride: rideList){
                       ridesList.getItems().add(
                               ride.getName() + " | Wait Time: " + ride.getWaitTime() + " min | Is Open: " + (ride.getIsOpen() ? "Yes" : "No")
                       );
                   }

                   if(rideList.isEmpty()){
                       ridesList.getItems().add("No ride information can be found for this park!");
                   }

                } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Theme Park Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void fetchParks() throws networkErrorException, openInputStreamException, noItemFoundException {
        //Search is left blank because we are just grabbing the parks so there's no value to give it but a blank string
        ApiInputStream apiInputStream = new ApiInputStream(parkConnection.search(""));
        parkParser = new ParkParser(apiInputStream);
        parksMap = parkParser.parse();
    }

    private void alphabeticalSort(ObservableList<String> list){

        String temp;

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size() -1 - i; j++){
                if(list.get(j).charAt(0) > list.get(j+1).charAt(0)){
                    temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
