package persist.src;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="schoolClasses")
public class SchoolClass implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "startYear")
	private int startYear;
	@Column(name="currentYear")
	private int currentYear;
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}


	@Column(name = "profile")
	private String profile;
	



	@OneToMany(mappedBy="schoolClass",
			cascade= {CascadeType.DETACH,CascadeType.MERGE,
					  CascadeType.PERSIST,CascadeType.REFRESH})
	private Set <Student> studList;
	
	
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,
						 CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="school_id")
	private School school;
	
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinTable(
			name="teacher_class", 
			joinColumns=@JoinColumn(name="class_id"),
			inverseJoinColumns=@JoinColumn(name="teacher_id")
					)
	private List<Teacher> teachers;

	public SchoolClass(int startYear, int currentYear, String profile) {
		this.startYear = startYear;
		this.currentYear = currentYear;
		this.profile = profile;
	}
	
	public SchoolClass() {
	}


	public School getSchool() {
		return school;
	}



	public void setSchool(School school) {
		this.school = school;
	}



	public Set<Student> getStudList() {
		return studList;
	}



	public void setStudList(Set<Student> studList) {
		this.studList = studList;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
			studList.add(student);
			student.setSchoolClass(this);
		}else {
			studList.add(student);
			student.setSchoolClass(this);
		}
	}
	
	public void add(Teacher teacher) {
		if (teachers==null) {
			teachers = new ArrayList<Teacher>();
		}
		teachers.add(teacher);
	}
}