package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.CourseDAO;
import com.ihsinformatics.spring.appgpa.model.Course;

public class CourseDAOImp implements CourseDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			courses = session.createQuery("from Course", Course.class).list();
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
			String hql = "FROM Course Crs WHERE Crs.courseId = :course_id";
			Query<Course> query = session.createQuery(hql, Course.class);
			query.setParameter("course_id", id);
			course = query.getSingleResult();// query.list();
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
			session.update(course);
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
			String hql = "DELETE FROM Course Crs " + "WHERE Crs.courseId = :course_id";
			Query query = session.createQuery(hql);
			query.setParameter("course_id", id);
			int result = query.executeUpdate();
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
			courses = session.createQuery("from Course Cr WHERE Cr.semester.semesterId = :semesterID", Course.class)
					.setParameter("semesterID", semesterId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
}
