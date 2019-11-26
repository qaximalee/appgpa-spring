package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public List<Semester> getAllSemesters() {
		// TODO Auto-generated method stub
		List<Semester> semesters = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			semesters = session.createQuery("from Semester", Semester.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return semesters;
	}

	@Override
	public Semester getSemesterById(int id) {
		// TODO Auto-generated method stub
		Semester semester = new Semester();
		Session session = sessionFactory.getCurrentSession();
		try {
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
		Session session = sessionFactory.getCurrentSession();
		try {
			// save the semester objects
			session.save(semester);
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public boolean update(Semester semester) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(semester);
			updated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean deleteSemesterById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "DELETE FROM Semester Sem " + "WHERE Sem.semesterId = :semester_id";
			Query query = session.createQuery(hql);
			query.setParameter("semester_id", id);
			int result = query.executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}
}