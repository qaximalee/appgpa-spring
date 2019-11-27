package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ihsinformatics.spring.appgpa.dao.SemesterResultsDao;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

public class SemesterResultsDaoImp implements SemesterResultsDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<SemesterResults> getAllSemesterResults() {
		// TODO Auto-generated method stub
		List<SemesterResults> semesterResultss = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			semesterResultss = session.createQuery("from SemesterResults", SemesterResults.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return semesterResultss;
	}

	@Override
	public SemesterResults getSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		SemesterResults semesterResults = new SemesterResults();
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM SemesterResults Std WHERE Std.semesterResultId = :semesterResults_id";
			Query<SemesterResults> query = session.createQuery(hql, SemesterResults.class);
			query.setParameter("semesterResults_id", id);
			semesterResults = query.getSingleResult();// query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return semesterResults;
	}

	@Override
	public boolean deleteSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "DELETE FROM SemesterResults Std " + "WHERE Std.semesterResultId = :semesterResults_id";
			Query query = session.createQuery(hql);
			query.setParameter("semesterResults_id", id);
			int result = query.executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

	@Override
	public boolean update(SemesterResults data) {
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
	public boolean save(SemesterResults data) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.getCurrentSession();
		try {

			data.setcGPA(getCGPA(data.getStudent().getStudentId()));

			// String hql = "FROM SemesterResults Sem_RS WHERE Sem_RS.semester = :semesterID
			// AND Sem_RS.student = :studentID";
			String hql = "FROM SemesterResults Sem_RS WHERE Sem_RS.semester.semesterId = :semesterId AND Sem_RS.student.studentId = :studentId";
			Query<SemesterResults> query = session.createQuery(hql, SemesterResults.class);
			query.setParameter("semesterId", data.getSemester().getSemesterId());
			query.setParameter("studentId", data.getStudent().getStudentId());

			SemesterResults semesterResults = null;
			try {
				semesterResults = query.getSingleResult();
				int semResultsId = semesterResults.getSemesterResultId();
				data.setSemesterResultId(semResultsId);
				update(data);
				saved = true;
			} catch (Exception e) {
				// save the semesterResults objects
				session.save(data);
				saved = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			saved = false;
		}

		return saved;
	}

	private double getCGPA(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM CourseResults Crs_RS WHERE Crs_RS.student.studentId = :studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", studentId);
			List<CourseResults> courseResults = query.list();
			double cGPAPoints = 0.0;
			int totalCreditHours = 0;
			for (CourseResults results : courseResults) {
				cGPAPoints += results.getTotalPoints();
				totalCreditHours += 3;
			}
			double cGPA = cGPAPoints / totalCreditHours;
			return cGPA;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SemesterResultsPOJO> getAllReadableSemesterResults() {

		List<SemesterResultsPOJO> semResults = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			semResults = session.createSQLQuery("CALL getAllSemesterResults()").addEntity(SemesterResultsPOJO.class)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return semResults;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId) {

		List<SemesterResultsPOJO> semResults = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			semResults = session.createSQLQuery("Call getStudentSemResults(:studentID)")
					.setParameter("studentID", studentId).addEntity(SemesterResultsPOJO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return semResults;
	}

	@Override
	public boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId) {
		// TODO Auto-generated method stub

		SemesterResults semesterResults = new SemesterResults();
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM SemesterResults Std WHERE Std.semester.semesterId = :semesterID AND Std.student.studentId = :studentID";
			Query<SemesterResults> query = session.createQuery(hql, SemesterResults.class);
			query.setParameter("semesterID", semesterId);
			query.setParameter("studentID", studentId);
			semesterResults = query.getSingleResult();
			if (semesterResults.getSemester() != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId) {
		List<SemesterResults> semesterResults = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM SemesterResults Std WHERE Std.student.studentId = :student_id";
			semesterResults = session.createQuery(hql, SemesterResults.class).setParameter("student_id", studentId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return semesterResults;
	}
}
