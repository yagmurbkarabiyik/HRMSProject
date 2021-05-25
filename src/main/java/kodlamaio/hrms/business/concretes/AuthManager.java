package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.adapters.UserCheckService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.vericifations.VerificationService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class AuthManager implements AuthService {

	private UserService userService;
	private CandidateService candidateService;
	private EmployerService employerService;
	private VerificationService verificationService;
	private UserCheckService userCheckService;

	@Autowired
	public AuthManager(UserService userService, CandidateService candidateService, EmployerService employerService,
			VerificationService verificationService, UserCheckService userCheckService) {
		super();
		this.userService = userService;
		this.candidateService = candidateService;
		this.employerService = employerService;
		this.verificationService = verificationService;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result registerCandidate(Candidate candidate) {
		if (checkIfRealPerson(candidate.getFirstName(), candidate.getLastName(), candidate.getIdentityNumber(),
				candidate.getBirthOfYear()) == false) {

			return new ErrorResult(Messages.MERNIS_VERIFICATION_ERROR);

		}
		if (!checkIfNullValueInCandidates(candidate)) {
			return new ErrorResult(Messages.CANDIDATE_NULL_ERROR);
		}

		if (!checkIfExistIdentityNumber(candidate.getIdentityNumber())) {
			return new ErrorResult(candidate.getIdentityNumber() + Messages.CANDIDATE_IDENTITY_NUMBER_ERROR);
		}
		if (!checkIfEmailExist(candidate.getEmail())) {
			return new ErrorResult(candidate.getEmail() + Messages.CANDIDATE_EMAIL_ERROR);

		}

		verificationService.sendLink(candidate.getEmail());
		candidateService.add(candidate);
		return new SuccessResult(Messages.CANDIDATE_REGISTIRATION_COMPLETED);

	}

	@Override
	public Result registerEmployer(Employer employer) {

		if (!checkIfNullValueInEmployer(employer)) {
            return new ErrorResult(Messages.EMPLOYER_LISTED);
        }

        if (!checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebAddress())) {
            return new ErrorResult(Messages.EMPLOYER_EMAIL_ERROR);
        }

        if(!checkIfEmailExist(employer.getEmail())) {
            return new ErrorResult(employer.getEmail() + Messages.EMPLOYER_EMAIL_EXIST_ERROR);
        }

        verificationService.sendLink(employer.getEmail());
        employerService.add(employer);
        return new SuccessResult(Messages.EMPLOYER_REGISTIRATION_COMPLETED);
		
	}

	// Candidate Validation

	private boolean checkIfNullValueInCandidates(Candidate candidate) {
		if (candidate.getFirstName() != null && candidate.getLastName() != null && candidate.getEmail() != null
				&& candidate.getPassword() != null && candidate.getBirthOfYear() != null) {

			return true;

		}
		return false;
	}

	private boolean checkIfExistIdentityNumber(String identityNumber) {
		if (this.candidateService.getCandidatesByIdentityNumber(identityNumber).getData() == null) {
			return true;
		}
		return false;
	}

	private boolean checkIfRealPerson(String firstName, String lastName, String identityNumber, LocalDate birthOfYear) {
		if (this.userCheckService.checkIfRealPerson(firstName, lastName, identityNumber, birthOfYear)) {

			return true;

		}
		return false;
	}

	// Employer Validation
	private boolean checkIfNullValueInEmployer(Employer employer) {
		if (employer.getCompanyName() != null && employer.getWebAddress() != null && employer.getEmail() != null
				&& employer.getPhoneNumber() != null && employer.getPassword() != null) {
			return true;
		}

		return false;
	}

	private boolean checkIfEqualEmailAndDomain(String email, String webAddress) {

		String[] emailArr = email.split("@", 2);
		String domain = webAddress.substring(4, webAddress.length());

		if (emailArr[1].equals(domain)) {
			return true;
		}

		return false;

	}

	// Common Validation

	private boolean checkIfEmailExist(String email) {
		if (this.userService.getUserByEmail(email).getData() == null) {
			return true;
		}

		return false;
	}
}
