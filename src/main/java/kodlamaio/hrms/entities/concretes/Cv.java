package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cities"})

public class Cv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne()
    @JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@Column(name = "github_address")
	private String githubAddress;
	
	@Column(name = "likedin_address")
	private String linkedinAddress;
	
	@Column(name = "description")
	@NotNull
	@NotBlank
	private String description;

	@Column(name = "created_date")
	@JsonIgnore
	private LocalDate createdDate = LocalDate.now();

	@OneToMany(mappedBy = "cv")
	@JsonIgnore
	private List<CvJobExperience> cvJobExperiences;
	
	@OneToMany(mappedBy = "cv")
	@JsonIgnore
	private List<CvLanguage> cvLanguages;
	
	@OneToMany(mappedBy = "cv")
	@JsonIgnore
	private List<CvSchool> cvSchools;
	
	@OneToMany(mappedBy = "cv")
	@JsonIgnore
	private List<CvTalent> cvTalents;
	

	@OneToMany(mappedBy = "cv")
	@JsonIgnore
	private List<CvImage> cvImage;
	
 	
}
