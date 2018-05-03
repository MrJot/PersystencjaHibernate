import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainTestClass {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(SchoolClass.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Student student = session.get(Student.class, 1);
			List<Student> theStudents = session
										.createQuery("from Student")
										.getResultList();
			System.out.println(student.getName());
			System.out.println(theStudents);
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}

		
		
	}

}
