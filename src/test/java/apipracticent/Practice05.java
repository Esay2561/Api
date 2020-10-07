package apipracticent;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice05 extends TestBase {
	/*
	When 
		I send a PUT request to REST API URL 
		http://dummy.restapiexample.com/api/v1/update/21
		{
            "employee_name": "Veli Han",
            "employee_salary": "88000",
            "employee_age": "33",
            "profile_image": ""
        }  
    Then 
	    HTTP Status Code should be 200
	    And Response format should be "application/json"
	    And "employee_name" should be "Ali Can"
	    And "employee_salary" should be 77000
	    And "employee_age" should be 35
	    And "profile_image" should be ""
	    
	    Note: For Base URL use spec04 and add path param "/update/21"
	    Note: For actual data use JsonPath
        Note: For expected data use JSONObject
        Note: Use Hard Assertion and Soft Assertion
    */
	@Test
	public void putPractice() {
		
		spec04.pathParams("update", "update",
				          "id", 21);
		
		//To create request body we have many options
		//1)Map 2)JSONObject 3)Pojo 4)String(Not Recommended)
		JSONObject putReqBody = new JSONObject();
		putReqBody.put("employee_name", "Veli Han");
		putReqBody.put("employee_salary", "88000");
		putReqBody.put("employee_age", 33);
		putReqBody.put("profile_image", "");
		
		JSONObject expectedResBody = new JSONObject();
		expectedResBody.put("status", "success");
		expectedResBody.put("data.empty", false);
		expectedResBody.put("message", "Successfully! Record has been updated.");
		
		Response response = given().
				                contentType(ContentType.JSON).
				                spec(spec04).
				                body(putReqBody.toString()).
				            when().
				                put("/{update}/{id}");
		response.prettyPrint();
		
		//Assert with body()
		response.
		     then().
		     assertThat().
		     statusCode(200).
		     body("status", Matchers.equalTo(expectedResBody.getString("status")),
		    	  "data.empty",Matchers.equalTo(expectedResBody.getBoolean("data.empty")),
		    	  "message", Matchers.equalTo(expectedResBody.getString("message")));
		
		/*
		 When you send "patch" request, you are sending just a couple of data not all.
		 But the response body contains all data. If the test case asks you to assert all
		 data request body object will not be enough to assert all details.
		 
		 So you need to create a new object and store expected data in it to use in assertion.
		*/
		
		//Soft Assertion
		//1)JsonPath  2)De-Serialization a)GSON b)ObjectMapper
		JsonPath json = response.jsonPath();
		
		SoftAssert softAssert=new SoftAssert();
		
		softAssert.assertEquals(json.getString("status"),expectedResBody.get("status"));
		softAssert.assertEquals(json.getBoolean("data.empty"),expectedResBody.get("data.empty"));
		softAssert.assertEquals(json.getString("message"),expectedResBody.get("message"));
		
		softAssert.assertAll();

	}

}
