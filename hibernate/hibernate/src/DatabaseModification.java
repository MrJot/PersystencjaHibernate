import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DatabaseModification {
	
	public static void addSchoolClassStudents(){
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
	}
	
	//Zmień adres znalezionej szkoły i zapisz zmiany przy użyciu 
	//znanych Ci już metod (Podpowiedź: patrz kod dotyczące tworzenia dodawania nowych obiektów).
	
	public static void changeSchooolAdress(String address, int schoolId) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			School school=session.get(School.class, schoolId);
			school.setAddress(address);
			session.save(school);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	//Wykorzystując funkcję session.delete() i analogię do tworzenia obiektów, usuń wszystkie odnalezione w powyższym punkcie szkoły.
	
	public static void deleteSchoolsUsingName(String schoolName) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<School> school = session.createQuery("from School s where s.name LIKE '%"+schoolName+"%'").getResultList();
			System.out.println("Deleting requested schools....");
			for(School s:school) {
				session.delete(s);
			}
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	

}
