package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihsinformatics.spring.appgpa.dao.StudentDao;
import com.ihsinformatics.spring.appgpa.model.Student;

@Repository
public class StudentDaoImp implements StudentDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			criteriaQuery.select(root);

			students = session.createQuery(criteriaQuery).list();// "from Student", Student.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		Student student = new Student();
		Session session = sessionFactory.getCurrentSession();
		try {
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
		Session session = sessionFactory.getCurrentSession();
		try {
			// save the student objects
			int a = (int) session.save(student);
			if (a > 0)
				saved = true;
			else
				saved = false;
			session.flush();
		} catch (Exception e) {
			saved = false;
			e.printStackTrace();
		}
		return saved;
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(student);
			updated = true;
		} catch (Exception e) {
			updated = false;
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			String hql = "DELETE FROM Student Std " + "WHERE Std.studentId = :student_id";
			Query query = session.createQuery(hql);
			query.setParameter("student_id", id);
			int result = query.executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			deleted = false;
			e.printStackTrace();
		}

		return deleted;
	}
}
