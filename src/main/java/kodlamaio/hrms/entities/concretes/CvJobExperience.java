package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv_job_experiences")
public class CvJobExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "cv_id")
	private Cv cv;
	
	@Column(name = "company_name")
	@NotNull
	@NotBlank
	private String companyName;
	
	@Column(name = "position")
	private String position;
	
	
	@Column(name = "starting_date")
	@NotNull
	private LocalDate startingDate;
	
	@Column(name = "finishing_date")
	private LocalDate finishingDate;
	
	@Column(name = "created_date")
	@JsonIgnore
	private LocalDate createdDate = LocalDate.now();
	
	
	
	
}
