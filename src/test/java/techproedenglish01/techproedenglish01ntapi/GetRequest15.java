package techproedenglish01.techproedenglish01ntapi;

import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class GetRequest15 extends TestBase {
	
	/*							Warm Up 10 Minutes
			 
			 When
				 	I send request to https://restful-booker.herokuapp.com/booking/3
				 Then
				        Status code is 200
				    And Content type is "application/json"
				    And Status line is "HTTP/1.1 200 OK"
				    And First name is Jim
				    And Total price is 623
				    And Deposit paid is true
				    And checkin date is 2020-03-18
				    
			  Use De-Serialization to convert Json response body to a Map. 
			  Then by using the map and soft assertion make assertion.  
	    
	 */
	
	@Test
	public void get01() {
		spec02.pathParam("bookingid", 3);
		
		Response response = given().
				               spec(spec02).
				            when().
				               get("/{bookingid}");
		response.prettyPrint();
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = response.as(HashMap.class);
		System.out.println(map);
		
		response.
		     then().
		     assertThat().
		     statusCode(200).
		     contentType(ContentType.JSON).
		     statusLine("HTTP/1.1 200 OK");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(map.get("firstname"), "Jim");
		softAssert.assertEquals(map.get("totalprice"), 623.0);
		softAssert.assertEquals(map.get("depositpaid"), false);
		softAssert.assertTrue(map.get("bookingdates").toString().contains("checkin=2017-06-29"));
		softAssert.assertAll();
	
	}
}
