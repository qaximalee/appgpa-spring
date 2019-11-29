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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.CourseResultsDao;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public class CourseResultsDaoImp implements CourseResultsDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<CourseResults> getAllCourseResults() {
		// TODO Auto-generated method stub
		List<CourseResults> courseResultss = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CourseResults> criteriaQuery = criteriaBuilder.createQuery(CourseResults.class);
			Root<CourseResults> root = criteriaQuery.from(CourseResults.class);
			criteriaQuery.select(root);

			courseResultss = session.createQuery(criteriaQuery).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseResultss;
	}

	@Override
	public CourseResults getCourseResultsById(int id) {
		// TODO Auto-generated method stub
		CourseResults courseResults = new CourseResults();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CourseResults> criteriaQuery = criteriaBuilder.createQuery(CourseResults.class);
			Root<CourseResults> root = criteriaQuery.from(CourseResults.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("courseResultId"), id));

			courseResults = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseResults;
	}

	@Override
	public boolean deleteCourseResultsById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<CourseResults> criteriaDelete = criteriaBuilder.createCriteriaDelete(CourseResults.class);
			Root<CourseResults> root = criteriaDelete.from(CourseResults.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("courseResultId"), id));

			int result = session.createQuery(criteriaDelete).executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

	@Override
	public boolean update(CourseResults courseResults) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<CourseResults> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(CourseResults.class);
			Root<CourseResults> root = criteriaUpdate.from(CourseResults.class);
			criteriaUpdate.set("courseResultId", courseResults.getCourseResultId());
			criteriaUpdate.set("course", courseResults.getCourse());
			criteriaUpdate.set("student", courseResults.getStudent());
			criteriaUpdate.set("gpa", courseResults.getGpa());
			criteriaUpdate.set("grade", courseResults.getGrade());
			criteriaUpdate.set("percentage", courseResults.getPercentage());
			criteriaUpdate.set("totalPoints", courseResults.getTotalPoints());

			criteriaUpdate.where(criteriaBuilder.equal(root.get("courseResultId"), courseResults.getCourseResultId()));
			int result = session.createQuery(criteriaUpdate).executeUpdate();
			if (result > 0)
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
	public List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId) {
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
	public List<CourseResultsPOJO> getAllReadableCourseResults() {
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
