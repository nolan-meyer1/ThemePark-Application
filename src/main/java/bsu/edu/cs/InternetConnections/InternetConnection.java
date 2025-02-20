package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Exceptions.networkErrorException;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class InternetConnection<T> {
    public InputStream search(T searchItem) throws networkErrorException {
        String url = createRequestUrl(searchItem);
        return getInputStream(url);
    }

    protected InputStream getInputStream(String url) throws networkErrorException {
        InputStream output;
        try{
            @SuppressWarnings("deprecation")
            URL urlConnection = new URL(url);
            URLConnection connection = urlConnection.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (nolan.meyer@bsu.edu)");
            output = connection.getInputStream();
        }catch (Exception e) {
            throw new networkErrorException();
        }
        return output;
    }

    protected abstract String createRequestUrl(T searchItem);

}
