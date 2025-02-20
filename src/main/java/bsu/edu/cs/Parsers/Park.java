package bsu.edu.cs.Parsers;

public class Park {
    private int id;
    private String name;
    private String country;
    private String continent;
    private String latitude;
    private String longitude;
    private String timezone;

    public Park(int id, String name, String country, String continent, String latitude, String longitude,String timezone){
        this.id = id;
        this.name = name;
        this.country = country;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

    public String getContinent(){
        return continent;
    }

    public String getLongitude(){
        return longitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getTimezone(){
        return timezone;
    }

}
