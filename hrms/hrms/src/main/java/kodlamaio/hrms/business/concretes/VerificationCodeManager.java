package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.results.ErrorDataResult;
import kodlamaio.hrms.core.results.Result;
import kodlamaio.hrms.core.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.validaitons.RandomCodeGenerate;
import kodlamaio.hrms.dataAccess.abstracts.EMailVerificationDao;
import kodlamaio.hrms.entities.concretes.EMailVerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService {

	private EMailVerificationDao emailVerificationDao;

	@Autowired
	public VerificationCodeManager(EMailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
	}

	@Override
	public void generateCode(EMailVerificationCode code, Integer id) {
		code.setCode(null);
		code.setVerified(false);
		if (code.isVerified() == false) {
			RandomCodeGenerate generator = new RandomCodeGenerate();
			String code_create = generator.create();
			code.setCode(code_create);
			code.setUserId(id);
			emailVerificationDao.save(code);
		}
		return;
	}

	@Override
	public Result verify(String verificationCode, Integer id) {
		EMailVerificationCode code = this.emailVerificationDao.getById(id);

		if (code.getCode().equals(verificationCode)) {													
			code.setVerified(true);
			return new SuccessDataResult<EMailVerificationCode>(this.emailVerificationDao.save(code), "Başarılı");
		}
		return new ErrorDataResult<EMailVerificationCode>(null, "Doğrulama Kodu Geçersiz");
	}

	

}
