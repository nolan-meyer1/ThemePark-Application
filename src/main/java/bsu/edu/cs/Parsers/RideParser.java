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

        for (Object rideItem : list) {
            if (rideItem instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                LinkedHashMap<String, ?> rideConverted = (LinkedHashMap<String, ?>) rideItem;
                ridesList.add(new Ride(
                        (int) rideConverted.get("id"),
                        (String) rideConverted.get("name"),
                        (Boolean) rideConverted.get("is_open"),
                        ((Number) rideConverted.get("wait_time")).intValue(),
                        (String) rideConverted.get("last_updated")
                ));
            }
        }
        alphabeticalSort(ridesList);
        return ridesList;
    }

    private void alphabeticalSort(List<Ride> list){

        Ride temp;

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size() -1 - i; j++){
                if(list.get(j).getName().charAt(0) > list.get(j+1).getName().charAt(0)){
                    temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
    }
}