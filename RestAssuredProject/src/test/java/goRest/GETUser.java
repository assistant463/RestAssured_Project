package goRest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;


public class GETUser {
	@Test
	public void getData() {
		RestAssured
			.given()
				.contentType("application/json")
				.header("Authorization", "Bearer 01afcc4766fb03e593df30a3df79acf345eca36afc80d4fa103d7e62037a25a4")
				.when()
					.get("https://gorest.co.in/public/v2/users/2571")
					
				.then()
					.statusCode(200)
					.log().all()
				.and()
					.body("email", is("spiderman554466@gamil.com"));
				
				}

}
