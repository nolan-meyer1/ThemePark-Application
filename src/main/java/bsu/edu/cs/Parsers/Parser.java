package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public abstract class Parser <T> {

    protected ApiInputStream inputStreamInstance;

    public Parser(ApiInputStream inputStream){
        this.inputStreamInstance = inputStream;
    }

    public T parse() throws noItemFoundException {
        JSONArray parsedRevisions = extractRevisions(new ByteArrayInputStream(this.inputStreamInstance.inputStream));
        return convertRevisionsToList(parsedRevisions);
    }

    protected JSONArray extractRevisions(InputStream inputStreamInstance) throws noItemFoundException {
        JSONArray output;
        try {
            JSONArray revisionArray = JsonPath.read(inputStreamInstance,getQuery());
            output = (JSONArray) revisionArray.getFirst();
        }catch (Exception e) {
            throw new noItemFoundException();
        }
        return output;
    }

    protected abstract String getQuery();
    protected abstract T convertRevisionsToList(JSONArray list);
}
