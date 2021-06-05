package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.CvJobExperienceService;
import kodlamaio.hrms.entities.concretes.CvJobExperience;

@RestController
@RequestMapping("/api/cvJobExperiences")
public class CvJobExperienceController {

	private CvJobExperienceService cvJobExperienceService;

	@Autowired
	public CvJobExperienceController(CvJobExperienceService cvJobExperienceService) {
		super();
		this.cvJobExperienceService = cvJobExperienceService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CvJobExperience cvJobExperience) {
		var result = this.cvJobExperienceService.add(cvJobExperience);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		var result = this.cvJobExperienceService.delete(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		var result = this.cvJobExperienceService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/getAllByCvIdOrderByFinishingDate")
	public ResponseEntity<?> getAllByCvIdOrderByFinishingDate(int cvId) {
		var result = this.cvJobExperienceService.getAllByCvIdOrderByFinishingDateDesc(cvId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
}
