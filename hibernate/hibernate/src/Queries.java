import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Queries {
	
	//Chcemy znaleźć tylko szkoły, których nazwa to UE. (Podpowiedź: użyj składni WHERE tabela.kolumna='wartosc')
	
	public static void findSchools(String schoolName) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<School> school = session.createQuery("from School s where s.name LIKE '%"+schoolName+"%'").getResultList();
			
			for (School s:school){
				System.out.println("School name: "+s.getName()+". School adress: "+s.getAddress());
			}
			System.out.println("DONE");
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
	

	
	
	//Napisz zapytanie, które zwraca ilość szkół w bazie (Podpowiedź: użyj funkcji COUNT())
	
	public static void countTotalNumberOfSchools() {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			long schoolNumber= (Long) session.createQuery("select count(*) from School").getSingleResult();
			System.out.println("Total number of Schools: "+schoolNumber);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	//Napisz zapytanie, które zwraca ilość studentów w bazie.
	
	public static void countTotalNumberOfStudents() {
		
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			long studentNumber= (Long) session.createQuery("select count(*) from Student").getSingleResult();
			System.out.println("Total number of Students: "+studentNumber);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
		
		
	}
	
	//Napisz zapytanie, które zwraca wszystkie szkoły o liczbie klas większej lub równej 2.
	
	public static void printSchoolsWithClassNumberHigherEqualTwo() {
	SessionFactory factory = Factory.factorySetup();
	Session session = factory.getCurrentSession();
	try {
		session.beginTransaction();
		List<School> requestedScholls = session.createQuery("from SchoolClass School s where s.id=2").getResultList();
		System.out.println("Requested Schools: "+requestedScholls);
		session.getTransaction().commit();
	}finally {
		session.close();
		factory.close();
	}
		
	}
	
	//Poniższe zapytanie wyszukuje szkołę, w której występuje klasa o profilu “biol-chem”. 
	//Bazując na tym zapytaniu napisz nowe zapytanie, które wyszukuje szkołę z klasą o 
	//profilu mat-fiz oraz obecnym roku większym bądź równym 2
	
	public static void findSchoolBasedOnClassProfile(String classProfile) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<School> requestedSchools = session.createQuery("SELECT s FROM School s INNER JOIN s.classes classes WHERE classes.profile = '"+classProfile+"'").getResultList();
			for(School school:requestedSchools) {
				System.out.println("Requested Schools: "+school.getName()+" ,adress: "+school.getAddress());
			}
			session.getTransaction().commit();
			
		}finally {
			session.close();
			factory.close();
			
		}
			
	}
	

	
	//Wypisz studentow dla zdefiniowanego profilu klasy
	
	public static void printStudentsUsingClassName(String keyWord) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<SchoolClass> schoolClass = session.createQuery("from SchoolClass s where s.profile LIKE '%"+keyWord+"'").getResultList();
			System.out.println("Students enrolled to "+keyWord+" class:");
			for(SchoolClass s:schoolClass) {
				for(Student stu:s.getStudList()) {
					System.out.println(stu.getName()+" "+stu.getSurname()+" ,PESEL "+stu.getPesel());
				}
			}
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
