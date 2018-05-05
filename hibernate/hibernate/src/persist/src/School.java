package persist.src;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="schools")
public class School implements java.io.Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@OneToMany(mappedBy="school",
			cascade= {CascadeType.DETACH,CascadeType.MERGE,
					  CascadeType.PERSIST,CascadeType.REFRESH})
	private Set<SchoolClass> classes;

	public School() {
	}
	
	

	public School(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public Set<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}

	public String toString() {
		return "School: " + getName() + " (" + getAddress() + ")";
}
	
	public void add(SchoolClass schoolClass) {
		if(classes==null) {
			classes=new HashSet<SchoolClass>();
			classes.add(schoolClass);
			schoolClass.setSchool(this);
		}else {
			classes.add(schoolClass);
			schoolClass.setSchool(this);
		}
	}

}
