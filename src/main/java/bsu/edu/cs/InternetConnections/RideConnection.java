package bsu.edu.cs.InternetConnections;

public class RideConnection extends InternetConnection<Integer>{

    @Override
    protected String createRequestUrl(Integer searchItem) {
        //Parameter doesn't need to be used because this simply is fetching one item
        return "https://queue-times.com/parks/"+searchItem+"/queue_times.json";
    }
}