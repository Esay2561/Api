package apipracticent;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice01 extends TestBase {
	/*
	 When 
	    I send a GET request to REST API URL 
	    https://restful-booker.herokuapp.com/booking/1001   
    Then 
        HTTP Status Code should be 404
        And response body contains "Not Found"
        And response body does not contain "JavaApi" 
        And header "Server" should be "Cowboy"
        And header "Content-Type" should be "text/plain; charset=utf-8"
        And header "Via" should be "1.1 vegur"

        Note: For Base URL use spec02
        Note: Use Map for expected values
        Note: Use Hard Assertion and Soft Assertion
	*/
	
	@Test
	public void getPractice() {
		
		Map<String, Object> expectedMap = new HashMap<>();
		expectedMap.put("Server", "Cowboy");
		expectedMap.put("Content-Type", "text/plain; charset=utf-8");
		expectedMap.put("Via", "1.1 vegur");
		
		spec02.pathParam("id", 1001);
		
		Response response = given().
				               spec(spec02).
				            when().
				               get("/{id}");
		
		//Print "Response Body" on the console
		response.prettyPrint();
		
		//Print "Headers" on the console
		System.out.println(response.getHeaders());

		//1.way for hard assertion of headers
		response.
		    then().
		    assertThat().
		    statusCode(404).
		    headers("Server", expectedMap.get("Server"),
		    		"Content-Type",expectedMap.get("Content-Type"),
		    		"Via",expectedMap.get("Via"));
		
		//2.way for hard assertion of headers
		assertTrue(response.asString().contains("Not Found"));
		assertFalse(response.asString().contains("JavaApi"));
		assertEquals(response.getHeader("Server"),expectedMap.get("Server"));
		assertEquals(response.getHeader("Content-Type"),expectedMap.get("Content-Type"));
		assertEquals(response.getHeader("Via"),expectedMap.get("Via"));
	}

}
