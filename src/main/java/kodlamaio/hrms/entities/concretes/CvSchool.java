package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv_schools")
public class CvSchool {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "cv_id")
	private Cv cv;
	
	@Column(name = "school_name")
	@NotNull
	@NotBlank
	private String schoolName;
	
	@Column(name = "department")
	@NotNull
	@NotBlank
	private String department;
	
	@Column(name = "starting_date")
	@NotNull
	private LocalDate startingDate;
	
	@Column(name = "finishing_date")
	private LocalDate finishingDate;
	
	@Column(name = "created_date")
	@JsonIgnore
	private LocalDate createdDate = LocalDate.now();
	
	
	
}
