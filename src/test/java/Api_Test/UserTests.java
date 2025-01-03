package Api_Test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Api_Endpoints.UserEndPoints;
import Api_Payload.UserPayload;
import org.apache.logging.log4j.LogManager;
import io.restassured.response.Response;

public class UserTests {
	
	
	Faker faker;
	UserPayload userpayload;
	
	public Logger logger;
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userpayload = new UserPayload();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//To Generate Logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1) 
	public void testPostUser() {
		
		logger.info("**************** Creating User ********************");
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is Created********************");
	}
	
	@Test(priority = 2) 
	public void testGetUserByName() {
		
		logger.info("**************** Reading User Info ********************");
		
		Response response = UserEndPoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User Info Is Displayed ********************");
	
	}
	
	@Test(priority = 3) 
	public void testUpdateUserByName() {
		
		logger.info("**************** Updating User ********************");
		//update data using payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("**************** User is Updated ********************");
	}
	
	@Test(priority = 4) 
	void deleleUserByName() {
		
		logger.info("**************** Deleting User ********************");
		
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is Deleted********************");
	}	
}
