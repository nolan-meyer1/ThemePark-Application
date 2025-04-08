package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.GUIModel;
import bsu.edu.cs.GUI.MapManager;
import bsu.edu.cs.Parsers.Ride;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.TextConstants;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class RidesListComponent {
    public void styleRidesList(ListView<Ride> ridesList, GUIModel controller, MapManager mapManager) {

        ridesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                try {
                    mapManager.addMarker(newValue);
                } catch (noItemFoundException | networkErrorException | openInputStreamException e) {
                    mapManager.alert();
                }
            }
        });

        ridesList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Ride ride, boolean empty) {
                super.updateItem(ride, empty);
                setStyle("");
                if (empty || ride == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    getStyleClass().add(CSSConstants.CLASS_BLACK);
                    VBox rideInfoBox = new VBox(10);
                    rideInfoBox.getStyleClass().add(CSSConstants.CLASS_RIDE_ITEM);

                    Label nameLabel = new Label(ride.getName());
                    nameLabel.getStyleClass().addAll(CSSConstants.CLASS_RIDE_NAME, CSSConstants.CLASS_CLICKABLE_LABEL);

                    VBox detailsBox = new VBox(5);
                    Label waitTimeLabel = new Label();
                    if (ride.getIsOpen()) {
                        waitTimeLabel.setText(controller.convertMinToHours(ride.getWaitTime()));
                        waitTimeLabel.getStyleClass().addAll(
                                controller.getWaitTimeColor(ride.getWaitTime()),
                                CSSConstants.CLASS_WAIT_TIME
                        );
                    }

                    Label statusLabel = new Label(ride.getIsOpen() ? TextConstants.OPEN_STATUS : TextConstants.CLOSED_STATUS);
                    statusLabel.getStyleClass().add(
                            ride.getIsOpen() ? CSSConstants.CLASS_STATUS_OPEN : CSSConstants.CLASS_STATUS_CLOSED
                    );

                    detailsBox.getChildren().addAll(waitTimeLabel, statusLabel);

                    rideInfoBox.getChildren().addAll(nameLabel, detailsBox);

                    setGraphic(rideInfoBox);
                }
            }
        });
    }
}

