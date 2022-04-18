import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.UserPojo;

import static org.hamcrest.Matchers.*;
import io.restassured.matcher.RestAssuredMatchers.*;


public class RestAssured1 {

//	@Test
	public void firstMethod() {
		/*
		 * RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		 * RequestSpecification req = RestAssured.given(); Response response =
		 * req.request(Method.GET,"/todos/1");
		 * System.out.println(response.getBody().asString())
		 */;
		 
		 RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		 Response response  = RestAssured.given().when().get("/todos/1");
		 System.out.println(response.getStatusCode());
		 System.out.println(response.getBody().asPrettyString());
		 System.out.println(response.getBody().asString());
		
	}
	
	@Test
	public void secondMethod() {
		 RestAssured.baseURI = "https://reqres.in/api";
		 given().queryParam("page", "1")
		 .when()
		 .get("/users")
		 .then()
		 .log().all()
		 .body("page", equalTo(1))
		 .body("data.first_name", hasItems("George","Tracey"));
	}
	@Test
	public void postReq() {
		RestAssured.baseURI = "https://reqres.in/api";
		Map<String, String> body =  new HashMap<String, String>();
		body.put("name", "morpheus");
		body.put("job", "leader");
		
		given()
		  .contentType(ContentType.JSON)
		  .body(body)
		  .log()
		  .all()
	// WHEN
	   .when()
		   .post("/CREATE")
	// THEN
	   .then()
		   .assertThat()
		   .statusCode(201)
		   .log()
		   .all();
	}
	
	@Test
	public void postReqPojo() {
		RestAssured.baseURI = "https://reqres.in/api";
		UserPojo userPojo = new UserPojo("chetan", "coder");
		given()
		  .contentType(ContentType.JSON)
		  .body(userPojo)
		  .log()
		  .all()
	// WHEN
	   .when()
		   .post("/CREATE")
	// THEN
	   .then()
		   .assertThat()
		   .statusCode(201)
		   .log()
		   .all();
	}
	
}
