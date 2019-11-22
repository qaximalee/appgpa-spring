package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		try (Session session = sessionFactory.getCurrentSession()) {
			courses = session.createQuery("from Course", Course.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public Course getSingle(int id) {
		// TODO Auto-generated method stub
		Course course = new Course();
		try (Session session = sessionFactory.getCurrentSession()) {
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
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the course objects
			session.save(course);
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
	public boolean update(Course course) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			transaction = session.beginTransaction();
			session.update(course);
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
			String hql = "DELETE FROM Course Crs " + "WHERE Crs.courseId = :course_id";
			Query query = session.createQuery(hql);
			query.setParameter("course_id", id);
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

	@Override
	public List<Course> getCoursesBySemester(int semesterId) {
		List<Course> courses = new ArrayList<>();
		try (Session session = sessionFactory.getCurrentSession()) {
			courses = session.createQuery("from Course Cr WHERE Cr.semester.semesterId = :semesterID", Course.class)
					.setParameter("semesterID", semesterId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
}
