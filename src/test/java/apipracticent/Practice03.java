package apipracticent;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
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
				    
				    Note: For Base URL use spec04
				    Note: For actual data use JsonPath
			        Note: For expected data use JSONObject
			        Note: Use Hard Assertion and Soft Assertion
		    */
	
	@Test
	public void getPractice() {
		//1.Set the URL
		spec04.pathParams("empParam", "employee",
				          "id", 3);
		//2.Set the expected data
		JSONObject expectedData = new JSONObject();
		expectedData.put("employee_name", "Ashton Cox");
		expectedData.put("employee_salary", 86000);
		expectedData.put("employee_age", 66);
		
		//3.Send Request
		Response response = given().spec(spec04).when().get("/{empParam}/{id}");
		response.prettyPrint();
		
		//4.Assert status code and response body details(Hard Assertion)
		response.
		   then().
		   assertThat().
		   statusCode(200).
		   contentType(ContentType.JSON).
		   body("data.employee_name", Matchers.equalTo(expectedData.getString("employee_name")),
				"data.employee_salary",Matchers.equalTo(expectedData.getInt("employee_salary")),
				"data.employee_age",Matchers.equalTo(expectedData.getInt("employee_age")));
		
		//5.Assert response body details(Hard Assertion)
		JsonPath actualData = response.jsonPath();
		
		assertEquals(expectedData.getString("employee_name"),actualData.getString("data.employee_name"));
		assertEquals(expectedData.getInt("employee_salary"),actualData.getInt("data.employee_salary"));
		assertEquals(expectedData.getInt("employee_age"),actualData.getInt("data.employee_age"));

		//6.Assert response body details(Soft Assertion)
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualData.getString("data.employee_name"), expectedData.getString("employee_name"));
		softAssert.assertEquals(actualData.getInt("data.employee_salary"), expectedData.getInt("employee_salary"));
		softAssert.assertEquals(actualData.getInt("data.employee_age"), expectedData.getInt("employee_age"));
		softAssert.assertAll();

	}

	
}
