# FinalProject-TeamD

----------------------------------------------------------------------------

## Parsers

### ApiInputStream
This class is used so that we can have an input stream and open it more than once for parsing if we want to. 

#### openInputStrem()
When you create in instance of this object you will need to give it an input stream, and it will store the input stream as a byte array. This method will then return that byte array as an input stream. 

### Park
This is what will contain all of the data we have about the parks. Each park object will have the id, the name, country, continenet, latitude, longitude, and timezone. 

### ParkParser
This is what will parse the data that is given to use from the api. 

#### getQuery()
This is method will return a String that contains our JSON path query.

#### conertRevisionsToList(JSONArray list)
This method takes in a JSONArray. It will then loop through that array and convert each item in that list to a Park object. It will then return it as a HashMap with the park's name as the key, and the value as the Park object.

### Parser
This is an abstract class that will be used for all of our parsers. It also uses generics so we can return whatever type of data that we would like.

#### parse()
This method will call the extract data to get the data, then call the convertRevisionsToList method and return the output. 

#### extractData(InputStream inputStreamInstance)
This takes in an inputstream as a parameter. It will then read the data into a JSONArray and return it. It uses the query given by getQuery() to extract the data.

#### getQuery()
This is method will return a String that contains our JSON path query. It will be overriden by the subclass. 

#### conertRevisionsToList(JSONArray list)
This method takes in a JSONArray. This is overriden by the subclass and uses the generic when the instance is created to specify it's return type. 


### Ride

### RideParser

----------------------------------------------------------------------------

## Internet Connections

### internetConnection
This abstract class also uses generics in order to handle what the searchItem is. This will grab us our input stream that we will then parse. 

#### search(T searchItem)
This method will be given a search item. It will then call the createURLRequest and give it the search item to get the API endpoint we want to go to. It will then give that URL to getInputStream.

#### getInputStream(String url)
This method takes in a URL. It will then go to that endpoint and grab an input stream and return it.

#### createURLRequest(T searchItem)
This method will take in a search item that we will then use to build a URL and return it. This will be overriden by the subclass. 

### parkConnection
This class handles the fetching of all the parks from the QueueTimes API. 

#### createURLRequest(T searchItem)
This method will still take in a search item, but because of the URL we just want to grab the URL. We won't be using the search item for this method, but will will still override it to use the URL we want. 

### rideConnection

----------------------------------------------------------------------------

## GUI

### Controller
This class is what will handle all the logic for our GUI. 

#### fetchParks()
This method will fetch the parks using the ParkConnection and ParkParser class. 

#### getWaitTimeColor(int waitTime)
This takes in the an integer that will be our wait time. It will then return the css style color that we want. 

#### getRides(int id)
This takes in an id was the parameter. It will use the RideConnection and RideParser class to get a list of rides. 

#### getWeather(String latitude, String longitude)
This method takes a string of longitude and latitude. It then uses the WeatherConnection and WeatherParser class to return a Weather object containing all the weather data.


### GUI

----------------------------------------------------------------------------

## Error Handling

### networkErrorException

### noItemFoundException

### openInputStreamException
