package techproedenglish01.techproedenglish01ntapi;


import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import Utilities.JsonUtil;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBase;

import static io.restassured.RestAssured.*;

public class ObjectMapperWithMap extends TestBase {
	
	//I will create a map, I will put some data into it then I will convert it to
	//Json by using ObjectMapper Class (Serialization)
	
	@Test
	public void javaToJsonToJava() {
		
		HashMap<Integer, String> map = new HashMap<>();
		map.put(101, "Ali");
		map.put(102, "Veli");
		map.put(103, "Angie");
		map.put(104, "Marry");
		System.out.println(map);
		
		//By using convertJavaToJson() method I converted map to Json Data
		String jsonFromMap = JsonUtil.convertJavaToJson(map);
		System.out.println(jsonFromMap);
		
		//Now let's convert jsonFromMap to Java Object which is Map
		Map<Integer, String> JavaFromJson = JsonUtil.convertJsonToJava(jsonFromMap, Map.class);
		System.out.println(JavaFromJson);
	
	}
	
	@Test
	public void jsonFromApiToJava() {
		
		spec02.pathParam("id", 3);
		
		Response response = given().
				               spec(spec02).
				            when().
				               get("/{id}");
		response.prettyPrint();
		
		Map<String, Object> apiJsonToMap = JsonUtil.convertJsonToJava(response.asString(), Map.class);
		//apiJsonToMap is containing actual value.
		System.out.println(apiJsonToMap);
		
		//expectedValuesMap is containing expected values
		Map<String, Object> expectedValuesMap = new HashMap<>();
		expectedValuesMap.put("firstname", "Jim");
		expectedValuesMap.put("lastname", "Wilson");
		expectedValuesMap.put("totalprice", 490);
		expectedValuesMap.put("depositpaid", false);
		
		response.
		    then().
		    assertThat().
		    statusCode(200);
		
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiJsonToMap.get("firstname"), expectedValuesMap.get("firstname"));
        softAssert.assertEquals(apiJsonToMap.get("lastname"), expectedValuesMap.get("lastname"));
        softAssert.assertEquals(apiJsonToMap.get("totalprice"), expectedValuesMap.get("totalprice"));
        softAssert.assertEquals(apiJsonToMap.get("depositpaid"), expectedValuesMap.get("depositpaid"));
        softAssert.assertAll();

	}
}

