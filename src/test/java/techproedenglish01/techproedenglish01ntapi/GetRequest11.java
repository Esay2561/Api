package techproedenglish01.techproedenglish01ntapi;

import org.junit.Test;
import org.testng.asserts.SoftAssert;
import com.google.gson.Gson;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class GetRequest11 extends TestBase {
	
	//GSON is a converter
	//By using GSON, we can convert Json Data to Java Object ==> De-Serialization
	//               we can convert Java Objects to Json Data ==> Serialization
	
	@Test
	public void get01() {
		
		spec01.pathParam("id", 2);
		
		Response response = given().
				               spec(spec01).
				            when().
				               get("/{id}");
		response.prettyPrint();
		
		@SuppressWarnings("unchecked")
		HashMap<String,Object> map = response.as(HashMap.class);//De-Serialization
		
		System.out.println(map);
		System.out.println(map.keySet());
		System.out.println(map.values());
		
		SoftAssert softAssert = new SoftAssert();
		
		//Assert that "completed" is false by using soft assertion
		softAssert.assertEquals(map.get("completed"), false);
		//Or
		softAssert.assertTrue(map.get("completed").equals(false));
		
		//Assert that "title" is "quis ut nam facilis et officia qui" by using soft assertion
		softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui");
		
		//Assert that "userId" is 1 by using soft assertion
		softAssert.assertEquals(map.get("userId"),1.0);
		
		softAssert.assertAll();
		
		//How to convert Java Object to Json Data ==> Serialization
		Gson gson = new Gson();
		System.out.println(gson.toJson(map));

	}

}
