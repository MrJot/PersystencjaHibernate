import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="schoolClasses")
public class SchoolClass implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name = "startYear")
	private int startYear;
	@Column(name="currentYear")
	private int currentYear;
	@Column(name = "profile")
	private String profile;
	
	@OneToMany(mappedBy="schoolClass",
			cascade= {CascadeType.DETACH,CascadeType.MERGE,
					  CascadeType.PERSIST,CascadeType.REFRESH})
	private Set <Student> studList;



	public Set<Student> getStudList() {
		return studList;
	}



	public void setStudList(Set<Student> studList) {
		this.studList = studList;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String toString() {
		return "Class: " + profile + " (Started: " + getStartYear() + ", Current year: " + getCurrentYear() + ")";
	}
	
	
	public void add(Student student) {
		if(studList==null) {
			studList=new HashSet<Student>();
		}else {
			studList.add(student);
			student.setSchoolClass(this);
		}
	}
}