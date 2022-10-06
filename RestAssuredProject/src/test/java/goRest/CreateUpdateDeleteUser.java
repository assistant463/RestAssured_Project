package goRest;

import java.util.HashMap;
import java.util.UUID;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import log4jdemo.BaseTest;

import static org.hamcrest.Matchers.*;

public class CreateUpdateDeleteUser extends BaseTest{
	
	HashMap<String, String> map = new HashMap<String, String>();
	UUID uuid = UUID.randomUUID();
	String id;
	
	@Test(priority = 0)
	public void postData() {
		map.put("name", "spiderman");
		logger.info("Added name");
		map.put("email", "spiderman" + uuid + "@gmail.com");
		logger.info("Added email");
		map.put("gender", "male");
		logger.info("Added gender");
		map.put("status", "inactive");
		logger.info("Added status");
		RestAssured.baseURI =  "https://gorest.co.in/";
		RestAssured.basePath = "/public/v2/users";
		logger.info("PayLoad created for creating the resources");
	}
	@Test(priority = 1)
	public void createUser() {
		Response response = RestAssured
								.given()
									.contentType("application/json")
									.body(map)
									.header("Authorization", "Bearer 01afcc4766fb03e593df30a3df79acf345eca36afc80d4fa103d7e62037a25a4")
								.when()
									.post()
								.then()
									.log().all()
									.contentType(ContentType.JSON).extract().response();
		logger.info("Resource created and response captured");
		
		JsonPath jsonPath = response.jsonPath();
		logger.info("JSON path created");
		id = jsonPath.get("id").toString();
		logger.info("Resource id captured");
		
		
		
	}
	@Test(priority = 2)
	public void updateUser() {
		map.put("name", "spidermann");
		map.put("email", "spiderman"+ uuid + "@gmail.com");
		map.put("gender", "male");
		map.put("status", "inactive");
		RestAssured.baseURI =  "https://gorest.co.in/";
		RestAssured.basePath = "/public/v2/users/" + id;
		logger.info("PayLoad created for updating the resource");
		
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
					.body("name", is("spidermann"));
		logger.info("Resource updated");
	}
	@Test(priority = 3)
	public void deleteUser() {
		RestAssured.baseURI =  "https://gorest.co.in/";
		RestAssured.basePath = "/public/v2/users/" + id;
		
		RestAssured
			.given()
				.contentType("application/json")
				.header("Authorization", "Bearer 01afcc4766fb03e593df30a3df79acf345eca36afc80d4fa103d7e62037a25a4")
			.when()
				.delete()
			.then()
				.statusCode(204);
		logger.info("Resource deleted");
					
		
	}
	

}
