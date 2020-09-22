package techproedenglish01.techproedenglish01ntapi;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Delete02 extends TestBase {
	
	@Test
	public void delete01() {
		
		/*							WARM UP (10 minutes)
		 When 
		   I send DELETE Request to http://dummy.restapiexample.com/api/v1/delete/719
		 Then 
		   Status code should be 200
		   The value of "status" key in response body should be "success"  
		   The value of "message" key in response body should be "Successfully! Record has been deleted"
		   
		   Note 1: Use hard assertion
		   Note 2: After given() please use contentType(ContentType.JSON)
		*/
		
		spec04.pathParams("delParam", "delete",
				          "idParam", 719);
		
		Response response = given().
				              contentType(ContentType.JSON).
				              spec(spec04).
				            when().delete("/{delParam}/{idParam}");
		
		response.prettyPrint();
		
		@SuppressWarnings("unchecked")
		Map<String, String> responseMap = response.as(HashMap.class);
		System.out.println(responseMap);
		
		@SuppressWarnings("unchecked")
		Map<String, String> expectedMap = response.as(HashMap.class);
		expectedMap.put("status", "success");
		expectedMap.put("message", "Successfully! Record has been deleted");
		System.out.println(expectedMap);
		
		response.
		     then().
		     assertThat().
		     statusCode(200).
		     body("status", Matchers.equalTo(expectedMap.get("status")),
		    	  "message",Matchers.equalTo(expectedMap.get("message")));

	}

}
