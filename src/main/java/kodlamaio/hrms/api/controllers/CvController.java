package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.entities.concretes.Cv;

@RestController
@RequestMapping("/api/cvs")
public class CvController {

	private CvService cvService;

	@Autowired
	public CvController(CvService cvService) {
		super();
		this.cvService = cvService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Cv cv) {
		var result = this.cvService.add(cv);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		var result = this.cvService.delete(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		var result = this.cvService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	

	@GetMapping("/getById")
	public ResponseEntity<?> getById(int id) {
		var result = this.cvService.getById(id);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
}
