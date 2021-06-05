package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvLanguage;

public interface CvLanguageService {

	DataResult<List<CvLanguage>> getAll();
	
	Result add(CvLanguage cvLanguage);
	Result delete(int cvLanguageId);
	
 }
