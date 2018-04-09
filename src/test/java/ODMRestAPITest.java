package test.java;

import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import main.java.ODMJsonBuilder;
import main.java.OLBProperties;

public class ODMRestAPITest {
		
	@Test 
	public void
	testODMExample001() {
		RestAssured.baseURI = OLBProperties.getODMEndpoint();
	    Response response = RestAssured.given()
	    .port(9081)
	    .contentType("application/json")
	    .body(ODMJsonBuilder.jsonRequest001())
	    .when()
	    .post("/DecisionService/rest/v1/validate/validate");
	    
	    response.then().statusCode(200);
	    Reporter.log("<pre>"+response.getBody().asString()+"</pre>", true);
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ODMExample001Response-jsonschema.json"));
	}

	@DataProvider(name="inAndOutExamples")
	public static String[][] inAndOutExamples() {
		return new String[][] {
		/*       email                          zipcode  response */
				{"Powell.Robert@aaa-calif.com", "91506", "hello"},
				{"test@test.com",               "90210", "hello"}
		};
	}

	@Test(dataProvider="inAndOutExamples")
	public void
	testODMinAndOutExamples(String emailAddress, String zipCode, String expectedResponse) {
		RestAssured.baseURI = OLBProperties.getODMEndpoint();
	    Response response = RestAssured.given()
	    .port(9081)
	    .contentType("application/json")
	    .body(ODMJsonBuilder.inAndOutRequestJson(emailAddress, zipCode))
	    .when()
	    .post("/DecisionService/rest/v1/validate/validate");
	    
	    response.then().statusCode(200);
		response.then().assertThat().body(
				"inAndOut.emailAddress", Matchers.equalTo(emailAddress),
				"inAndOut.zipCode", Matchers.equalTo(zipCode),
				"inAndOut.response", Matchers.equalTo(expectedResponse)
		);
	    Reporter.log("<pre>"+response.getBody().asString()+"</pre>", true);
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ODMExample001Response-jsonschema.json"));
	}
}
