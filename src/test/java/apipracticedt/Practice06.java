package apipracticedt;

public class Practice06 {
	/*
	 1)Create base url in TestBase Class for "http://api.agromonitoring.com"
	 2)Set the URL to "http://api.agromonitoring.com/agro/1.0/polygons?appid=2cb6803f295233aa579843d9e45599f2"
	   by using pathParams() and queryParams() methods
	 3)Request Body is: {
						   "name":"Polygon Sample",
						   "geo_json":{
						      "type":"Feature",
						      "properties":{},
						      "geometry":{
						         "type":"Polygon",
						         "coordinates":[
						            [
						               [-121.1958,37.6683],
						               [-121.1779,37.6687],
						               [-121.1773,37.6792],
						               [-121.1958,37.6792],
						               [-121.1958,37.6683]
						            ]
						         ]
						      }
						   }
						}
	 4)Create POST Request to "http://api.agromonitoring.com/agro/1.0/polygons?appid=2cb6803f295233aa579843d9e45599f2"
	   Print the response body on the console
	 5)Assert Status Code (201) and Response Body details by using body() method  
	 6)Assert Response Body details by using Soft Assert                   
	*/
}
