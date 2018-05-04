import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="students")
public class Student implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="pesel")
	private String pesel;

	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="class_id")
	private SchoolClass schoolClass;


	public SchoolClass getSchoolClass() {
		return schoolClass;
	}



	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Student(String name, String surname, String pesel) {
		super();
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.schoolClass = schoolClass;
	}
	
	public Student() {
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getPesel() {
		return pesel;
	}



	public void setPesel(String pesel) {
		this.pesel = pesel;
	}



	public String toString() {
		return "Students: " + getName() + getSurname()+","+"PESEL:"+getPesel();
}

}
