package kodlamaio.hrms.core.vericifations;

public interface VerificationService {

	void sendLink(String email);
	void sendCode(String email);
	
	
}
