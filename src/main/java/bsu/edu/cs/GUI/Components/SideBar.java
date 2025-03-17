package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Parsers.Park;
import bsu.edu.cs.Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

public class SideBar {

    public VBox createSideBar(Map<String, Park> parksMap, Alert errorPopUp) {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(Constants.PADDING));
        sidebar.getStyleClass().add(Constants.CLASS_SIDEBAR);

        TextField searchBar = new TextField();
        searchBar.setPromptText(Constants.SEARCH_PROMPT);

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

        Hyperlink contributionLink = new Hyperlink(Constants.QUEUE_TIMES_TEXT);
        contributionLink.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(Constants.QUEUE_TIMES_URL));
            } catch (Exception e) {
                errorPopUp.setContentText(Constants.LINK_ERROR);
                errorPopUp.showAndWait();
            }
        });

        sidebar.getChildren().addAll(searchBar, parksList, contributionLink);
        return sidebar;
    }
}
