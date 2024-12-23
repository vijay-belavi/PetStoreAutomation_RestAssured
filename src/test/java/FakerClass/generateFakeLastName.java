package FakerClass;

import com.github.javafaker.Faker;
public class generateFakeLastName {
public static void main(String[] args) {
	Faker faker = new Faker();
	String lastName = faker.name().fullName();
	System.out.println(lastName);
}
}