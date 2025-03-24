package bsu.edu.cs.Parsers;

import java.util.List;

public class ParkReviewInformation {

    private final double rating;
    private final List<Review> listOfReviews;

    public ParkReviewInformation(double rating,List<Review> listOfReviews){
        this.rating = rating;
        this.listOfReviews = listOfReviews;
    }

    public double getRating(){
        return rating;
    }

    public List<Review> getListOfReviews(){
        return listOfReviews;
    }

}
