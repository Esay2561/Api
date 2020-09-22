package techproedenglish01.techproedenglish01ntapi;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class PostRequest01 extends TestBase {

	/*
	 To create Post Request in API, you need
	 1)Endpoint ==> Mandatory ==> spec02
	 2)Request Body ==> Mandatory
	 3)Authorization ==> Optional
	 4)Headers ==> Optional
	 */
	
	@Test
	public void post01() {
	
		//1.Way: To create a Request Body ==> Create a String variable and assign the Request Body into the variable
//		String reqBody = "{\n" + 
//						 "    \"bookingid\": 24,\n" + 
//				         "    \"booking\": {\n" + 
//				         "    \"firstname\": \"Suleyman\",\n" + 
//				         "    \"lastname\": \"Alptekin\",\n" + 
//				         "    \"totalprice\": 111,\n" + 
//				         "    \"depositpaid\": true,\n" + 
//				         "    \"bookingdates\": {\n" + 
//				         "            \"checkin\": \"2020-09-14\",\n" + 
//				         "            \"checkout\": \"2020-10-04\"\n" + 
//				         "    },\n" + 
//				         "    \"additionalneeds\": \"Wifi\"\n" + 
//				         "    }\n" + 
//				         "}";
		//3.Way: To create Request Body ==> Use HashMap ==> The Most Common One is using map
		Map<String, Object> reqBody = new HashMap<>();
		reqBody.put("firstname", "Suleyman");
		reqBody.put("lastname", "Alptekin");
		reqBody.put("totalprice", 111);
		reqBody.put("depositpaid", true);
		
		Map<String, Object> bookingDatesReqBody = new HashMap<>();
		bookingDatesReqBody.put("checkin", "2020-09-14");
		bookingDatesReqBody.put("checkout", "2020-10-04");
		
		reqBody.put("bookingdates", bookingDatesReqBody);
		
		reqBody.put("additionalneeds", "Wifi");
		
		
		Response response = given().
				               contentType(ContentType.JSON).
				               spec(spec02).
				               auth().
				               basic("admin", "password123").
				               body(reqBody).
				            when().
				               post();
		response.prettyPrint();
		
		response.
	      then().
	      assertThat().
	      statusCode(200);

	}
	
}
