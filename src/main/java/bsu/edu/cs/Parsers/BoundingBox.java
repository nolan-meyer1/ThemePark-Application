package bsu.edu.cs.Parsers;

public class BoundingBox {

    private final double minimumLatitude;
    private final double maximumLatitude;
    private final double minimumLongitude;
    private final double maximumLongitude;

    public BoundingBox(double minimumLatitude, double maximumLatitude,double minimumLongitude, double maximumLongitude){
        this.minimumLatitude = minimumLatitude;
        this.maximumLatitude = maximumLatitude;
        this.minimumLongitude = minimumLongitude;
        this.maximumLongitude = maximumLongitude;
    }

    public double getMinimumLatitude(){
        return minimumLatitude;
    }

    public double getMaximumLatitude() {
        return maximumLatitude;
    }

    public double getMinimumLongitude() {
        return minimumLongitude;
    }

    public double getMaximumLongitude() {
        return maximumLongitude;
    }

}
