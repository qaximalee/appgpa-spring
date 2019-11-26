package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.CourseResultsDAO;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public class CourseResultsDAOImp implements CourseResultsDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<CourseResults> getAll() {
		// TODO Auto-generated method stub
		List<CourseResults> courseResultss = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			courseResultss = session.createQuery("from CourseResults", CourseResults.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseResultss;
	}

	@Override
	public CourseResults getSingle(int id) {
		// TODO Auto-generated method stub
		CourseResults courseResults = new CourseResults();
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM CourseResults Crs_Rs WHERE Crs_Rs.courseResultId = :courseResult_id";
			Query<CourseResults> query = session.createQuery(hql, CourseResults.class);
			query.setParameter("courseResult_id", id);
			courseResults = query.getSingleResult();// query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseResults;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "DELETE FROM CourseResults Crs_Rs " + "WHERE Crs_Rs.courseResultId = :courseResult_id";
			Query query = session.createQuery(hql);
			query.setParameter("courseResult_id", id);
			int result = query.executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

	@Override
	public boolean update(CourseResults data) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(data);
			updated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean save(CourseResults data) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(data);
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public List<CourseResults> getAllCourseResultsBySemester(int semesterId, int studentId) {
		List<CourseResults> courseResults = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createSQLQuery("CALL getCourseResults(:semesterId, :studentId)")
					.addEntity(CourseResults.class);
			query.setParameter("semesterId", semesterId);
			query.setParameter("studentId", studentId);
			courseResults = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseResults;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CourseResultsPOJO> getAllReadableResults() {
		List<CourseResultsPOJO> courseResults = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			courseResults = session.createSQLQuery("CALL getAllCourseResults()").addEntity(CourseResultsPOJO.class)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseResults;
	}
}
