import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManyToManyTestDrive {
	
	public static void createTeacherAndClasses() {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Teacher teacher = new Teacher("Mariusz", "Grzesiak", "grzesiak@gmail.com");
			
			session.save(teacher);
			
			SchoolClass class_one = new SchoolClass(2009, 3, "mat-ekonom");
			SchoolClass class_two = new SchoolClass(2010, 5, "mat-human");
			
			teacher.addSchoolClass(class_one);
			teacher.addSchoolClass(class_two);
			
			session.save(class_one);
			session.save(class_two);
			
			session.getTransaction().commit();
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
