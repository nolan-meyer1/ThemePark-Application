package bsu.edu.cs.Parsers;

public class Review {

    private final String authorName;
    private final String profilePhotoURL;
    private final double rating;
    private final String relativeTimeDescription;
    private final String text;

    public Review(String authorName,String profilePhotoURL, double rating, String relativeTimeDescription, String text){
        this.authorName = authorName;
        this.profilePhotoURL = profilePhotoURL;
        this.rating = rating;
        this.relativeTimeDescription = relativeTimeDescription;
        this.text = text;
    }

    private String getAuthorName(){
        return authorName;
    }

    private String getProfilePhotoURL(){
        return profilePhotoURL;
    }

    private double rating(){
        return rating;
    }

    private String getRelativeTimeDescription(){
        return relativeTimeDescription;
    }

    private String text(){
        return text;
    }

}
