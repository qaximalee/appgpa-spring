package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihsinformatics.spring.appgpa.dao.StudentDAO;
import com.ihsinformatics.spring.appgpa.model.Student;

@Repository
public class StudentDAOImp implements StudentDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			students = session.createQuery("from Student", Student.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public Student getSingle(int id) {
		// TODO Auto-generated method stub
		Student student = new Student();
		// Session session = HibernateUtils.getSessionFactory().openSession()
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Student Std WHERE Std.studentId = :student_id";
			Query<Student> query = session.createQuery(hql);
			query.setParameter("student_id", id);
			student = query.getSingleResult();// query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Transaction transaction = null;
		// Session session = HibernateUtils.getSessionFactory().openSession()
		// (Session session = HibernateUtils.getHibernateSession())
		Session session = sessionFactory.getCurrentSession();
		try {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.save(student);
			// commit transaction
			transaction.commit();

			saved = true;
		} catch (Exception e) {
			saved = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return saved;
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
			updated = true;
		} catch (Exception e) {
			updated = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return updated;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Transaction transaction = null;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			transaction = session.beginTransaction();
			String hql = "DELETE FROM Student Std " + "WHERE Std.studentId = :student_id";
			Query query = session.createQuery(hql);
			query.setParameter("student_id", id);
			int result = query.executeUpdate();
			transaction.commit();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			deleted = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return deleted;
	}
}
