package techproedenglish01.techproedenglish01ntapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest02 {
	
									/*
									 When I send a GET request to REST API URL 
									 https://restful-booker.herokuapp.com/booking/1001   
								     Then HTTP Status Code should be 404
								     HTTP Status code should be 404
									 And  Status Line should be HTTP/1.1 404 Not Found
								     And response body contains "Not Found"
								     And response body does not contain "TechProEd" 
									*/
	
	@Test
	public void get01() {
		
		//1.Set the URL
			String url = "https://restful-booker.herokuapp.com/booking/1001";
			//String url = "http://api.openweathermap.org/data/2.5/weather?q=Zamazingo&appid=6eaa1af5bb0846c4456dbf0a4849cb73";
			
		//2.Set the expected data
			//We will learn it later
		
		//3.Send Request
		Response response = given().
								accept("application/json").
							when().
								get(url);
		response.prettyPrint();
		
		//4.Assert status code, headers, statusLine, response body etc...
			//1.Way: Hard Assertion
			response.
				then().
				assertThat().
				statusCode(404).
				//contentType will fail because content is just "Not Found" text not json data.
				//For negative scenarios no need to assert content type
				//contentType("application/json").
			    statusLine("HTTP/1.1 404 Not Found");
			
			//2.Way: Hard Assertion
			//When you use asertEquals method make the 1. parameter expected, and 2. parameter actual
			assertEquals(404, response.statusCode());
			//contentType will fail because content is just "Not Found" text not json data.
			//For negative scenarios no need to assert content type
			//assertEquals("application/json", response.getContentType());
			assertEquals("HTTP/1.1 404 Not Found", response.getStatusLine());
		
			//When you use assertTrue(), put a boolean as parameter.If the boolean is true your test will pass
            //otherwise it will fail
			assertTrue(response.asString().contains("Not Found"));
			
			//When you use assertFalse(), put a boolean as parameter.If the boolean is false your test will pass
            //otherwise it will fail
			assertFalse(response.asString().contains("TechProEd"));
			
			//3.Way: Soft Assertion
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.assertEquals(response.statusCode(), 404);
			//contentType will fail because content is just "Not Found" text not json data.
			//For negative scenarios no need to assert content type
			//softAssert.assertEquals(response.getContentType(), "application/json");
			softAssert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
			softAssert.assertTrue(response.asString().contains("Not Found"));
			softAssert.assertFalse(response.asString().contains("TechProEd"));
					
			softAssert.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
