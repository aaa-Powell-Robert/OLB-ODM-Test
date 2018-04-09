package main.java.steps;

import org.hamcrest.Matchers;
import org.testng.Reporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import main.java.ODMJsonBuilder;

public class ODMRestAPITestSteps {

	private RestAssured restAssured;
	private Response response;
	private String json;
	
	@Given("^User email \"(.*?)\" and zipcode \"(.*?)\"$")
	public void user_email_and_zipcode(String email, String zipcode) {
		json = ODMJsonBuilder.inAndOutRequestJson(email,  zipcode);
	}
	
	@When("I send JSON as POST to endpoint")
	public void send_JSON_as_POST_to_endpoint() {
	    response = RestAssured.given()
	    .baseUri("http://localhost")
	    .port(9081)
	    .contentType("application/json")
	    .body(json)
	    .when()
	    .post("/DecisionService/rest/v1/validate/validate");
	    Reporter.log("<pre>"+response.getBody().asString()+"</pre>", true);
	}
	
	@Then("^I should get response code \"(\\d*)\"$")
	public void should_get_response_code(int i) {
	    response.then().statusCode(i);
	}
	
	@And("response should have email \"(.*?)\"$")
	public void response_should_have_email(String expectedEmail) {
		response.then().assertThat().body(
				"inAndOut.emailAddress", Matchers.equalTo(expectedEmail)
		);
	}

	@And("response should have zipcode \"(.*?)\"$")
	public void response_should_have_zipcode(String expectedZipcode) {
		response.then().assertThat().body(
				"inAndOut.zipCode", Matchers.equalTo(expectedZipcode)
		);
	}

	@And("response should have response \"(.*?)\"$")
	public void response_should_have_response(String expectedResponse) {
		response.then().assertThat().body(
				"inAndOut.response", Matchers.equalTo(expectedResponse)
		);
	}
}
