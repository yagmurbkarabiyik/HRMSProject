package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvImage;

public interface CvImageService {

	
	DataResult<List<CvImage>> getAll();
    DataResult<CvImage> getById(int cvImageId);

    Result add(CvImage cvImage);
    Result delete(int id);

    Boolean isExists(int id);
}
