package bsu.edu.cs.InternetConnections;

public class WeatherConnection extends InternetConnection<String[]> {

    private static final String API_KEY = "1b0f4529f6b03a65738f138508968d2b";

    @Override
    public String createRequestUrl(String[] latitudeAndLongitude) {
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + latitudeAndLongitude[0] + "&lon=" + latitudeAndLongitude[1] + "&appid=" + API_KEY + "&units=imperial";
    }

}