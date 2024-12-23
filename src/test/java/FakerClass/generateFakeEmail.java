package FakerClass;

import com.github.javafaker.Faker;
public class generateFakeEmail {
public static void main(String[] args) {
	Faker faker = new Faker();
	String email = faker.internet().safeEmailAddress();
	email = email.replace("example", "gmail");
	System.out.println(email);
}
}