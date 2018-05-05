package persist.queries;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import persist.setup.files.Factory;
import persist.src.School;
import persist.src.SchoolClass;
import persist.src.Teacher;

public class ManyToManyTestDrive {
	
	/**
	 * Tworzy jednego nauczyciela i dwie klasy
	 */
	
	public static void createTeacherAndClasses() {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Teacher teacher = new Teacher("Mariusz", "Zbien", "zbien@gmail.com");
			
			session.save(teacher);
			
			School school = session.get(School.class, 1);
			SchoolClass class_one = new SchoolClass(2009, 3, "mat-ekonom");
			SchoolClass class_two = new SchoolClass(2010, 5, "mat-human");
			
			teacher.addSchoolClass(class_one);
			teacher.addSchoolClass(class_two);
			
			school.add(class_one);
			school.add(class_two);
			
			session.save(class_one);
			session.save(class_two);
			
			session.getTransaction().commit();
			
		}finally {
			session.close();
			factory.close();
		}
	}
	
	
	/**
	 * Tworzy jedna klase i dwoch nauczycieli
	 */
	public static void createClassAndTeachers() {
		SessionFactory factory = Factory.factorySetup();
		Session session  = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			SchoolClass tempClass= new SchoolClass(2012, 1, "religijno-przemyslowy");
			Teacher teacher_one = new Teacher("Tadeusz", "Rydzyk", "rydzyk@buziaczek.pl");
			Teacher teacher_two = new Teacher("Antoni", "Macierewicz","macierewicz@gmail.com");
			tempClass.add(teacher_one);
			tempClass.add(teacher_two);
			session.save(tempClass);
			session.save(teacher_one);
			session.save(teacher_two);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	
	///Queries
	
	/**
	 * Znajdz wszystkie klasy ktore sa przypisane do okreslonego nauczyciela
	 * @param Nazwisko_nauczyciela
	 */
	
	public static void findClassesBasedOnTeacherName(String name) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Teacher teacher = (Teacher) session.createQuery("from Teacher s where s.surname LIKE '%" +name+ "'").getSingleResult();
			System.out.println("Classes assigned to: "+teacher.getName()+" "+teacher.getSurname());
			List<SchoolClass> classes = teacher.getClasses();
			for(SchoolClass c:classes) {
				System.out.println(c.getProfile()+" at School: "+ c.getSchool().getName());
			}
			session.getTransaction().commit();
			
		}finally {
			session.close();
			factory.close();
		}
	}
	
	
	
	/**
	 * Znajdz wszystkich nauczycieli ktorzy sa przypisani do okreslonego profilu klasy
	 * @param profil_klasy
	 */
	public static void findTeachersBasedonClassProfile(String profile) {
		
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<SchoolClass> tempClass = session.createQuery("from SchoolClass s where s.profile LIKE '%" +profile+ "'").getResultList();
			System.out.println("Requested class profile: "+profile);
			System.out.println("Teachers assigned to selected profile: ");
			for(SchoolClass sc:tempClass) {
				System.out.println("School name: "+sc.getSchool());
				for(Teacher t:sc.getTeachers()) {
					System.out.println("Teacher name: "+t.getName()+" "+t.getSurname());
					
				}
			}

		}finally {
			session.close();
			factory.close();
		}
					
	}
	
	/**
	 * Usuwanie nauczyciela.
	 * @param lastName
	 */
	
	public static void deleteTeacherBasedOnLastName(String lastName) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Teacher teacher = (Teacher) session.createQuery("from Teacher s where s.surname LIKE '%" +lastName+ "'").getSingleResult();
			session.delete(teacher);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	/**
	 * Dodawanie klasy do istniejacego nauczyciela
	 * @param lastName
	 * @param startYear
	 * @param currentYear
	 * @param profile
	 */
	
	public static void addClassToExistingTeacher(String lastName, int startYear, int currentYear, String profile) {
		SessionFactory factory = Factory.factorySetup();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			School school = session.get(School.class, 1);
			Teacher teacher = (Teacher) session.createQuery("from Teacher s where s.surname LIKE '%" +lastName+ "'").getSingleResult();
			SchoolClass schoolClass = new SchoolClass(startYear, currentYear, profile);
			school.add(schoolClass);
			teacher.addSchoolClass(schoolClass);
			session.save(schoolClass);
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}
	
	

}
