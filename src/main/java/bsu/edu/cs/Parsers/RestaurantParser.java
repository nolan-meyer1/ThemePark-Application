package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RestaurantParser extends Parser<List<Restaurant>>{

    public RestaurantParser(ApiInputStream inputStream){
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.results";
    }

    @Override
    protected List<Restaurant> convertData(JSONArray list) {
        List<Restaurant> restaurantsList = new ArrayList<>();
        Coordinates coordinates = null;
        Double priceLevel = null;
        Double rating = null;

        for(Object restaurant: list){

            if(restaurant instanceof LinkedHashMap<?, ?> restrauntHashMap){

                if(restrauntHashMap.get("geometry") instanceof LinkedHashMap<?, ?> geometryHashMap) {

                    if(geometryHashMap.get("location") instanceof LinkedHashMap<?, ?> locationHashMap) {
                        coordinates = new Coordinates(Double.toString((Double) locationHashMap.get("lat")),Double.toString((Double) locationHashMap.get("lng")));
                    }
                }

                if(restrauntHashMap.get("price_level") != null){
                    priceLevel = ((Number) restrauntHashMap.get("price_level")).doubleValue();
                }

                if(restrauntHashMap.get("rating") != null){
                    rating = ((Number) restrauntHashMap.get("rating")).doubleValue();
                }

                restaurantsList.add(new Restaurant((String) restrauntHashMap.get("name"),coordinates,rating,
                        priceLevel,(String) restrauntHashMap.get("place_id")));
            }
        }

        return restaurantsList;
    }
}
