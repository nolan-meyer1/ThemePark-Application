package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.RideConnection;
import bsu.edu.cs.InternetConnections.RidePositionConnection;
import bsu.edu.cs.Parsers.*;
import javafx.scene.web.WebEngine;

import java.util.List;

public class MapManager {

    private final WebEngine webEngine;
    private final RidePositionConnection ridePositionConnection = new RidePositionConnection();
    private List<Ride> ridesList;

    public MapManager(WebEngine webEngine){
        this.webEngine = webEngine;
    }

    public void createMap(Park park) throws noItemFoundException, networkErrorException, openInputStreamException {

        webEngine.executeScript(String.format("setMapView(%s, %s, 17)",park.getLatitude(),park.getLongitude()));
        ridesList = fetchRides(park.getId());

        for (Ride ride : ridesList) {

            RidePositionParser ridePositionParser = new RidePositionParser(new ApiInputStream(ridePositionConnection.search(new RidePositionSearch(ride.getName(), park))));
            Coordinates coordinates = ridePositionParser.parse();

            if (coordinates != null) {

                String convertedRideName = ride.getName().replace("'", "\\'");

                webEngine.executeScript(String.format("addMarker(%s, %s, '%s', %d)", coordinates.getLatitude(), coordinates.getLongitude(), convertedRideName, ride.getWaitTime()));
            }
        }
    }

    public List<Ride> fetchRides(int id) throws networkErrorException, openInputStreamException, noItemFoundException {
        RideConnection rideConnectionInstance = new RideConnection();
        RideParser rideParserInstance = new RideParser(new ApiInputStream(rideConnectionInstance.search(id)));
        return rideParserInstance.parse();
    }

    public List<Ride> getListOfRides(){
        return ridesList;
    }



}
