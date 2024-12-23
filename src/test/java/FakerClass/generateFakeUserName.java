package FakerClass;

import com.github.javafaker.Faker;
public class generateFakeUserName {
public static void main(String[] args) {
	Faker faker = new Faker();
	String mobileNumber = faker.phoneNumber().cellPhone();
	System.out.println(mobileNumber);
}
}