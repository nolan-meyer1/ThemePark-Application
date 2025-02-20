package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkParser extends Parser<List<Park>> {

    public ParkParser(ApiInputStream inputStream){
        super(inputStream);
    }
    @Override
    protected String getQuery() {
        return "$..parks";
    }

    @Override
    protected List<Park> convertRevisionsToList(JSONArray list) {

        List<Park> parkList = new ArrayList<>();

        for(Object company:list){
            for(Object park: (JSONArray) company) {
                if (park instanceof LinkedHashMap<?, ?>) {
                    @SuppressWarnings("unchecked")
                    LinkedHashMap<String, ?> parkConverted = (LinkedHashMap<String, ?>) park;
                    parkList.add(new Park((int) parkConverted.get("id"), (String) parkConverted.get("name"), (String) parkConverted.get("country"),
                            (String) parkConverted.get("continent"), (String) parkConverted.get("latitude"), (String) parkConverted.get("longitude"),
                            (String) parkConverted.get("timezone")));
                }
            }
        }
        return parkList;
    }
}
