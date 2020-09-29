package apipracticent;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice06 extends TestBase{
	
	/*
	 1)Create base url in TestBase Class for "http://api.openweathermap.org"
	 2)Set the URL to "http://api.openweathermap.org/data/2.5/weather?q=Istanbul&&appid="2cb6803f295233aa579843d9e45599f2"
	   by using pathParams() and queryParams() methods
	 3)Use JSONObject Class or MAP to store expected values
	   Expected Values are: "coord.lon" ==> 28.98f   
	                        "coord.lat" ==> 41.04f
	                        "weather.description" ==> "broken clouds"
	                        "base" ==> "stations"
	                        "main.feels_like" ==> 301.81f
	                        "visibility" ==> 10000
	                        "clouds.all" ==> 57
	                        "sys.country" ==> "TR"
	                        "timezone" ==> 10800
	                        "name" ==> "Istanbul"
	 4)Create GET Request to "http://api.openweathermap.org/data/2.5/weather?q=Istanbul&&appid="2cb6803f295233aa579843d9e45599f2"
	   Print the response body on the console
	 5)Assert Status Code and Response Body details by using body() method
	 6)Assert Response Body details by using assertEquals() method    
	 7)Assert Response Body details by using Soft Assert                   
	*/

	@Test
	public void getPractice(){
		//1)Create base url in TestBase Class for "http://api.openweathermap.org"
		//To create Base URL in TestBase Class we used a)RequestSpecification Class
	    //                                             b)RequestSpecBuilder()
		
		//2)How to add path params and query params to the base url
		spec05.pathParams("data", "data",
				          "id", 2.5,
				          "weather", "weather").
		       queryParams("q", "Istanbul",
		    		       "appid","2cb6803f295233aa579843d9e45599f2");
		
	   //To store expected values we have 3 main options: a)JSONObject Class b)Map or List of Maps c)Pojo Class
	   //3)Use JSONObject Class or MAP to store expected values
	    JSONObject expectedValues = new JSONObject();
		//Map<String,Object> expectedValues = new HashMap<>();
		expectedValues.put("coord.lon", 28.98f);
		expectedValues.put("coord.lat", 41.04f);
		expectedValues.put("weather.description", "clear sky");
		expectedValues.put("base", "stations");
		expectedValues.put("main.feels_like", 297.54f);
		expectedValues.put("visibility", 10000);
		expectedValues.put("clouds.all", 0);
		expectedValues.put("sys.country", "TR");
		expectedValues.put("timezone", 10800);
		expectedValues.put("name", "Istanbul");
		expectedValues.put("statusCode", 200);
		
	  //4)Create GET Request
		Response response = given().spec(spec05).when().get("/{data}/{id}/{weather}");
		response.prettyPrint();
		
	  //5)Make assertions a)body()  
	  //                  b)assertEquals(), assertTrue(), assertFalse() (JsonPath or De-Serialization ==> GSON or ObjectMapper) 
	  //                  c)Soft Assert (JsonPath or De-Serialization ==> GSON or ObjectMapper)	
		
		//a)body()
		response.
		      then().
		      assertThat().
		      statusCode(200).
		      body("coord.lon", equalTo(expectedValues.get("coord.lon")),
		    	   "coord.lat", equalTo(expectedValues.get("coord.lat")),
		    	   //"weather[0].description",equalTo(expectedValues.get("weather.description")),
		    	   "base",equalTo(expectedValues.get("base")),
		    	   //"main.feels_like",equalTo(expectedValues.get("main.feels_like")),
		    	   "visibility",equalTo(expectedValues.get("visibility")),
		    	   //"clouds.all",equalTo(expectedValues.get("clouds.all")),
		    	   "sys.country",equalTo(expectedValues.get("sys.country")),
		    	   "timezone",equalTo(expectedValues.get("timezone")),
		    	   "name", equalTo(expectedValues.get("name")));
		
		//b)assertEquals(), assertTrue(), assertFalse() 
		JsonPath json = response.jsonPath();
		assertEquals(expectedValues.getInt("statusCode"), response.getStatusCode());
		assertEquals(expectedValues.get("coord.lon"),json.get("coord.lon"));
		assertEquals(expectedValues.get("coord.lat"),json.get("coord.lat"));
		assertEquals(expectedValues.get("base"),json.get("base"));
		assertEquals(expectedValues.get("visibility"),json.get("visibility"));
		assertEquals(expectedValues.get("sys.country"),json.get("sys.country"));
		assertEquals(expectedValues.get("timezone"),json.get("timezone"));
		assertEquals(expectedValues.get("name"),json.get("name"));
		
		//c)Soft Assert (JsonPath or De-Serialization ==> GSON or ObjectMapper)
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(response.getStatusCode(),expectedValues.getInt("statusCode"));
		softAssert.assertEquals(json.get("coord.lon"), expectedValues.get("coord.lon"));
		softAssert.assertEquals(json.get("coord.lat"),expectedValues.get("coord.lat"));
		softAssert.assertEquals(json.get("base"), expectedValues.get("base"));
		softAssert.assertEquals(json.get("visibility"), expectedValues.get("visibility"));
		softAssert.assertEquals(json.get("sys.country"), expectedValues.get("sys.country"));
		softAssert.assertEquals(json.get("timezone"), expectedValues.get("timezone"));
		softAssert.assertEquals(json.get("name"), expectedValues.get("name"));

		softAssert.assertAll();

	}

}
