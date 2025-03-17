package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.GUI.Controller;
import bsu.edu.cs.Parsers.Ride;
import bsu.edu.cs.Utils.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class RidesList {
    public void styleRidesList(ListView<Ride> ridesList, Controller controller) {
        ridesList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Ride ride, boolean empty) {
                super.updateItem(ride, empty);
                setStyle("");
                if (empty || ride == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    getStyleClass().add(Constants.CLASS_BLACK);
                    Label nameLabel = new Label(ride.getName());
                    nameLabel.getStyleClass().add(Constants.CLASS_RIDE_NAME);

                    Label waitTimeLabel = new Label();
                    if (ride.getIsOpen()) {
                        waitTimeLabel.setText(controller.convertMinToHours(ride.getWaitTime()));
                        waitTimeLabel.getStyleClass().addAll(controller.getWaitTimeColor(ride.getWaitTime()), Constants.CLASS_WAIT_TIME);
                    }

                    Label statusLabel = new Label(ride.getIsOpen() ? Constants.OPEN_STATUS : Constants.CLOSED_STATUS);
                    statusLabel.getStyleClass().add(ride.getIsOpen() ? Constants.CLASS_STATUS_OPEN : Constants.CLASS_STATUS_CLOSED);

                    HBox spacer = new HBox();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    HBox rideInfoBox = new HBox(40, nameLabel, spacer, waitTimeLabel, statusLabel);
                    rideInfoBox.getStyleClass().add(Constants.CLASS_RIDE_ITEM);
                    rideInfoBox.setAlignment(Pos.CENTER_LEFT);
                    HBox.setHgrow(rideInfoBox, Priority.ALWAYS);

                    setGraphic(rideInfoBox);
                }
            }
        });
    }
}
