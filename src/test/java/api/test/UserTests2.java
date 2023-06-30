package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payloads.User;

import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayloads;
	public Logger log;
	
	@BeforeClass
	public void setUser() {
		faker=new Faker();
		userPayloads=new User();
		
		userPayloads.setId(faker.idNumber().hashCode());
		userPayloads.setUsername(faker.name().username());
		userPayloads.setPassword(faker.internet().password(5, 10));
		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setPhone(faker.phoneNumber().cellPhone());
		userPayloads.setEmail(faker.internet().safeEmailAddress());
		
		log=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		log.info("+++++++++++ User is Creating ++++++++++");
		Response response= UserEndpoints2.creatUser(userPayloads);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		log.info("+++++++++++ User was Created ++++++++++");
//		String username=response.jsonPath().get("username").toString();
//		String password=response.jsonPath().get("password").toString();
//		int id=response.jsonPath().getInt("id");
//		String firstName=response.jsonPath().get("firstName").toString();
//		String lastName=response.jsonPath().get("lastName").toString();
//		String phone=response.jsonPath().get("phone").toString();
//		// validations
//		Assert.assertEquals(username, userPayloads.getUsername());
//		Assert.assertEquals(password, userPayloads.getPassword());
//		Assert.assertEquals(id, userPayloads.getId());
//		Assert.assertEquals(firstName, userPayloads.getFirstName());
//		Assert.assertEquals(lastName, userPayloads.getLastName());
//		Assert.assertEquals(phone, userPayloads.getPhone());
		
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		log.info("+++++++++++ Read Created user and started get method ++++++++++");
		
		Response response=UserEndpoints2.readUser(userPayloads.getUsername());
		response.then().log().all();
		
		String username=response.jsonPath().get("username").toString();
		String password=response.jsonPath().get("password").toString();
		int id=response.jsonPath().getInt("id");
		String firstName=response.jsonPath().get("firstName").toString();
		String lastName=response.jsonPath().get("lastName").toString();
		String phone=response.jsonPath().get("phone").toString();
		// validations
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(username, userPayloads.getUsername());
		Assert.assertEquals(password, userPayloads.getPassword());
		Assert.assertEquals(id, userPayloads.getId());
		Assert.assertEquals(firstName, userPayloads.getFirstName());
		Assert.assertEquals(lastName, userPayloads.getLastName());
		Assert.assertEquals(phone, userPayloads.getPhone());
		
		log.info("+++++++++++ Read Created user and correct user got created  ++++++++++");
	}
	
	@Test(priority = 3)
	public void testPutUser() {
		log.info("+++++++++++ Updating the User ++++++++++");
		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setPhone(faker.phoneNumber().cellPhone());
		
		Response response=UserEndpoints2.updateUser(userPayloads,this.userPayloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
		// valdidation
		Response response2=UserEndpoints2.readUser(userPayloads.getUsername());
		response2.then().log().all();
		Assert.assertEquals(response2.statusCode(), 200);
		log.info("+++++++++++ Updated the User ++++++++++");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
	
		log.info("+++++++++++ Deleting the User ++++++++++");
		Response response=UserEndpoints2.deleteUser(this.userPayloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		log.info("+++++++++++ Deleted the User ++++++++++");
	}
	
}
