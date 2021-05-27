package kodlamaio.hrms.core.utilities.validaitons;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.results.Result;
import kodlamaio.hrms.core.results.SuccessResult;

public class MernisServiceAdapter implements IdentityValidationService{

	@Override
	public Result validate(String tckn, String firstName, String lastName, int yearOfDate) {
		
		 return new SuccessResult(Messages.IdentityNumberIs());
	}

}
