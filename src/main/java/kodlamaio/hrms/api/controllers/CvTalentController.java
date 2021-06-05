package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.CvTalentService;
import kodlamaio.hrms.entities.concretes.CvTalent;

@RestController
@RequestMapping("/api/cvTalents")
public class CvTalentController {

	private CvTalentService cvTalentService;

	@Autowired
	public CvTalentController(CvTalentService cvTalentService) {
		super();
		this.cvTalentService = cvTalentService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CvTalent cvTalent) {
		var result = this.cvTalentService.add(cvTalent);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		var result = this.cvTalentService.delete(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		var result = this.cvTalentService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

}
