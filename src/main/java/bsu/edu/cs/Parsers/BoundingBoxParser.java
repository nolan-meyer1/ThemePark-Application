package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;

public class BoundingBoxParser extends Parser<BoundingBox> {

    public BoundingBoxParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.[*]";
    }

    @Override
    protected BoundingBox convertData(JSONArray list) {
        String minimumLatitude = "";
        String maximumLatitude = "";
        String minimumLongitude = "";
        String maximumLongitude = "";

        if (list.get(0) instanceof LinkedHashMap<?, ?>) {
            @SuppressWarnings("unchecked")
            LinkedHashMap<String, ?> hashMapConverted = (LinkedHashMap<String, ?>) list.get(0);

            if(hashMapConverted.containsKey("boundingbox")){
                JSONArray boundingBoxArray = (JSONArray) hashMapConverted.get("boundingbox");

                if (boundingBoxArray.size() == 4){
                    minimumLatitude = (String) boundingBoxArray.get(0);
                    maximumLatitude = (String) boundingBoxArray.get(1);
                    minimumLongitude = (String) boundingBoxArray.get(2);
                    maximumLongitude = (String) boundingBoxArray.get(3);
                }
            }

        }
        return new BoundingBox(minimumLatitude, maximumLatitude, minimumLongitude, maximumLongitude);
    }

}
