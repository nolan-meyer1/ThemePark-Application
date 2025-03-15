# Final Project Team-D

----------------------------------------------------------------------------

## $${\color{lightgreen}Parsers}$$

### &#9679; $${\color{lightblue}ApiInputStream}$$ 
This class is used so that we can have an input stream and open it more than once for parsing if we want to.  

#### &bull; *openInputStream()*
When you create in instance of this object you will need to give it an input stream, and it will store the input stream as a byte array. This method will then return that byte array as an input stream. 


### &#9679; $${\color{lightblue}Park}$$ 
This is what will contain all of the data we have about the parks. Each park object will have the id, the name, country, continent, latitude, longitude, and timezone.  

### &#9679; $${\color{lightblue}ParkParser}$$ 
This is what will parse the data that is given to use from the api.  

#### &bull; *getQuery()*
This is method will return a String that contains our JSON path query.

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. It will then loop through that array and convert each item in that list to a Park object. It will then return it as a HashMap with the park's name as the key, and the value as the Park object.


### &#9679; $${\color{lightblue}Parser}$$ 
This is an abstract class that will be used for all of our parsers. It also uses generics so we can return whatever type of data that we would like.  

#### &bull; *parse()*
This method will call the extract data to get the data, then call the convertRevisionsToList method and return the output. 

#### &bull; *extractData(InputStream inputStreamInstance)*
This takes in an inputstream as a parameter. It will then read the data into a JSONArray and return it. It uses the query given by getQuery() to extract the data.

#### &bull; *getQuery()*
This is method will return a String that contains our JSON path query. It will be overriden by the subclass. 

#### &bull; *conertRevisionsToList(JSONArray list)*
This method takes in a JSONArray. This is overriden by the subclass and uses the generic when the instance is created to specify it's return type. 


### &#9679; $${\color{lightblue}Ride}$$ 
This will hold data for rides within a park. Each ride object will contain an id, a name, whether it is open, the wait time, and when it was last updated.  

### &#9679; $${\color{lightblue}RideParser}$$ 
This is the class that will parse data from the Ride class for later use.  

#### &bull; *getQuery*
This returns a string that contains our JSON query like in the Parser class. It will is overriden as well.

#### &bull; *convertData*
This will convert the data from a Linked Hashmap and populate the items into an ArrayList.


### &#9679; $${\color{lightblue}Weather}$$ 
This is what contains all the data we have about the weather. Each weather object will have the id, name, temperature, feels_like, windSpeed, humidity, and iconID.  

### &#9679; $${\color{lightblue}WeatherParser}$$ 
This is what parses the object gotten from the Weather API.  

#### &bull; *getQuery*
This method returns a JSONPath query string, that is used to select all elements within the JSON structure.

#### &bull; *convertData(JSONArray list)*
This method is converts raw JSON weather data into a structured object.

----------------------------------------------------------------------------

## $${\color{lightgreen}Internet Connections}$$

### &#9679; $${\color{lightblue}InternetConnection}$$
This abstract class also uses generics in order to handle what the searchItem is. This will grab us our input stream that we will then parse.  

#### &bull; *search(T searchItem)*
This method will be given a search item. It will then call the createURLRequest and give it the search item to get the API endpoint we want to go to. It will then give that URL to getInputStream.

#### &bull; *getInputStream(String url)*
This method takes in a URL. It will then go to that endpoint and grab an input stream and return it.

#### &bull; *createRequestURL(T searchItem)*
This method will take in a search item that we will then use to build a URL and return it. This will be overriden by the subclass. 


### &#9679; $${\color{lightblue}ParkConnection}$$
This class handles the fetching of all the parks from the QueueTimes API.  

#### &bull; *createURLRequest(T searchItem)*
This method will still take in a search item, but here we just want to grab the URL. We won't be using the search item for this method, but we will still override it to use the URL we want. 


### &#9679; $${\color{lightblue}RideConnection}$$
This class extends InternetConnection, and allows us to handle the searchItem as well, but for rides. We override the createRequestURL and use getSearchItem in this class to adjust to different data.  


### &#9679; $${\color{lightblue}WeatherConnection}$$
This class handles the fetching of all the weather information from the OpenWeatherMap(https://openweathermap.org/) API.  

#### &bull; *createRequestUrl(String[] latitudeAndLongitude)*
This method generates the API request URL for retrieving weather data based on geographic coordinates.  

----------------------------------------------------------------------------

## $${\color{lightgreen}GUI}$$

### &#9679; $${\color{lightblue}Controller}$$
This class is what will handle all the logic for our GUI.  

#### &bull; *fetchParks()*
This method will fetch the parks using the ParkConnection and ParkParser class. 

#### &bull; *getWaitTimeColor(int waitTime)*
This takes in the an integer that will be our wait time. It will then return the css style color that we want. 

#### &bull; *getRides(int id)*
This takes in an id was the parameter. It will use the RideConnection and RideParser class to get a list of rides. 

#### &bull; *getWeather(String latitude, String longitude)*
This method takes a string of longitude and latitude. It then uses the WeatherConnection and WeatherParser class to return a Weather object containing all the weather data.

### &#9679; $${\color{lightblue}GUI}$$  

----------------------------------------------------------------------------

## $${\color{lightgreen}Error Handling}$$

### &#9679; $${\color{lightblue}networkErrorException}$$
This is a custom exception that we wrote. This exception is thrown when a network error occurs. This is handled in the GUI.  


### &#9679; $${\color{lightblue}noItemFoundException}$$
This is a custom exception happens when the item cannot be found. It is handled inside of the GUI.  


### &#9679; $${\color{lightblue}openInputStreamException}$$
This is a custom exception that happens when we cannot open the input stream. This is also handled inside of the GUI.  


