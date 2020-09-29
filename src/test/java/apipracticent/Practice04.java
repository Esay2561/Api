package apipracticent;

import org.junit.Test;

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
		    
		    Note: For Base URL use spec04 and add path param "/create"
		    Note: For actual data use De-Serialization
	        Note: For expected data use Pojo Class
	        Note: Use Hard Assertion and Soft Assertion
     */
	
	@Test
	public void postPractice() {
		spec04.pathParam("create", "create");
		
		PojoPractice expectedData = new PojoPractice("Ali Can", "77000", "35", "");
		
		Response response = given().contentType(ContentType.JSON).spec(spec04).body(expectedData).when().post("/{create}");
		response.prettyPrint();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
