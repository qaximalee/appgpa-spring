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
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;

public class SemesterResultsDaoImp implements SemesterResultsDao {

	private SessionFactory sessionFactory;
	private StudentDao studentDao;
	private SemesterDao semesterDao;

	@Autowired
	private CourseResultsService courseResultsService;

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

	/**
	 * This method will get all semester results in the database.
	 * 
	 * @return List<SemesterResults>
	 */
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

	/**
	 * This method will get Semester Results by semester results id.
	 * 
	 * @param int
	 *            id
	 * @return SemesterResults
	 */
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

	/**
	 * This method will delete Semester Results.
	 * 
	 * @param int
	 *            id
	 * @return boolean
	 */
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

	/**
	 * This method will update the Semester Results
	 * 
	 * @param SemesterResults
	 *            semesterResults
	 * @return boolean
	 */
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

	/**
	 * This method will save the Semester Results.
	 * 
	 * @param SemesterResults
	 *            data
	 * @return boolean
	 */
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

	/**
	 * This method will calculate the CGPA of a particular student. It is called
	 * when the user generate semester result.
	 * 
	 * @param int
	 *            studentId
	 * @return double
	 */
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

	/**
	 * This method gets all data of semester results in a readable form which is
	 * showed in the view all semester results
	 * 
	 * @return List<SemesterResultsPOJO>
	 */
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

	/**
	 * This method will be called when a new semester result is added and the
	 * student has any previous semester results.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResultsPOJO>
	 */
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

	/**
	 * This method will be checking the semester result added by the student is the
	 * same as previous semester's result.
	 * 
	 * @param int
	 *            studentId, int semesterId
	 * @return boolean
	 */
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

	/**
	 * This method will get all semester results by student id and return in non
	 * readable form means it will return in SemesterResults Entity.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResults>
	 */
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

	/**
	 * It will calculate the results for a student and return it to the calling
	 * controller(s).
	 * 
	 * @param semesterId
	 * @param studentId
	 * @return'
	 */
	@Override
	public SemesterResults calculateSemesterResults(int studentId, int semesterId) {
		Student student = studentDao.getStudentById(studentId);
		Semester semester = semesterDao.getSemesterById(semesterId);

		List<CourseResults> listOfCourseResults = courseResultsService
				.getCourseResultsByStudentAndSemesterId(semesterId, studentId);

		/*
		 * Semester GPA can be get by below formula gpa = totalPoints / gradableCredit
		 * where, totalPoints = gpa * credit hours gradableCredit = total credit hours
		 */
		double totalPoints = 0.0;

		for (CourseResults courseResults : listOfCourseResults) {
			totalPoints += courseResults.getTotalPoints();
		}

		double gradableCredit = 3 * listOfCourseResults.size();
		double semesterGPA = totalPoints / gradableCredit;

		return new SemesterResults(0, semester, student, semesterGPA, semesterGPA);
	}
}
