package persist.src;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import persist.queries.DatabaseModification;
import persist.queries.ManyToManyTestDrive;
import persist.queries.Queries;

public class Main {

	public static void main(String[] args) {
		
		//Queries
		
//		Queries.countTotalNumberOfSchools();
//		Queries.countTotalNumberOfStudents();
//		Queries.findSchoolBasedOnClassProfile("ekonom");
//		Queries.findSchools("UE");
//		Queries.printSchoolsWithClassNumberHigherEqualTwo();
//		Queries.printStudentsUsingClassName("mat-fiz");
//		
//		//DatabseModification
//		DatabaseModification.addNewStudent("Marcin", "Krzysztal", "00432230003");
//		DatabaseModification.addSchoolClassStudents();
//		DatabaseModification.changeSchooolAdress("Mikolajska 4", 1);
//		DatabaseModification.deleteSchoolsUsingName("AGH");
//		
//		//ManyToManyRelation
		ManyToManyTestDrive.createClassAndTeachers();
//		ManyToManyTestDrive.createTeacherAndClasses();
//		ManyToManyTestDrive.findClassesBasedOnTeacherName("Maziarz");
//		ManyToManyTestDrive.findTeachersBasedonClassProfile("mat-fiz");
//		ManyToManyTestDrive.addClassToExistingTeacher("Maziarz", 1987, 4, "maszynowy");
//		ManyToManyTestDrive.deleteTeacherBasedOnLastName("Zbien");

		
	}
	


	

}
