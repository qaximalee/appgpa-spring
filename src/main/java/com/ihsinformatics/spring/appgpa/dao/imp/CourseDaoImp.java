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

import com.ihsinformatics.spring.appgpa.dao.CourseDao;
import com.ihsinformatics.spring.appgpa.dao.SemesterDao;
import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Semester;

public class CourseDaoImp implements CourseDao {

	private SessionFactory sessionFactory;
	private SemesterDao semesterDao;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setSemesterDao(SemesterDaoImp semesterDao) {
		this.semesterDao = semesterDao;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
			Root<Course> root = criteriaQuery.from(Course.class);
			criteriaQuery.select(root);

			courses = session.createQuery(criteriaQuery).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		Course course = new Course();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
			Root<Course> root = criteriaQuery.from(Course.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("courseId"), id));
			course = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public boolean save(Course course) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			// save the course objects
			session.save(course);

			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public boolean update(Course course) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Course> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Course.class);
			Root<Course> root = criteriaUpdate.from(Course.class);
			criteriaUpdate.set("courseId", course.getCourseId());
			criteriaUpdate.set("courseCode", course.getCourseCode());
			criteriaUpdate.set("name", course.getName());
			criteriaUpdate.set("semester", course.getSemester());
			criteriaUpdate.where(criteriaBuilder.equal(root.get("courseId"), course.getCourseId()));
			int result = session.createQuery(criteriaUpdate).executeUpdate();
			if (result > 0)
				updated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean deleteCourseById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Course> criteriaDelete = criteriaBuilder.createCriteriaDelete(Course.class);
			Root<Course> root = criteriaDelete.from(Course.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("courseId"), id));

			int result = session.createQuery(criteriaDelete).executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}

	@Override
	public List<Course> getCoursesBySemesterId(int semesterId) {
		List<Course> courses = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
			Root<Course> root = criteriaQuery.from(Course.class);

			Semester semester = semesterDao.getSemesterById(semesterId);

			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("semester"), semester));
			courses = session.createQuery(criteriaQuery).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
}
