package bsu.edu.cs.Parsers;

public class RidePositionSearch {
    private final String rideName;
    private final BoundingBox boundingBox;

    public RidePositionSearch(String rideName, BoundingBox boundingBox){
        this.rideName = rideName;
        this.boundingBox = boundingBox;
    }

    public String getRideName(){
        return rideName;
    }

    public BoundingBox getBoundingBox(){
        return boundingBox;
    }

}
