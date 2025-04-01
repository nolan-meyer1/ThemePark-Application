package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.PlaceIDConnection;
import bsu.edu.cs.InternetConnections.ReviewConnection;

public class ReviewRetriever {

    private final PlaceIDConnection placeIDConnectionInstance = new PlaceIDConnection();
    private final ReviewConnection reviewConnectionInstance = new ReviewConnection();

    public ParkReviewInformation getReviewInformation(Park park) throws networkErrorException, openInputStreamException, noItemFoundException {

        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(placeIDConnectionInstance.search(park)));
        String placeID = placeIDParserInstance.parse();

        ReviewParser reviewParserInstance = new ReviewParser(new ApiInputStream(reviewConnectionInstance.search(placeID)));

        return reviewParserInstance.parse();
    }

}
