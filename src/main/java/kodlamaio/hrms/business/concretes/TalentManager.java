package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TalentService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.TalentDao;
import kodlamaio.hrms.entities.concretes.Talent;

@Service
public class TalentManager implements TalentService{

	private TalentDao talentDao;
	
	@Autowired
	public TalentManager(TalentDao talentDao) {
		super();
		this.talentDao = talentDao;
	}

	@Override
	public DataResult<List<Talent>> getAll() {
		return new SuccessDataResult<List<Talent>>(this.talentDao.findAll(), Messages.TALENT_LISTED);
	}

	@Override
	public Result add(Talent talent) {
		this.talentDao.save(talent);
		return new SuccessResult(Messages.TALENT_ADDED);
	}

	@Override
	public DataResult<Talent> getById(int talentId) {
		return new SuccessDataResult<Talent>(this.talentDao.getOne(talentId));
	}

	@Override
	public Result delete(int talentId) {
		this.talentDao.deleteById(talentId);
		return new SuccessResult("Deleted!");
	}

}
