package apipracticent;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice07 extends TestBase {
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
	
	@Test
	public void postPractice() {
		spec06.pathParams("agro","agro",
				          "id", 1.0,
				          "polygons", "polygons").
		       queryParam("appid", "2cb6803f295233aa579843d9e45599f2");
		
		double coordinates[][][] = { { {-121.1958,37.6683}, {-121.1779,37.6687}, {-121.1773,37.6792}, {-121.1958,37.6792}, {-121.1958,37.6683} } }; 

		Map<String,Object> geometry = new HashMap<>();
		geometry.put("type", "Polygon");
		geometry.put("coordinates", coordinates);
		
		Map<String, String> properties = new HashMap<>();
		
		Map<String, Object> geo_json = new HashMap<>();
		geo_json.put("type", "Feature");
		geo_json.put("properties", properties);
		geo_json.put("geometry", geometry);
		
		double center[] = {-121.1867,37.67385};
		
		Map<String, Object> ReqBodyMap = new HashMap<>();
		ReqBodyMap.put("name", "Polygon Sample");
		ReqBodyMap.put("geo_json", geo_json);
		ReqBodyMap.put("area", 190.9484f);
		ReqBodyMap.put("center", center);

		
		Response response = given().contentType(ContentType.JSON).spec(spec06).body(ReqBodyMap).when().post("/{agro}/{id}/{polygons}");
		response.prettyPrint();
		
		response.
		    then().
		    assertThat().
		    statusCode(201).
		    body("geo_json.geometry.coordinates[0][0][0]", equalTo((float)coordinates[0][0][0]),
		    	 "geo_json.geometry.type", equalTo(geometry.get("type")),
		    	 "geo_json.type", equalTo(geo_json.get("type")),
		    	 "geo_json.properties",equalTo(properties),
		    	 "name", equalTo(ReqBodyMap.get("name")),
		    	 "area", equalTo(ReqBodyMap.get("area")),
		    	 "center[0]", equalTo((float)center[0]));

	}

}
