package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response creatUser(User payload) {

		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Roots.post_url);
		return response;
	}

	public static Response readUser(String username) {

		Response response =	given()
				.pathParam("username", username)
				.when()
				.get(Roots.get_url);
		return response;
	}
	
	public static Response updateUser(User payload, String username) {

		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", username)
				.when()
				.put(Roots.put_url);
		return response;
	}
	
	public static Response deleteUser(String username) {

		Response response =	given()
				.pathParam("username", username)
				.when()
				.delete(Roots.delete_url);
		return response;
	}
}
