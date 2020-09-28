package apipracticent;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice06 extends TestBase {
	
	/*
	 1)Create base url in TestBase Class for "http://api.openweathermap.org"
	 2)Set the URL to "http://api.openweathermap.org/data/2.5/weather?q=Istanbul"
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
	 4)Create GET Request to "http://api.openweathermap.org/data/2.5/weather?q=Istanbul"
	   Print the response body on the console
	 5)Assert Status Code and Response Body details by using body() method
	 6)Assert Response Body details by using assertEquals() method    
	 6)Assert Response Body details by using Soft Assert                   
	 */
	
	@Test
	public void getPractice() {
		//1.Set the URL
		spec05.pathParams("data", "data",
				          "id", 2.5,
				          "weather", "weather").
		       queryParams("q", "Istanbul",
				           "appid","2cb6803f295233aa579843d9e45599f2");

		//2.Set the expected data
		//Map<String, Object> expectedData = new HashMap<>();
		JSONObject expectedData = new JSONObject();
		expectedData.put("coord.lon", 28.98f);
		expectedData.put("coord.lat", 41.04f);
		expectedData.put("weather.description", "broken clouds");
		expectedData.put("base", "stations");
		expectedData.put("main.feels_like", 301.81f);
		expectedData.put("visibility", 10000);
		expectedData.put("clouds.all", 57);
		expectedData.put("sys.country", "TR");
		expectedData.put("timezone", 10800);
		expectedData.put("name", "Istanbul");
		
		
		//3.Send Request
		Response response = given().spec(spec05).when().get("/{data}/{id}/{weather}");
		response.prettyPrint();
		
		//4.Assert status code and response body details(Hard Assertion by body() method)
		response.
		   then().
		   assertThat().
		   statusCode(200).
		   body("name", Matchers.equalTo(expectedData.get("name")),
				"timezone",Matchers.equalTo(expectedData.get("timezone")),
				"sys.country",Matchers.equalTo(expectedData.get("sys.country")),
				"clouds.all",Matchers.equalTo(expectedData.get("clouds.all")),
				"visibility",Matchers.equalTo(expectedData.get("visibility")),
				"main.feels_like",Matchers.equalTo(expectedData.get("main.feels_like")),
				"base",Matchers.equalTo((expectedData.get("base"))),
				"weather[0].description",Matchers.equalTo((expectedData.get("weather.description"))),
				"coord.lat", Matchers.equalTo(expectedData.get("coord.lat")),
				"coord.lon", Matchers.equalTo(expectedData.get("coord.lon")));
		
		//5.Assert response body details(Hard Assertion by assertEquals() method)
		
		//6.Assert response body details(Soft Assertion)
	}
}
