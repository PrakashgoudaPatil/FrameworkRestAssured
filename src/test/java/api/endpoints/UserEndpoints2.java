package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

	
	public static ResourceBundle geturl() {
		
		ResourceBundle rout=ResourceBundle.getBundle("routs");
		return rout;
	} 
	
	public static Response creatUser(User payload) {
		
		String post_url=geturl().getString("post_url");
		
		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return response;
	}

	public static Response readUser(String username) {

		String get_url=geturl().getString("get_url");
		
		Response response =	given()
				.pathParam("username", username)
				.when()
				.get(get_url);
		return response;
	}
	
	public static Response updateUser(User payload, String username) {

		String put_url=geturl().getString("put_url");
		
		Response response =	given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", username)
				.when()
				.put(put_url);
		return response;
	}
	
	public static Response deleteUser(String username) {

		String delete_url=geturl().getString("delete_url");
		
		Response response =	given()
				.pathParam("username", username)
				.when()
				.delete(delete_url);
		return response;
	}
}
