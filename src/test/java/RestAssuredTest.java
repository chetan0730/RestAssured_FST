
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.UserPojo;
import pojo.postPojo;
import pojo.putPojo;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;

public class RestAssuredTest {
private static String id;
	@Test(description = "get method")
	public void getMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	 given().contentType(ContentType.JSON)
	 .when()
	 .get("/todos/2")
	 .then()
	 .log().all()
	 .assertThat()
	 .body("id", equalTo(2))
	 .statusCode(200);
		
	}


	@Test(description = "get id from response and store it in a variable")
	public void postReqMethodPojo() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		postPojo postpojo = new postPojo("Mr", "body", "10");

		Response res = given().contentType(ContentType.JSON).body(postpojo).log().all()
				// WHEN
				.when().post("/posts")
				// THEN
				.then().log().all().assertThat().statusCode(201)
				.body("title", equalTo("Mr"))
				.extract().response();
		id = res.jsonPath().getString("id");
		System.out.println(id);
	}

	
	 @Test(description = "passing output of post response to put request")
		public void putReqMethodPojo() {
			RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
			putPojo putpojo = new putPojo(id, "Mrs", "BodyPut", "1");
		

			given().contentType(ContentType.JSON).body(putpojo).log().all()
					// WHEN
					.when().put("/posts/1")
					// THEN
					.then().assertThat().statusCode(200).log().all();
		}

}
