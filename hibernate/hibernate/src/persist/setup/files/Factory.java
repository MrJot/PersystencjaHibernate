package persist.setup.files;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import persist.src.School;
import persist.src.SchoolClass;
import persist.src.Student;
import persist.src.Teacher;

public class Factory {
	
	public static SessionFactory factorySetup() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(SchoolClass.class)
				.addAnnotatedClass(School.class)
				.addAnnotatedClass(Teacher.class)
				.buildSessionFactory();
		return factory;
	}
	
	

}
