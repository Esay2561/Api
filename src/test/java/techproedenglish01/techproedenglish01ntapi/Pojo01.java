package techproedenglish01.techproedenglish01ntapi;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import techproedenglish01.techproedenglish01api.Booking;
import techproedenglish01.techproedenglish01api.Bookingdates;
import techproedenglish01.techproedenglish01api.TestBase;


public class Pojo01 extends TestBase {
	
	//By using Pojo class we will send Post Request to https://restful-booker.herokuapp.com/booking
	
	@Test
	public void post01() {
		//Created Request Body by using Pojo Classes
		Bookingdates bookingdates = new Bookingdates("2020-09-15", "2020-09-17");
		Booking booking = new Booking("Suleyman", "Alp", 777, true, bookingdates, "Wifi");
		
		Response response = given().
							   contentType(ContentType.JSON).
				               spec(spec02).
				               body(booking).
				            when().
				                post();
		response.prettyPrint();
		
		//Status Code Assertion
		response.then().assertThat().statusCode(200);	
						//Soft Assert the response body	
		//1)You can use JsonPath. Create JsonPath Object by using response.
		//  JsonPath json = response.jsonPath();
		//2)You can use De-Serialization to create a Java Object from response body
		
        @SuppressWarnings("unchecked")
		Map<String, Object> map = response.as(HashMap.class);
        System.out.println(map);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getFirstname()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getLastname()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getTotalprice().toString()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getDepositpaid().toString()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getBookingdates().getCheckin()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getBookingdates().getCheckout()));
		softAssert.assertTrue(map.get("booking").toString().contains(booking.getAdditionalneeds()));
		softAssert.assertAll();

	}

}
