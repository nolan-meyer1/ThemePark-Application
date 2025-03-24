package bsu.edu.cs.Parsers;

public class BoundingBox {

    private final String minimumLatitude;
    private final String maximumLatitude;
    private final String minimumLongitude;
    private final String maximumLongitude;

    public BoundingBox(String minimumLatitude, String maximumLatitude,String minimumLongitude, String maximumLongitude){
        this.minimumLatitude = minimumLatitude;
        this.maximumLatitude = maximumLatitude;
        this.minimumLongitude = minimumLongitude;
        this.maximumLongitude = maximumLongitude;
    }

    public String getMinimumLatitude(){
        return minimumLatitude;
    }

    public String getMaximumLatitude() {
        return maximumLatitude;
    }

    public String getMinimumLongitude() {
        return minimumLongitude;
    }

    public String getMaximumLongitude() {
        return maximumLongitude;
    }

}
