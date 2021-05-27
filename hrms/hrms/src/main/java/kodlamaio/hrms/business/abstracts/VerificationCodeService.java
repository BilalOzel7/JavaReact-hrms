package kodlamaio.hrms.business.abstracts;



import kodlamaio.hrms.core.results.Result;
import kodlamaio.hrms.entities.concretes.EMailVerificationCode;


public interface VerificationCodeService {

	void generateCode(EMailVerificationCode code,Integer id);
	Result verify(String verificationCode,Integer id);
}
