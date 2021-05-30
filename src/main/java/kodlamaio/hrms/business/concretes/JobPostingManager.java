package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService {
	
	private JobPostingDao jobPostingDao;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao) {
		super();
		this.jobPostingDao = jobPostingDao;
	}

	
	@Override
	public DataResult<JobPosting> getById(int id) {
		return new SuccessDataResult<JobPosting>(this.jobPostingDao.getOne(id));
		
	
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {

		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(), Messages.JOB_POSTING_LISTED);
		
	}

	@Override
	public DataResult<List<JobPosting>> getAllOpenJobPostingList() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getAllOpenJobPostingList());
	}

	@Override
	public DataResult<List<JobPosting>> getAllByOrderBypublishedAtDesc() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getAllByOrderBypublishedAtDesc());
	}

	@Override
	public DataResult<List<JobPosting>> getAllOpenJobPostingByEmployer(int id) {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getAllOpenJobPostingByEmployer(id));
	}

	
	@Override
	public Result add(JobPosting jobPosting) {
		if (!checkIfNullField(jobPosting)) {
			return new ErrorResult(Messages.JOB_POSTING_NULL_ERROR);
			
		}
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult(Messages.JOB_POSTING_ADDED);
	
	}

	@Override
	public Result update(JobPosting jobPosting) {
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult(Messages.JOB_POSTING_UPDATED);
	}

	@Override
	public Result changeToOpenClose(int id) {
		if (getById(id) == null) {
			return new ErrorResult(Messages.JOB_POSTING_CHANGE_ERROR);
			
		}
		
		if (getById(id).getData().isOpen() == false ) {
			return new ErrorResult(Messages.JOB_POSTING_CHANGE_ERROR_TWO);
			
		}
		
		JobPosting jobPosting = getById(id).getData();
		jobPosting.setOpen(false);
		update(jobPosting);
		return new SuccessResult(Messages.JOB_POSTING_CHANGE);
		
	}
	//rules
	private boolean checkIfNullField(JobPosting jobPosting) {
		if (jobPosting.getJobTitle() != null && jobPosting.getCity() != null && jobPosting.getDescription() != null && jobPosting.getOpenPositionCount() != 0) {
			return true;
			
		}
		
		return false;
	}
	
	
}
