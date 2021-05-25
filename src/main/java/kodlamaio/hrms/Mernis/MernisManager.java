package kodlamaio.hrms.Mernis;

import java.time.LocalDate;

public class MernisManager {

	public boolean validatePerson(String firstName, String lastName, String identityNumber, LocalDate birthOfYear) {
		System.out.println(firstName + " " + lastName + " is a valid person!");
		return true;
	}
}
