package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ReviewParser extends Parser<ParkReviewInformation>{
    public ReviewParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.result[*]";
    }

    @Override
    protected ParkReviewInformation convertData(JSONArray list) {

        ParkReviewInformation output;
        List<Review> listOfReviews = new ArrayList<>();
        Object reviews = list.get(2);

        Number parkRating = (Number) list.get(1);
        double parkRatingAsDouble = parkRating.doubleValue();

            for(Object review: (JSONArray) reviews){
                @SuppressWarnings("unchecked")
                LinkedHashMap<String,?> convertedReview = (LinkedHashMap<String, ?>) review;

                Number reviewRating = (Number) convertedReview.get("rating");
                double reviewRatingAsDouble =  reviewRating.doubleValue();

                listOfReviews.add(new Review((String) convertedReview.get("author_name"), (String) convertedReview.get("profile_photo_url"),
                        reviewRatingAsDouble,(String) convertedReview.get("relative_time_description"),
                        (String) convertedReview.get("text")));
            }

            output = new ParkReviewInformation(parkRatingAsDouble,listOfReviews);

        return output;
    }
}
