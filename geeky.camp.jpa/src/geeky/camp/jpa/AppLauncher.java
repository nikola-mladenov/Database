package geeky.camp.jpa;

import geeky.camp.jpa.dao.CourseDAO;
import geeky.camp.jpa.dao.FacultyDAO;
import geeky.camp.jpa.dao.StudentDAO;
import geeky.camp.jpa.entities.Course;
import geeky.camp.jpa.entities.Faculty;
import geeky.camp.jpa.entities.Student;

import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppLauncher {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("geeky.camp");
		StudentDAO studentDAO = new StudentDAO(emf);
		CourseDAO courseDAO = new CourseDAO(emf);
		FacultyDAO facultyDAO = new FacultyDAO(emf);
		courseDAO.createNewCourse("1", "SCRUM", 18);
		courseDAO.createNewCourse("2", "Java", 20);
		courseDAO.createNewCourse("3", "Himiq", 16);
		facultyDAO.createNewFaculty("10", "FMI");
		facultyDAO.createNewFaculty("11", "Medicinski");
		studentDAO
				.createNewStudent("4131", "mincho", "todorov", 17, new Date());
		studentDAO.createNewStudent("1141", "toshko", "petkov", 29, new Date());
		studentDAO.createNewStudent("1232", "pesho", "ivanov", 22, new Date());
		studentDAO.createNewStudent("1442", "elena", "mladenova", 31,
				new Date());
		studentDAO
				.createNewStudent("1336", "valeri", "rosenov", 24, new Date());
		studentDAO.createNewStudent("2213", "kosta", "tazobedrev", 26,
				new Date());
		studentDAO.createNewStudent("4123", "gosho", "nikolov", 27, new Date());
		studentDAO.createNewStudent("3121", "maria", "borislavova", 19,
				new Date());
		Student studentByFn = studentDAO.findByPrimaryKey("1232");
		Course courseById = courseDAO.findByPrimaryKey("3");
		Faculty id = facultyDAO.findByPrimaryKey("11");
		System.out.println("Found student : " + studentByFn);
		System.out.println("Found course : " + courseById);
		studentByFn.setFirstName("spascho");
		courseById.setCourseName("DataBase");
		id.setFacultyName("Fizicheski");
		courseDAO.updateCourse(courseById);
		courseDAO.deleteCourse(courseById.getId());
		studentDAO.updateStudent(studentByFn);
		studentDAO.deleteStudent(studentByFn.getId());
		facultyDAO.updateFaculty(id);
		facultyDAO.deleteFaculty(id.getId());

	}
}
