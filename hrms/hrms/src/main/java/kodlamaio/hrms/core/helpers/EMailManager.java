package kodlamaio.hrms.core.helpers;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.results.Result;
import kodlamaio.hrms.core.results.SuccessResult;

@Service
public class EMailManager implements EMailService{

	@Override
	public Result send(String receiver, String title, String message) {
		
		return new SuccessResult(Messages.validationSend());
	}

}
