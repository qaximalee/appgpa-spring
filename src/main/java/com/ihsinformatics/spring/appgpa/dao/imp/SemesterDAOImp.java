package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.SemesterDAO;
import com.ihsinformatics.spring.appgpa.model.Semester;

public class SemesterDAOImp implements SemesterDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Semester> getAll() {
		// TODO Auto-generated method stub
		List<Semester> semesters = new ArrayList<>();
		try (Session session = sessionFactory.getCurrentSession()) {
			semesters = session.createQuery("from Semester", Semester.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return semesters;
	}

	@Override
	public Semester getSingle(int id) {
		// TODO Auto-generated method stub
		Semester semester = new Semester();
		try (Session session = sessionFactory.getCurrentSession()) {
			String hql = "FROM Semester Sem WHERE Sem.semesterId = :semester_id";
			Query<Semester> query = session.createQuery(hql, Semester.class);
			query.setParameter("semester_id", id);
			semester = query.getSingleResult();// query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return semester;
	}

	@Override
	public boolean save(Semester semester) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the semester objects
			session.save(semester);
			// commit transaction
			transaction.commit();

			saved = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public boolean update(Semester semester) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			transaction = session.beginTransaction();
			session.update(semester);
			transaction.commit();
			updated = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			transaction = session.beginTransaction();
			String hql = "DELETE FROM Semester Sem " + "WHERE Sem.semesterId = :semester_id";
			Query query = session.createQuery(hql);
			query.setParameter("semester_id", id);
			int result = query.executeUpdate();
			transaction.commit();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return deleted;
	}

}
