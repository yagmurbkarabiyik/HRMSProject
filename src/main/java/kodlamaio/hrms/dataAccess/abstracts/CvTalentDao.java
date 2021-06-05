package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.CvTalent;

public interface CvTalentDao extends JpaRepository<CvTalent, Integer> {

}
