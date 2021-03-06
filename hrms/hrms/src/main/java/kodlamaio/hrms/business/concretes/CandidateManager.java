package kodlamaio.hrms.business.concretes;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.results.DataResult;
import kodlamaio.hrms.core.results.ErrorDataResult;
import kodlamaio.hrms.core.results.Result;
import kodlamaio.hrms.core.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.validaitons.IdentityValidationService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.EMailVerificationCode;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private VerificationCodeService verificationCodeService;
	private UserService userService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao,VerificationCodeService verificationCodeService,
			UserService userService) {
		super();
		this.candidateDao = candidateDao;
	    this.verificationCodeService = verificationCodeService;
		this.userService = userService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll());
	}

	@Override
	public Result add(Candidate candidate) {
		if (!firstNameChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Ad bilgisi boş olamaz!");
		}

		else if (!lastNameChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Soyadı bilgisi boş olamaz!");
		}

		else if (!IdentityValidationService.isRealPerson(candidate.getIdentityNumber())) {
			return new ErrorDataResult<Candidate>(null, "Kimlik Doğrulanamadı.");
		} else if (candidate.getIdentityNumber().isBlank()) {
			return new ErrorDataResult<Candidate>(null, "Tc Kimlik bilgisi boş olamaz!");
		}

		else if (!birthDateChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Doğum Tarihi bilgisi boş olamaz!");
		}

		else if (!emailNullChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Email Bilgisi zorunludur!");
		} else if (!isRealEmail(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Email adresiniz yanlış!");
		}

		else if (!passwordNullChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null, "Şifre Bilgisi zorunludur!");
		}

		else if (candidateDao.findAllByEmail(candidate.getEmail()).stream().count() != 0) {
			return new ErrorDataResult<Candidate>(null, "Email Zaten Kayıtlı");
		}

		else if (candidateDao.findAllByIdentityNumber(candidate.getIdentityNumber()).stream()
				.count() != 0) {
			return new ErrorDataResult<Candidate>(null, "TC Numarası Zaten Kayıtlı");
		}

		Result savedUser = this.userService.add(candidate);

		this.verificationCodeService.generateCode(new EMailVerificationCode(), savedUser.hashCode());

		return new SuccessDataResult<Candidate>(this.candidateDao.save(candidate),
				"İş Arayan Eklendi , Doğrulama Kodu Gönderildi: " + candidate.getId());
	}

	private boolean firstNameChecker(Candidate candidate) {
		if (candidate.getFirstName().isBlank() || candidate.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean lastNameChecker(Candidate candidate) {
		if (candidate.getLastName().isBlank() || candidate.getLastName().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean birthDateChecker(Candidate candidate) {
		if (candidate.getBirthDay().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean emailNullChecker(Candidate candidate) {
		if (candidate.getEmail().isBlank() || candidate.getEmail().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean passwordNullChecker(Candidate candidate) {
		if (candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean isRealEmail(Candidate candidate) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(candidate.getEmail());
		if (!matcher.matches()) {
			return false;
		}
		return true;

	}
	
	

}
