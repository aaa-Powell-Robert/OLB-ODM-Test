package test.java;

import static org.testng.Assert.assertTrue;

import javax.json.Json;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import main.java.ODMJsonBuilder;

public class ODMJsonBuilderTests {
	
	@Test
	public void testStaticJsonRequestExample001() throws JSONException {
		String expectedJson = "{\"inAndOut\": {\"emailAddress\": \"test@test.com\", \"zipCode\": \"90210\"}}";
		String actualJson = ODMJsonBuilder.jsonRequest001();
		
		JSONAssert.assertEquals(expectedJson, actualJson, true);
		
		JsonSchemaValidator jsonSchemaValidator = JsonSchemaValidator.matchesJsonSchemaInClasspath("ODMExample001Response-jsonschema.json");
	    assertTrue(jsonSchemaValidator.matches(actualJson));
	}
	
	@Test
	public void testInAndOutRequestJson() throws JSONException {
		String expectedJson = "{\"inAndOut\": {\"emailAddress\": \"test@test.com\", \"zipCode\": \"90210\"}}";
		String actualJson = ODMJsonBuilder.inAndOutRequestJson("test@test.com", "90210");
		
		JSONAssert.assertEquals(expectedJson, actualJson, true);
		
		JsonSchemaValidator jsonSchemaValidator = JsonSchemaValidator.matchesJsonSchemaInClasspath("ODMExample001Response-jsonschema.json");
	    assertTrue(jsonSchemaValidator.matches(actualJson));
	}
}
