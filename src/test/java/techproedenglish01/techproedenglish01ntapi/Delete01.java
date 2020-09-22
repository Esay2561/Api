package techproedenglish01.techproedenglish01ntapi;

import static io.restassured.RestAssured.given;
import org.junit.Test;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

public class Delete01 extends TestBase {
	
	/*
	  For Delete Request we need just Endpoint like Get Request
	*/
	
	@Test
	public void delete01() {
		
		//Get the data before deleting
		Response response = given().spec(spec01).when().get("/198");
		response.prettyPrint();
		
		Response responseDel = given().spec(spec01).when().delete("/198");
		responseDel.prettyPrint();
		
		responseDel.then().assertThat().statusCode(200);
	}

}
