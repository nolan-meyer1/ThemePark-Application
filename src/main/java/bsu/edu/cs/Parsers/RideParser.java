package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RideParser extends Parser<List<Ride>> {

    public RideParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$..rides[*]";
    }

    @Override
    protected List<Ride> convertRevisionsToList(JSONArray list) {
        List<Ride> ridesList = new ArrayList<>();

        for (Object rideInstance : list) {
            if (rideInstance instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                LinkedHashMap<String, ?> rideConverted = (LinkedHashMap<String, ?>) rideInstance;
                ridesList.add(new Ride(
                        (int) rideConverted.get("id"),
                        (String) rideConverted.get("name"),
                        (Boolean) rideConverted.get("is_open"),
                        ((Number) rideConverted.get("wait_time")).intValue(),
                        (String) rideConverted.get("last_updated")
                ));
            }
        }
        return ridesList;
    }
}
