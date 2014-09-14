package geeky.camp.jpa.dao;

import geeky.camp.jpa.entities.Faculty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

public class FacultyDAO {
	protected EntityManagerFactory emf;

	public FacultyDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void createNewFaculty(String id, String facultyName) {
		Faculty newFaculty = new Faculty(id, facultyName);
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(newFaculty);
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
		System.out.println("Faculty created " + newFaculty);
	}

	public void deleteFaculty(Object primaryKey) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Faculty willBeDeleted = em.find(Faculty.class, primaryKey);
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

	public void updateFaculty(Faculty updateInfo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Faculty willBeUpdated = em.find(Faculty.class,
					updateInfo.getId());
			System.out.println("Faculty " + willBeUpdated
					+ " will be updated to " + updateInfo);
			updateProps(willBeUpdated, updateInfo);
			tx.commit();
			System.out.println("Faculty updated with success");
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

	public Faculty findByPrimaryKey(Object primaryKey) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Faculty.class, primaryKey);
		} finally {
			if (em != null)
				em.close();
		}
	}

	private void updateProps(Faculty willBeUpdated, Faculty updateInfo) {
		willBeUpdated.setFacultyName(updateInfo.getFacultyName());

	}

}
