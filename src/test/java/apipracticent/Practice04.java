package apipracticent;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import techproedenglish01.techproedenglish01api.PojoPractice;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice04 extends TestBase {

	/*
		When 
			I send a POST request to REST API URL 
			http://dummy.restapiexample.com/api/v1/create
			{
	            "employee_name": "Ali Can",
	            "employee_salary": "77000",
	            "employee_age": "35",
	            "profile_image": ""
            }  
	    Then 
		    HTTP Status Code should be 200
		    And Response format should be "application/json"
		    And "employee_name" should be "Ali Can"
		    And "employee_salary" should be 77000
		    And "employee_age" should be 35
		    And "profile_image" should be ""
		    And "status" should be "success"
		    And "message" should be "Successfully! Record has been added." 
		    
		    Note: For Base URL use spec04 and add path param "/create"
		    Note: For actual data use De-Serialization
	        Note: For expected data use Pojo Class
	        Note: Use Hard Assertion(body()) and Soft Assertion
     */
	
	@Test
	public void postPractice() {
		spec04.pathParam("create", "create");
		
		PojoPractice expectedData = new PojoPractice("Ali Can", "77000", "35", null);
		expectedData.setStatus("success");
		expectedData.setMessage("Successfully! Record has been added.");
		
		Response response = given().
				               contentType(ContentType.JSON).
				               spec(spec04).
				               body(expectedData).
				               when().
				               post("/{create}");
		response.prettyPrint();
		
		//Hard Assertion by using body()
		response.
		    then().
		    assertThat().
		    statusCode(200).
		    body("data.employeeName", equalTo(expectedData.getEmployeeName()),
		         "data.employeeSalary", equalTo(expectedData.getEmployeeSalary()),
		         "data.employeeAge", equalTo(expectedData.getEmployeeAge()),
		         "data.profileImage", equalTo(expectedData.getProfileImage()),
		         "status", equalTo(expectedData.getStatus()),
		         "message", equalTo(expectedData.getMessage()));
		
		//Soft Assertion by using 
		Map<String,Object> actualData = response.as(HashMap.class);
		System.out.println(actualData);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(actualData.get("data").toString().contains(expectedData.getEmployeeName()));
		softAssert.assertTrue(actualData.get("data").toString().contains(expectedData.getEmployeeSalary()));
		softAssert.assertTrue(actualData.get("data").toString().contains(expectedData.getEmployeeAge()));
		//softAssert.assertTrue(actualData.get("data").toString().contains(expectedData.getProfileImage().toString()));
		softAssert.assertEquals(actualData.get("status").toString(),expectedData.getStatus());
		softAssert.assertEquals(actualData.get("message").toString(),expectedData.getMessage());
		
		softAssert.assertAll();
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
