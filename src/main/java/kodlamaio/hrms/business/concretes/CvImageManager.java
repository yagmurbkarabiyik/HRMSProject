package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.CvImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvImageDao;
import kodlamaio.hrms.entities.concretes.CvImage;

@Service
public class CvImageManager implements CvImageService {

	private CvImageDao cvImageDao;

	@Autowired
	public CvImageManager(CvImageDao cvImageDao) {
		super();
		this.cvImageDao = cvImageDao;

	}

	@Override
	public DataResult<List<CvImage>> getAll() {
		return new SuccessDataResult<List<CvImage>>(this.cvImageDao.findAll(), "listed");
	}

	@Override
	public DataResult<CvImage> getById(int cvImageId) {
		if (!this.cvImageDao.existsById(cvImageId)) {
			return new ErrorDataResult<CvImage>("not found");
		}
		return new SuccessDataResult<CvImage>(this.cvImageDao.getOne(cvImageId));
	}

	@Override
	public Result add(CvImage cvImage) {
		this.cvImageDao.save(cvImage);
		return new SuccessResult("added");
	}

	@Override
	public Result delete(int id) {
		this.cvImageDao.deleteById(id);
		return new SuccessResult("deleted");
	}

	@Override
	public Boolean isExists(int id) {
		return this.cvImageDao.existsById(id);
	}
}
