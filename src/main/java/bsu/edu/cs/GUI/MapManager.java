package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.RideConnection;
import bsu.edu.cs.InternetConnections.RidePositionConnection;
import bsu.edu.cs.Parsers.*;
import bsu.edu.cs.Utils.AlertConstants;
import bsu.edu.cs.Utils.TextConstants;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;

import java.util.HashMap;
import java.util.List;

public class MapManager {

    private final WebEngine webEngine;
    private final RidePositionConnection ridePositionConnection = new RidePositionConnection();
    private List<Ride> ridesList;

    private final Alert noRideFound = new Alert(Alert.AlertType.ERROR);

    private HashMap<String,Coordinates> ridePositions;

    public MapManager(WebEngine webEngine){
        this.webEngine = webEngine;

        noRideFound.setTitle(AlertConstants.ERROR_TITLE);
        noRideFound.getDialogPane().getStyleClass().add(AlertConstants.ALERT_CLASS);
    }

    public void createMap(Park park) throws noItemFoundException, networkErrorException, openInputStreamException {

        webEngine.executeScript(String.format("setMapView(%s, %s, 18)",park.getLatitude(),park.getLongitude()));
        ridesList = fetchRides(park.getId());
        ridePositions = new HashMap<>();

        for (Ride ride : ridesList) {

            RidePositionParser ridePositionParser = new RidePositionParser(new ApiInputStream(ridePositionConnection.search(new RidePositionSearch(ride.getName(), park))));
            Coordinates coordinates = ridePositionParser.parse();

            if (coordinates != null) {

                String convertedRideName = ride.getName().replace("'", "\\'");
                ridePositions.put(ride.getName(),coordinates);
                webEngine.executeScript(String.format("addMarker(%s, %s, '%s', %d)", coordinates.getLatitude(), coordinates.getLongitude(), convertedRideName, ride.getWaitTime()));
            }
        }
    }

    public List<Ride> fetchRides(int id) throws networkErrorException, openInputStreamException, noItemFoundException {
        RideConnection rideConnectionInstance = new RideConnection();
        RideParser rideParserInstance = new RideParser(new ApiInputStream(rideConnectionInstance.search(id)));
        return rideParserInstance.parse();
    }

    public void recenterToRide(String rideName){
        Coordinates coordinates = ridePositions.get(rideName);
        if(coordinates != null) {
            webEngine.executeScript(String.format("setMapView(%s, %s, 18)", coordinates.getLatitude(), coordinates.getLongitude()));
        }else{
            noRideFound.setContentText(TextConstants.NO_RIDE_FOUND);
            noRideFound.showAndWait();
        }
    }

    public List<Ride> getListOfRides(){
        return ridesList;
    }



}
