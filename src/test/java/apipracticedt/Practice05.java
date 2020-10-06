package apipracticedt;

public class Practice05 {
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
}
