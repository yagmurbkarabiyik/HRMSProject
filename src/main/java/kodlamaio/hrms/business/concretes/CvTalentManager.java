package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvTalentService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvTalentDao;
import kodlamaio.hrms.entities.concretes.CvTalent;

@Service
public class CvTalentManager implements CvTalentService {

	private CvTalentDao cvTalentDao;
	
	@Autowired
	 public CvTalentManager(CvTalentDao cvTalentDao) {
		 super();
		 this.cvTalentDao = cvTalentDao;
		 
	}
	@Override
	public DataResult<List<CvTalent>> getAll() {
		return new SuccessDataResult<List<CvTalent>>(this.cvTalentDao.findAll(), Messages.CV_TALENT_LISTED);
	}

	@Override
	public Result add(CvTalent cvTalent) {
		this.cvTalentDao.save(cvTalent);
		return new SuccessResult(Messages.CV_TALENT_ADDED);
	}
	@Override
	public Result delete(int cvTalentId) {
		this.cvTalentDao.deleteById(cvTalentId);
		return new SuccessResult("Deleted!");
}

}
