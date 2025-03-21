package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

public class PlaceIDParser extends Parser<String>{
    public PlaceIDParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$..place_id";
    }

    @Override
    protected String convertData(JSONArray list) {
        return (String) list.get(0);
    }
}
