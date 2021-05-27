package kodlamaio.hrms.core.utilities.validaitons;

import kodlamaio.hrms.core.results.Result;

public interface IdentityValidationService {
	Result validate(String tckn, String firstName, String lastName, int yearOfDate);

	static boolean isRealPerson(String identityNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
