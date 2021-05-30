package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/jobPostings")
public class JobPostingController {

	private JobPostingService jobPostingService;

	@Autowired
	public JobPostingController(JobPostingService jobPostingService) {
		super();
		this.jobPostingService = jobPostingService;
	}

//	@GetMapping("/getbyid")
//	public DataResult<JobPosting> getById(int id) {
//		return this.jobPostingService.getById(id);
//
//	}

	@GetMapping("/getall")
	public DataResult<List<JobPosting>> getAll() {
		return this.jobPostingService.getAll();

	}

	@GetMapping("/getallopenjobpostinglist")
	public DataResult<List<JobPosting>> getAllOpenJobPostingList() {
		return this.jobPostingService.getAllOpenJobPostingList();
	}

	@GetMapping("/getallbyorderbypublishedatdesc")
	public DataResult<List<JobPosting>> getAllByOrderBypublishedAtDesc() {
		return this.jobPostingService.getAllByOrderBypublishedAtDesc();

	}

	@GetMapping("/getallopenjobpostingbyemployer")
	public DataResult<List<JobPosting>> getAllOpenJobPostingByEmployer(int id) {
		return this.jobPostingService.getAllOpenJobPostingByEmployer(id);
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobPosting jobPosting) {
		return this.jobPostingService.add(jobPosting);

	}

//	@PostMapping(name = "/update")
	// public Result update(JobPosting jobPosting) {
	// return this.jobPostingService.update(jobPosting);
	// }

	@PostMapping("/changetoopenclose")
	public Result changeToOpenClose(int id) {
		return this.jobPostingService.changeToOpenClose(id);

	}

}
