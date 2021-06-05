package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvLanguageDao;
import kodlamaio.hrms.entities.concretes.CvLanguage;

@Service
public class CvLanguageManager implements CvLanguageService{

	private CvLanguageDao cvLanguageDao;
	
		@Autowired
		public CvLanguageManager(CvLanguageDao cvLanguageDao) {
		super();
		this.cvLanguageDao = cvLanguageDao;
		
	}
	@Override
	public DataResult<List<CvLanguage>> getAll() {
		return new SuccessDataResult<List<CvLanguage>>(this.cvLanguageDao.findAll(), Messages.CV_LANGUAGE_LISTED);
	}

	@Override
	public Result add(CvLanguage cvLanguage) {
		this.cvLanguageDao.save(cvLanguage);
		return new SuccessResult(Messages.CV_LANGUAGE_ADDED);
	}
	@Override
	public Result delete(int cvLanguageId) {
		this.cvLanguageDao.deleteById(cvLanguageId);
		return new SuccessResult("Deleted!");
	}

}
