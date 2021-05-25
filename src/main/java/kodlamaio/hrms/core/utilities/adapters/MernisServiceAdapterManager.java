package kodlamaio.hrms.core.utilities.adapters;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.Mernis.MernisManager;

@Service
public class MernisServiceAdapterManager implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(String firstName, String lastName, String identityNumber, LocalDate birthOfYear) {

		MernisManager mernisManager = new MernisManager();

		boolean result = mernisManager.validatePerson(firstName, lastName, identityNumber, birthOfYear);

		if (!result) {
			System.out.println("Person is not valid!");
			return false;
		}

		return true;
	}

}
