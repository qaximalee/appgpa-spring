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

	/**
	 * This function will return all the Courses.
	 * 
	 * @return List<Course> It will contains all courses data
	 */
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

	/**
	 * This function will return a Course by course id
	 * 
	 * @param courseId
	 *            Id of a course
	 * @return Course returns the course
	 */
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

	/**
	 * This method will save the course into database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is added successfully or not.
	 */
	@Override
	public boolean save(Course course) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.openSession();
		try {
			// save the course objects
			session.save(course);
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return saved;
	}

	/**
	 * This method will update the course in the database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is updated successfully or not.
	 */
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

	/**
	 * This method will delete the course in the database.
	 * 
	 * @param courseId
	 *            id of a course given to this method.
	 * @return boolean if the course is deleted successfully or not.
	 */
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

	/**
	 * This method will return the total courses by per semester.
	 * 
	 * @param semesterId
	 *            provide semester id for which the courses are return.
	 * @return List<Course> list of courses against the semester id.
	 */
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
