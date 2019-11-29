package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.SemesterDao;
import com.ihsinformatics.spring.appgpa.model.Semester;

public class SemesterDaoImp implements SemesterDao {

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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Semester> criteriaQuery = criteriaBuilder.createQuery(Semester.class);
			Root<Semester> root = criteriaQuery.from(Semester.class);
			criteriaQuery.select(root);
			semesters = session.createQuery(criteriaQuery).list();
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Semester> criteriaQuery = criteriaBuilder.createQuery(Semester.class);
			Root<Semester> root = criteriaQuery.from(Semester.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("semesterId"), id));
			semester = session.createQuery(criteriaQuery).getSingleResult();
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Semester> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Semester.class);
			Root<Semester> root = criteriaUpdate.from(Semester.class);
			criteriaUpdate.set("semesterId", semester.getSemesterId());
			criteriaUpdate.set("semesterNo", semester.getSemesterNo());
			criteriaUpdate.where(criteriaBuilder.equal(root.get("semesterId"), semester.getSemesterId()));
			int result = session.createQuery(criteriaUpdate).executeUpdate();
			if (result > 0)
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Semester> criteriaDelete = criteriaBuilder.createCriteriaDelete(Semester.class);
			Root<Semester> root = criteriaDelete.from(Semester.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("semesterId"), id));

			int result = session.createQuery(criteriaDelete).executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}
}