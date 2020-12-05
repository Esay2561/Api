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
		
		//1.Set the URL
		String url = "https://restful-booker.herokuapp.com/booking/3";
		//String url = "http://api.openweathermap.org/data/2.5/weather?q=Istanbul&appid=6eaa1af5bb0846c4456dbf0a4849cb73";
		
		//2.Set the expected data
			//We will learn it later
		
		//3.Send Request
		Response response = given().
								//accept("application/json") means, I want response in json format
								accept("application/json").
				            when().
				                get(url);
		
		//prettyPrint() prints the response body on the console
		response.prettyPrint();
		
		//4.Assert status code, headers, statusLine, response body etc...
			//1.Way:
			response.
			    then().
			    assertThat().
			    statusCode(200).
			    //contentType("application/json") can be used for both request and response to be in json format
			    contentType("application/json").
			    statusLine("HTTP/1.1 200 OK");
			
			//2.Way:
				//We will learn it later
			
			//3.Way:
				//We will learn it later
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
		//1.Set the URL
				String url = "https://restful-booker.herokuapp.com/booking/1001";
				//String url = "http://api.openweathermap.org/data/2.5/weather?q=Zamazingo&appid=6eaa1af5bb0846c4456dbf0a4849cb73";
		
		//2.Set the expected data
				//We will learn it later
			
		//3.Send Request
		Response response = given().
				            when().
				               get(url);
		//prettyPrint() prints the response body on the console
		response.prettyPrint();
		
		//4.Assert status code, headers, statusLine, response body etc...
		//1.Way:
		response.
		    then().
		    assertThat().
		    statusCode(404).
		    statusLine("HTTP/1.1 404 Not Found");
		//2.Way:
			//We will learn it later
	
		//3.Way:
			//We will learn it later
		
		//How to print statusLine
		System.out.println(response.statusLine());
		
		//How to print "Headers" on the console
		System.out.println(response.getHeaders());
		System.out.println(response.getHeader("Server"));
		System.out.println(response.getHeader("Content-type"));// ==> 1.way to print content type on the console
		System.out.println(response.getContentType());// ==> 2.way to print content type on the console

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
