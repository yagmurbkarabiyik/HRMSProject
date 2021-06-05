package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.entities.concretes.CvLanguage;

@RestController
@RequestMapping("/api/cvLanguages")
public class CvLanguageController {
	
	private CvLanguageService cvLanguageService;

	@Autowired
	public CvLanguageController(CvLanguageService cvLanguageService) {
		super();
		this.cvLanguageService = cvLanguageService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CvLanguage cvLanguage) {
		var result = this.cvLanguageService.add(cvLanguage);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		var result = this.cvLanguageService.delete(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		var result = this.cvLanguageService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}

	
	
	
}
