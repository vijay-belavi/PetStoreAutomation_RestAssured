package Api_Endpoints;
/*
 * Swagger URI --> https://petstore.io
 * 
 * Create User (Post) : https://petstore.io/v2/user
 * Get User (Get) : https://petstore.io/v2/user/{username}
 * Update User (Put) : https://petstore.io/v2/user/{username}
 * Delete User (Delete) : https://petstore.io/v2/user/{username}
 */
public class Routes {

	//Keeping the Base Url in a variable
	public static String base_url = "https://petstore.swagger.io/v2";
	
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String put_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//Store Module
	
}
