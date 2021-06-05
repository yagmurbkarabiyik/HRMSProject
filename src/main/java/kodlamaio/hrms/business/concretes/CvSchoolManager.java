package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvSchoolService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvSchoolDao;
import kodlamaio.hrms.entities.concretes.CvSchool;

@Service
public class CvSchoolManager implements CvSchoolService {

	private CvSchoolDao cvSchoolDao;
	
		@Autowired	
		public CvSchoolManager(CvSchoolDao cvSchoolDao) {
		super();
		this.cvSchoolDao = cvSchoolDao;
	}
	
	@Override
	public DataResult<List<CvSchool>> getAll() {
		return new SuccessDataResult<List<CvSchool>>(this.cvSchoolDao.findAll(), Messages.CV_SCHOOL_LISTED);
	}

	@Override
	public Result add(CvSchool cvSchool) {
		this.cvSchoolDao.save(cvSchool);
		return new SuccessResult(Messages.CV_SCHOOL_ADDED);
	}

	@Override
	public DataResult<List<CvSchool>> getAllByCvIdOrderByFinishingDate(int cvId) {
		return new SuccessDataResult<List<CvSchool>>(this.cvSchoolDao.getAllByCvIdOrderByFinishingDate(cvId));
	}

	@Override
	public Result delete(int cvSchoolId) {
		this.cvSchoolDao.deleteById(cvSchoolId);
		return new SuccessResult("Deleted!");
	}

}
