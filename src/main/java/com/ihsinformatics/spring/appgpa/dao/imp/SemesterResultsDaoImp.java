package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.SemesterDao;
import com.ihsinformatics.spring.appgpa.dao.SemesterResultsDao;
import com.ihsinformatics.spring.appgpa.dao.StudentDao;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

public class SemesterResultsDaoImp implements SemesterResultsDao {

	private SessionFactory sessionFactory;
	private StudentDao studentDao;
	private SemesterDao semesterDao;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setStudentDao(StudentDaoImp studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setSemesterDao(SemesterDaoImp semesterDao) {
		this.semesterDao = semesterDao;
	}

	@Override
	public List<SemesterResults> getAllSemesterResults() {
		// TODO Auto-generated method stub
		List<SemesterResults> semesterResultss = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SemesterResults> criteriaQuery = criteriaBuilder.createQuery(SemesterResults.class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);
			criteriaQuery.select(root);

			semesterResultss = session.createQuery(criteriaQuery).list();
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SemesterResults> criteriaQuery = criteriaBuilder.createQuery(SemesterResults.class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("semesterResultId"), id));

			semesterResults = session.createQuery(criteriaQuery).getSingleResult();
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<SemesterResults> criteriaDelete = criteriaBuilder
					.createCriteriaDelete(SemesterResults.class);
			Root<SemesterResults> root = criteriaDelete.from(SemesterResults.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("semesterResultId"), id));

			int result = session.createQuery(criteriaDelete).executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

	@Override
	public boolean update(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<SemesterResults> criteriaUpdate = criteriaBuilder
					.createCriteriaUpdate(SemesterResults.class);
			Root<SemesterResults> root = criteriaUpdate.from(SemesterResults.class);
			criteriaUpdate.set("semesterResultId", semesterResults.getSemesterResultId());
			criteriaUpdate.set("semester", semesterResults.getSemester());
			criteriaUpdate.set("student", semesterResults.getStudent());
			criteriaUpdate.set("semesterGPA", semesterResults.getSemesterGPA());
			criteriaUpdate.set("cGPA", semesterResults.getcGPA());
			criteriaUpdate
					.where(criteriaBuilder.equal(root.get("semesterResultId"), semesterResults.getSemesterResultId()));
			int result = session.createQuery(criteriaUpdate).executeUpdate();
			if (result > 0)
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

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SemesterResults> criteriaQuery = criteriaBuilder.createQuery(SemesterResults.class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);
			Predicate[] predicates = new Predicate[2];
			predicates[0] = criteriaBuilder.equal(root.get("semester"), data.getSemester());
			predicates[1] = criteriaBuilder.equal(root.get("student"), data.getStudent());
			criteriaQuery.select(root).where(criteriaBuilder.and(predicates[0], predicates[1]));

			SemesterResults semesterResults = null;
			try {
				semesterResults = session.createQuery(criteriaQuery).getSingleResult();
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

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CourseResults> criteriaQuery = criteriaBuilder.createQuery(CourseResults.class);
			Root<CourseResults> root = criteriaQuery.from(CourseResults.class);
			criteriaQuery.select(root)
					.where(criteriaBuilder.equal(root.get("student"), studentDao.getStudentById(studentId)));

			List<CourseResults> courseResults = session.createQuery(criteriaQuery).list();
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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SemesterResults> criteriaQuery = criteriaBuilder.createQuery(SemesterResults.class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);
			Predicate[] predicates = new Predicate[2];
			predicates[0] = criteriaBuilder.equal(root.get("semester"), semesterDao.getSemesterById(semesterId));
			predicates[1] = criteriaBuilder.equal(root.get("student"), studentDao.getStudentById(studentId));
			criteriaQuery.select(root).where(criteriaBuilder.and(predicates[0], predicates[1]));

			semesterResults = session.createQuery(criteriaQuery).getSingleResult();

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
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SemesterResults> criteriaQuery = criteriaBuilder.createQuery(SemesterResults.class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);
			criteriaQuery.select(root)
					.where(criteriaBuilder.equal(root.get("student"), studentDao.getStudentById(studentId)));

			semesterResults = session.createQuery(criteriaQuery).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return semesterResults;
	}
}
