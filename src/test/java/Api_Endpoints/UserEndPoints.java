package Api_Endpoints;

import static io.restassured.RestAssured.given;

import Api_Payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {

	public static Response createUser(UserPayload payload) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String userName) {
		
		Response response = given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPayload payload) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.put_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		Response response = given()
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
}
