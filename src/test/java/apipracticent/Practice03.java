package apipracticent;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Practice03 extends TestBase {

	/*
		When 
			I send a GET request to REST API URL 
			http://dummy.restapiexample.com/api/v1/employee/3  
	    Then 
		    HTTP Status Code should be 200
		    And Response format should be "application/json"
		    And "employee_name" should be "Ashton Cox"
		    And "employee_salary" should be 86000
		    And "employee_age" should be 66
		    
		    Note: For Base URL use spec02
		    Note: For actual data use JsonPath
	        Note: For expected data use Map
	        Note: Use Hard Assertion and Soft Assertion
    */
	
	@Test
	public void get01() {
		
		spec04.pathParams("empParam", "employee",
				           "id", 3);
		
		Response response = given().spec(spec04).when().get("/{empParam}/{id}");
		response.prettyPrint();
		
		response.
		    then().
		    assertThat().
		    statusCode(200);
		
		JsonPath actualData = response.jsonPath();
		
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("employee_name", "Ashton Cox");
		expectedData.put("employee_salary", 86000);
		expectedData.put("employee_age", 66);
		System.out.println(expectedData);
		
		
	}
}
