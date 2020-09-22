package techproedenglish01.techproedenglish01ntapi;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		//  You can use JsonPath. Create JsonPath Object by using response.
		//  JsonPath json = response.jsonPath();

		
        JsonPath json = response.jsonPath();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.get("booking.firstname"),booking.getFirstname());
		softAssert.assertEquals(json.get("booking.lastname"),booking.getLastname());
		softAssert.assertEquals(json.get("booking.totalprice"),booking.getTotalprice());
		softAssert.assertEquals(json.get("booking.depositpaid"),booking.getDepositpaid());
		softAssert.assertEquals(json.get("booking.bookingdates.checkin"),bookingdates.getCheckin());
		softAssert.assertEquals(json.get("booking.bookingdates.checkout"),bookingdates.getCheckout());
		softAssert.assertEquals(json.get("booking.additionalneeds"),booking.getAdditionalneeds());

		softAssert.assertAll();

	}

}
