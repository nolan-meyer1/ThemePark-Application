package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;

public class RidePositionParser extends Parser<Coordinates> {

    public RidePositionParser(ApiInputStream inputStream){
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.[*]";
    }

    @Override
    protected Coordinates convertData(JSONArray list) {

        String latitude = "";
        String longitude = "";

        if (list.get(0) instanceof LinkedHashMap<?, ?>) {
            @SuppressWarnings("unchecked")
            LinkedHashMap<String, ?> hashMapConverted = (LinkedHashMap<String, ?>) list.get(0);
            latitude = (String) hashMapConverted.get("lat");
            longitude = (String) hashMapConverted.get("lon");
        }
        return new Coordinates(latitude,longitude);
    }
}
