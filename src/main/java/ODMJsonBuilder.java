package main.java;

import javax.json.Json;

public class ODMJsonBuilder {
	
	public static String jsonRequest001() {
		String json = Json.createObjectBuilder()
				.add("inAndOut", Json.createObjectBuilder()
						.add("emailAddress", "test@test.com")
						.add("zipCode", "90210")
						)
				.build().toString();
		return json;
	}
	
	public static String inAndOutRequestJson(String email, String zip) {
		String json = Json.createObjectBuilder()
				.add("inAndOut", Json.createObjectBuilder()
						.add("emailAddress", email)
						.add("zipCode", zip)
						)
				.build().toString();
		return json;
	}

}
