package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantTest {

    @Test
    public void restaurantGetNameTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals("Rainforest Cafe",testRestaurant.getName());
    }

    @Test
    public void restaurantGetCoordinatesLatTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates("28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals("28.3546441",testRestaurant.getCoordinates().getLatitude());
    }

    @Test
    public void restaurantGetCoordinatesLngTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates("28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals("-81.59077189999999",testRestaurant.getCoordinates().getLongitude());
    }

    @Test
    public void restaurantGetRatingTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals(4.4,testRestaurant.getRating());
    }

    @Test
    public void restaurantGetPriceLevelTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals(2.0,testRestaurant.getPriceLevel());
    }

    @Test
    public void restaurantGetPlaceIDTest(){
        Restaurant testRestaurant = new Restaurant("Rainforest Cafe",new Coordinates(" 28.3546441","-81.59077189999999"),4.4, 2.0,"ChIJVVVVFbNi54gRtxun5fsZ9eg");
        assertEquals("ChIJVVVVFbNi54gRtxun5fsZ9eg",testRestaurant.getPlaceID());
    }

}
