package geeky.camp.jpa.dao;

import geeky.camp.jpa.entities.Student;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

public class StudentDAO {
	protected EntityManagerFactory emf;

	public StudentDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void deleteStudent(Object primaryKey) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Student willBeDeleted = em.find(Student.class, primaryKey);
			em.remove(willBeDeleted);
			tx.commit();
			System.out.println("Entry : " + willBeDeleted + " removed from db");
		} catch (RollbackException e) {
			System.out
					.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}

	public void createNewStudent(String id, String firstName, String lastName,
			Integer credits, Date birthDate) {
		Student newStudent = new Student(id, firstName, lastName, credits,
				birthDate);
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(newStudent);
			tx.commit();
		} catch (RollbackException e) {
			System.out
					.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
		System.out.println("Student created " + newStudent);
	}

	public void updateStudent(Student updateInfo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Student willBeUpdated = em.find(Student.class,
					updateInfo.getId());
			System.out.println("Student " + willBeUpdated
					+ " will be updated to " + updateInfo);
			updateProps(willBeUpdated, updateInfo);
			tx.commit();
			System.out.println("Student updated with success");
		} catch (RollbackException e) {
			System.out
					.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}

	public Collection<Student> findAllStudentsWithCreditsMoreThan(
			Integer credits) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em
					.createQuery(
							"SELECT s FROM Student s WHERE s.credits > :credits",
							Student.class).setParameter("credits", credits)
					.getResultList();
			} finally {
			if (em != null)
				em.close();
		}
	}

	public Student findByPrimaryKey(Object primaryKey) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Student.class, primaryKey);
		} finally {
			if (em != null)
				em.close();
		}
	}

	public Collection<Student> findStudentsOfAGivenCourse(String courseName) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em
					.createQuery(
							"SELECT s FROM students s join s.courses c WHERE c.courseName = :Java",
							Student.class)
					.setParameter("courseName", courseName).getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	public Collection<Student> findStudentsOfAGivenFaculty(String facultyName) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();			
			return em
					.createQuery(
							"SELECT s FROM students s join s.faculty f WHERE f.facultyName = :FMI",
							Student.class)
					.setParameter("facultyName", facultyName).getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}

	private void updateProps(Student willBeUpdated, Student updateInfo) {
		willBeUpdated.setBirthDate(updateInfo.getBirthDate());
		willBeUpdated.setCredits(updateInfo.getCredits());
		willBeUpdated.setFirstName(updateInfo.getFirstName());
		willBeUpdated.setLastName(updateInfo.getLastName());
	}

}
