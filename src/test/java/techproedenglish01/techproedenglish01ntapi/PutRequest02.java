package techproedenglish01.techproedenglish01ntapi;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class PutRequest02 extends TestBase {

			/*
							
					1)Add path parameter which is "/update" and "/2" to the base url
					2)By using JSONObject Class create a Request Body which has the following data
						{
						    "name": "Test Data",
						    "salary": "7777",
						    "age": "44"
					    }
					4)When 
					    I send PUT Request to http://dummy.restapiexample.com/api/v1/update/2
					  Then 
						Status code is 200
						"status" key has value "success"
						"message" key has value "Successfully! Record has been updated." 
					5)Make assertions (Except status code) by using soft assertion	 
			*/
	@Test
	public void put01() {
		spec04.pathParams("updateParam", "update",
				          "id",11);
		JSONObject reqBody = new JSONObject();
		reqBody.put("name", "Test Data");
		reqBody.put("salary", "7777");
		reqBody.put("age", "44");
		
		Response response = given().
				                spec(spec04).
				                body(reqBody).
				            when().
				                put("/{updateParam}/{id}");
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200);
		
		//For softt assertion you need to decide to use JsonPath or De-Serialization
		@SuppressWarnings("unchecked")
		HashMap<String, String> map = response.as(HashMap.class);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(map.get("status"), "success");
		softAssert.assertEquals(map.get("message"), "Successfully! Record has been updated.");
		softAssert.assertAll();
			
	}

}
