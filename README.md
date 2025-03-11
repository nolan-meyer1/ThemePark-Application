# FinalProject-TeamD

## Format Instructions : # = Title, ## = Folder/Group, ### = Class, #### = Function/Method; Type in normal text below each class/method to give a description

----------------------------------------------------------------------------

## Parsers

### ApiInputStream

### Park
This is what will contain all of the data we have about the parks. Each park object will have the id, the name, country, continenet, latitude, longitude, and timezone. 

### ParkParser
This is what will parse the data that is given to use from the api. 

#### getQuery()
This is method will return a String that contains our JSON path query.

#### conertRevisionsToList(JSONArray list)
This method takes in a JSONArray. It will then loop through that array and convert each item in that list to a Park object. Each object is then added to a list that will be returned.

### Parser

### Ride

### RideParser

----------------------------------------------------------------------------

## Internet Connections

### internetConnection

### parkConnection

### rideConnection

----------------------------------------------------------------------------

## GUI

### Controller

### GUI

----------------------------------------------------------------------------

## Error Handling

### networkErrorException

### noItemFoundException

### openInputStreamException
