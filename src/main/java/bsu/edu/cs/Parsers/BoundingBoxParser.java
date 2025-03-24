package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

public class BoundingBoxParser extends Parser<Coordinates> {

    public BoundingBoxParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.[*]";
    }

    @Override
    protected Coordinates convertData(JSONArray list) {
        return null;
    }
}
