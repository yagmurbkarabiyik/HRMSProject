package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor

public class Candidate extends User{

	@Column( name = "first_name")
	private String firstName;
	
	@Column( name = "last_name")
	private String lastName;
	
	@Column( name = "identity_number")
	private String identityNumber;
	
	@Column( name = "birth_date")
	private LocalDate birthOfYear;
	
	
	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<Cv> cv;
	
}
