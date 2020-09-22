package techproedenglish01.techproedenglish01ntapi;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;
import techproedenglish01.techproedenglish01api.TestPojo;

public class Pojo02 extends TestBase {
	
	/*
		1)Add path parameter which is "/create" to the base url
		2)By using POJO, create a Request Body which has the following data
			{
			    "name": "Test Data",
			    "salary": "8888",
			    "age": "33"
		    }
		3)When 
			I send POST Request to http://dummy.restapiexample.com/api/v1/create
		  Then 
			Status code is 200
			Content Type is "application/json"
			"status" key has value "success"
			"message" key has value "Successfully! Record has been added."  
		4)Make assertions by using hard assertion     
    */
	
	@Test
	public void post01() {
		spec04.pathParam("createParam", "create");
		
		TestPojo testPojo = new TestPojo("Test Data", "8888", "33");
		testPojo.setStatus("success");
		testPojo.setMessage("Successfully! Record has been added.");
		
		Response response = given().spec(spec04).body(testPojo).when().post("/{createParam}");
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = response.as(HashMap.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(map.get("status"), testPojo.getStatus());
		softAssert.assertEquals(map.get("message"), testPojo.getMessage());
		
		softAssert.assertAll();

	}

}
