package bsu.edu.cs.InternetConnections;

public class RideConnection extends InternetConnection<Integer>{

    @Override
    public String createRequestUrl(Integer searchItem) {
        return "https://queue-times.com/parks/"+searchItem+"/queue_times.json";
    }

}