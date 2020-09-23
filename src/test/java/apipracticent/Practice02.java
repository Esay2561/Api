package apipracticent;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import techproedenglish01.techproedenglish01api.TestBase;

public class Practice02 extends TestBase {
				  /*
					When 
						I send a GET request to REST API URL 
						https://restful-booker.herokuapp.com/booking/1   
				    Then 
					    HTTP Status Code should be 200
					    And Response format should be "application/JSON"
					    And first name should be "Sally"
					    And lastname should be "Smith"
					    And totalprice should be 705
					    And checkin date should be "2015-02-16"
					    And checkout date should be "2017-06-20"
					    
					    Note: For Base URL use spec02
					    Note: For actual data use JsonPath
				        Note: For expected data use Map
				        Note: Use Hard Assertion and Soft Assertion
				   */
	
	@Test
	public void getPractice() {
		//1.Set the URL
		spec02.pathParam("id", 1);
		
		//2.Set the expected data
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("firstname", "Jim");
		expectedData.put("lastname", "Brown");
		expectedData.put("totalprice", 753);
		expectedData.put("checkin", "2016-03-20");
		expectedData.put("checkout", "2018-12-03");
		
		//3.Send Request
		Response response = given().spec(spec02).when().get("/{id}");
		response.prettyPrint();
		
		//4.Assert status code and response body details(Hard Assertion)
		response.
		     then().
		     assertThat().
		     statusCode(200).
		     contentType(ContentType.JSON).
		     body("firstname", Matchers.equalTo(expectedData.get("firstname")),
		    	  "lastname", Matchers.equalTo(expectedData.get("lastname")),
		    	  "totalprice", Matchers.equalTo(expectedData.get("totalprice")),
		    	  "bookingdates.checkin",Matchers.equalTo(expectedData.get("checkin")),
		    	  "bookingdates.checkout", Matchers.equalTo(expectedData.get("checkout")));
		
		//5.Assert response body details(Hard Assertion)
		JsonPath actualData = response.jsonPath();

		assertEquals(expectedData.get("firstname"),actualData.getString("firstname"));
		assertEquals(expectedData.get("lastname"),actualData.getString("lastname"));
		assertEquals(expectedData.get("totalprice"),actualData.getInt("totalprice"));
		assertEquals(expectedData.get("checkin"),actualData.getString("bookingdates.checkin"));
		assertEquals(expectedData.get("checkout"),actualData.getString("bookingdates.checkout"));
		
		//6.Assert response body details(Soft Assertion)
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualData.getString("firstname"), expectedData.get("firstname"));
		softAssert.assertEquals(actualData.getString("lastname"), expectedData.get("lastname"));
		softAssert.assertEquals(actualData.getInt("totalprice"), expectedData.get("totalprice"));
		softAssert.assertEquals(actualData.getString("bookingdates.checkin"), expectedData.get("checkin"));
		softAssert.assertEquals(actualData.getString("bookingdates.checkout"), expectedData.get("checkout"));
		softAssert.assertAll();
	}
}
