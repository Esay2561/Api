package apipracticent;

public class Practice05 {
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
}
