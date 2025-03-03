package bsu.edu.cs.InternetConnections;

public class RideConnection extends InternetConnection<Integer>{
    private Integer searchItem;

    @Override
    public String createRequestUrl(Integer searchItem) {
        return "https://queue-times.com/parks/"+searchItem+"/queue_times.json";
    }
    public Integer getSearchItem() {
        return searchItem;
    }
}