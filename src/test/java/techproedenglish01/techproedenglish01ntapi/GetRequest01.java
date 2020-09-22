package techproedenglish01.techproedenglish01ntapi;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
	/*
	 	Positive Scenario
	 When I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3
	 Then 
	 HTTP Status code should be 200
	 And  Content type should be Json
	 And  Status Line should be HTTP/1.1 200 OK
	*/
	
	@Test
	public void get01() {
		
		Response response = given().
				               accept("application/json").//it means API will accept JSON Format Data
				            when().
				               get("https://restful-booker.herokuapp.com/booking/3");
		
		response.prettyPrint();//prettyPrint() prints the response body on the console
		
		response.
		    then().
		    assertThat().
		    statusCode(200).
		    contentType("application/json").//Content type of the response body
		    statusLine("HTTP/1.1 200 OK");
	}

	/*
	 	Negative Scenario
	 When I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/1001
	 Then 
	 HTTP Status code should be 404
	 And  Status Line should be HTTP/1.1 404 Not Found
   */
	
	@Test
	public void get02() {
		
		Response response = given().
				            when().
				               get("https://restful-booker.herokuapp.com/booking/1001");
		
		response.prettyPrint();
		
		response.
		    then().
		    assertThat().
		    statusCode(404).
		    statusLine("HTTP/1.1 404 Not Found");
		
		//How to print "Headers" on the console
		System.out.println(response.getHeaders());
		System.out.println(response.getHeader("Server"));
		System.out.println(response.getHeader("Content-type"));//1.way to print content type on the console
		System.out.println(response.getContentType());//2.way to print content type on the console

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
