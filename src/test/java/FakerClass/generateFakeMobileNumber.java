package FakerClass;

import com.github.javafaker.Faker;
public class generateFakeMobileNumber {
public static void main(String[] args) {
	Faker faker = new Faker();
	String userName = faker.name().username();
	
	String companyName = faker.company().name();
	System.out.println("bs: "+faker.company().bs());
	System.out.println("buzzword: "+faker.company().buzzword());
	System.out.println("catchPhrase: "+faker.company().catchPhrase());
	System.out.println("hashCode: "+faker.company().hashCode());
	System.out.println("industry: "+faker.company().industry());
	System.out.println("catchPhrase: "+faker.company().catchPhrase());
}
}