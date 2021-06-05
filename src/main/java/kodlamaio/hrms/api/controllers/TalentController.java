package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.TalentService;
import kodlamaio.hrms.entities.concretes.Talent;

@RestController
@RequestMapping("/api/talents")
public class TalentController {

	private TalentService talentService;

	@Autowired
	public TalentController(TalentService talentService) {
		super();
		this.talentService = talentService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Talent talent) {
		var result = this.talentService.add(talent);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(int talentId) {
		var result = this.talentService.delete(talentId);

		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		var result = this.talentService.getAll();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
		
		
	}
	@GetMapping("/getById")
	public ResponseEntity<?> getById(int talentId) {
		var result = this.talentService.getById(talentId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
		
		
	}

}
