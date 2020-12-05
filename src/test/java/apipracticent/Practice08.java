package apipracticent;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice08 extends TestBase {

	 /*
	 1)Use "http://api.openweathermap.org" as Base URI
	 2)Set the URL to "http://api.openweathermap.org/data/3.0/stations?appid=2cb6803f295233aa579843d9e45599f2"
	   by using pathParams() and queryParams() methods
	 3)Use JSONObject Class or MAP to store expected values
     Request Body should have:	"external_id": "SF_TEST001",
							    "name": "San Francisco Test Station",
							    "latitude": 37.76,
							    "longitude": -122.43,
							    "altitude": 150
	 4)Create POST Request to "http://api.openweathermap.org/data/3.0/stations?appid=2cb6803f295233aa579843d9e45599f2"
	   Print the response body on the console
	 5)Response Body should have: {
									    "ID": "5f7cda67cca8ce0001ef62f0", (Changes constantly)
									    "updated_at": "2020-10-06T20:58:15.687292423Z",(Changes constantly)
									    "created_at": "2020-10-06T20:58:15.687292369Z",(Changes constantly)
									    "user_id": "5f6df55e3da20c000743c7ad",(Depends on the user)
									    "external_id": "SF_TEST001",
									    "name": "San Francisco Test Station",
									    "latitude": 37.76,
									    "longitude": -122.43,
									    "altitude": 150,
									    "rank": 10,
									    "source_type": 5
									}  
	 5)Assert Status Code and Response Body details by using body() method
	 6)Assert Response Body details by using assertEquals() method and GSON   
	 7)Assert Response Body details by using Soft Assert and ObjectMapper                   
	*/
	@Test
	public void postPractice() {
		spec05.pathParams("data","data",
				          "id", 3.0,
				          "stations", "stations").
		      queryParam("appid", "2cb6803f295233aa579843d9e45599f2");
		
		//Map<String, Object> postReqBody = new HashMap<>(); 
		JSONObject postReqBody = new JSONObject();
		postReqBody.put("external_id", "SF_TEST001");
		postReqBody.put("name", "San Francisco Test Station");
		postReqBody.put("latitude", 37.76f);
		postReqBody.put("longitude", -122.43f);
		postReqBody.put("altitude", 150);
		
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("user_id", "5f6df55e3da20c000743c7ad");
		expectedData.put("rank", 10);
		expectedData.put("source_type", 5);
		
		
		Response response = given().
				              contentType(ContentType.JSON).
				              spec(spec05).
				              body(postReqBody.toString()).
				           when().
				              post("/{data}/{id}/{stations}");
		response.prettyPrint();
		
		//Hard Assertion body()
		response.
		     then().
		     assertThat().
		     statusCode(201).
		     contentType(ContentType.JSON).
		     body("user_id", equalTo(expectedData.get("user_id")),
		    	  "external_id", equalTo(postReqBody.get("external_id")),
		    	  "name", equalTo(postReqBody.get("name")),
		    	  "latitude", equalTo(postReqBody.get("latitude")),
		    	  "longitude", equalTo(postReqBody.get("longitude")),
		    	  "altitude", equalTo(postReqBody.get("altitude")),
		    	  "rank", equalTo(expectedData.get("rank")),
		    	  "source_type", equalTo(expectedData.get("source_type")));
		
		//Hard Assertion with assertEquals()
		//GSON for De-serialization
		Map<String, Object> actualData = response.as(HashMap.class);

		assertEquals(expectedData.get("user_id"),actualData.get("user_id"));
		assertEquals(postReqBody.get("external_id"),actualData.get("external_id"));
		assertEquals(postReqBody.get("name"),actualData.get("name"));
		assertEquals(postReqBody.get("latitude").toString(),actualData.get("latitude").toString());
		assertEquals(postReqBody.get("longitude").toString(),actualData.get("longitude").toString());
		assertEquals(postReqBody.get("altitude"),actualData.get("altitude"));
		assertEquals(expectedData.get("rank"),actualData.get("rank"));
		assertEquals(expectedData.get("source_type"),actualData.get("source_type"));
		
		//Soft Assertion by using Object Mapper
		Map<String, Object> actualDataObjMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualDataObjMap.get("external_id"), postReqBody.get("external_id"));
		softAssert.assertEquals(actualDataObjMap.get("latitude").toString(), postReqBody.get("latitude").toString());
		
		softAssert.assertAll();
	
	}

}
