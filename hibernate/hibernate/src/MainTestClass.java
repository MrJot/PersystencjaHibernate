import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainTestClass {

	public static void main(String[] args) {

		//Add School, schoolClass, Student
/*
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			School school = new School("Krakowskie LO", "Al. Pokoju 18");
			SchoolClass schoolClass = new SchoolClass(2008, 4, "bio-chem");
			Student student = new Student("Adam", "Kromka", "3214242121");
			session.save(school);
			school.add(schoolClass);
			session.save(schoolClass);
			schoolClass.add(student);
			session.save(student);
			session.getTransaction().commit();
		}catch(Exception ecp) {
			ecp.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
*/
		
		ManyToManyTestDrive.createTeacherAndClasses();
		
		//MainTestClass.printStudentsUsingClassName("bio-chem");
	}
	
	//Printing Students enrolled to specific class profile

	
	
	
	
	// Added new student to specific class profile
	public static void addNewStudent(String profileName, String studentName, String studentSurname, String pesel) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<SchoolClass> schoolClass = session.createQuery("from SchoolClass s where s.profile LIKE '%"+profileName+"'").getResultList();
			Student student = new Student(studentName, studentSurname, pesel);
			schoolClass.get(0).add(student);
			session.save(student);
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
	public static void findSchoolsWithSpecificName(String schoolName) {
		
	}
	

}
