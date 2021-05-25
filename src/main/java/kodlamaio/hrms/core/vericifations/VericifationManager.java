package kodlamaio.hrms.core.vericifations;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class VericifationManager implements VerificationService {

	@Override
	public void sendLink(String email) {
		UUID uuid = UUID.randomUUID();
		String verificationLink = "https://hrmsverification/" + uuid.toString();
		System.out.println("Verification mail has been send to : " + email);

		System.out.println("Clink on the link and verify your account " + verificationLink);
	}

	@Override
	public void sendCode(String email) {

		UUID uuid = UUID.randomUUID();
		String verificationCode = "https://hrmsverification/" + uuid.toString();
		System.out.println("Verification mail has been send to : " + email);

		System.out.println("Clink on the link and verify your account " + verificationCode);
	}

}
