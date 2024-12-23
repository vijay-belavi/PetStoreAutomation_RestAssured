package FakerClass;

import com.github.javafaker.Faker;
public class generateFakeFirstName {
public static void main(String[] args) {
	Faker faker = new Faker();
	String firstName = faker.name().firstName();
	System.out.println(firstName);
}
}