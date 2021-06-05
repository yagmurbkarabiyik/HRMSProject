package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.CvSchoolService;
import kodlamaio.hrms.entities.concretes.CvSchool;

@RestController
@RequestMapping("/api/cvSchools")
public class CvSchoolController {
	
	private CvSchoolService cvSchoolService;

	@Autowired
	public CvSchoolController(CvSchoolService cvSchoolService) {
		super();
		this.cvSchoolService = cvSchoolService;
	}
	

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CvSchool cvSchool) {
		var result = this.cvSchoolService.add(cvSchool);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		var result = this.cvSchoolService.delete(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		var result = this.cvSchoolService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	
	}
	
	@GetMapping("/getAllByCvIdOrderByFinishingDate")
	public ResponseEntity<?> getAllByCvIdOrderByFinishingDate(int cvId) {
		var result = this.cvSchoolService.getAllByCvIdOrderByFinishingDate(cvId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	
	}
	
}
