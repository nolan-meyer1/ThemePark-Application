package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.ParkConnection;
import bsu.edu.cs.InternetConnections.RideConnection;
import bsu.edu.cs.Parsers.*;

import java.util.List;
import java.util.Map;

public class Controller {

    public Map<String, Park> fetchParks() throws networkErrorException, openInputStreamException, noItemFoundException {
        ParkConnection parkConnection = new ParkConnection();
        //Search is left blank because we are just grabbing the parks so there's no value to give it but a blank string
        ApiInputStream apiInputStream = new ApiInputStream(parkConnection.search(""));
        ParkParser parkParser = new ParkParser(apiInputStream);
        return parkParser.parse();
    }


    public String getWaitTimeColor(int waitTime){
        String output;

        if(waitTime < 45){
            output = "low-wait-time";
        }else if(waitTime <= 90){
            output = "medium-wait-time";
        }else{
            output = "high-wait-time";
        }
        return output;
    }

    public String convertMinToHours(int minutes){

        String output;
        int hours;
        int extraMinutes;

        if(minutes < 60){
            output = minutes + " min";
        }else if(minutes % 60 == 0){
            hours = minutes / 60;
            output = String.format("%d hr",hours);
        }else{
            hours = minutes / 60;
            extraMinutes = minutes % 60;
            output = String.format("%d hr %d min",hours,extraMinutes);
        }

        return output;
    }

    public List<Ride> getRides(int id) throws networkErrorException, openInputStreamException, noItemFoundException {
        RideConnection rideConnection = new RideConnection();
        RideParser rideParser = new RideParser(new ApiInputStream(rideConnection.search(id)));
        return rideParser.parse();
    }

}
