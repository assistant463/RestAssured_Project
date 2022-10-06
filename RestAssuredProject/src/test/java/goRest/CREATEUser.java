package goRest;



import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CREATEUser {
	
	public static HashMap<String, String> map = new HashMap<String, String>();
	
	@BeforeMethod
	public void postData() {
		map.put("name", "spiderman");
		map.put("email", "spiderman554466@gamil.com");
		map.put("gender", "male");
		map.put("status", "inactive");
		RestAssured.baseURI =  "https://gorest.co.in/";
		RestAssured.basePath = "/public/v2/users";
	}
		
		@Test
		public void createData() {
			RestAssured
			.given()
				.contentType("application/json")
				.body(map)
				.header("Authorization", "Bearer 01afcc4766fb03e593df30a3df79acf345eca36afc80d4fa103d7e62037a25a4")
			.when()
				.post()
			.then()
				.statusCode(201)
				.log().all();
			
		}
		
		
		
}


