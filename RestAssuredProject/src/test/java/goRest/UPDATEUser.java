package goRest;

import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class UPDATEUser {
public static HashMap<String, String> map = new HashMap<String, String>();
	
	@BeforeMethod
	public void putData() {
		map.put("name", "spidermannn");
		map.put("email", "spiderman554466@gamil.com");
		map.put("gender", "male");
		map.put("status", "active");
		RestAssured.baseURI = "https://gorest.co.in/";
		RestAssured.basePath = "/public/v2/users/2571";
		
	}
	@Test
	public void updateResource() {
		RestAssured
			.given()
				.contentType("application/json")
				.body(map)
				.header("Authorization", "Bearer 01afcc4766fb03e593df30a3df79acf345eca36afc80d4fa103d7e62037a25a4")
			.when()
				.put()
			.then()
				.statusCode(200)
				.log().all()
			.and()
				.body("name", is("spidermannn"));
			
		
	}

}


