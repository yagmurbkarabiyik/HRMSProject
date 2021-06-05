package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;

public interface CvService {
	
	DataResult<List<Cv>> getAll();
	DataResult<Cv> getById(int id);
	
	Result add(Cv cv);
	Result delete(int id);
	
	
}
