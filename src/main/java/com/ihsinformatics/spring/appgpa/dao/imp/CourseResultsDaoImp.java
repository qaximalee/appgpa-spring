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
import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;
import com.ihsinformatics.spring.appgpa.model.Lookup;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.LookupService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

public class CourseResultsDaoImp implements CourseResultsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private LookupService lookupService;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will get all course results in the database.
	 * 
	 * @return List<CourseResults>
	 */
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

	/**
	 * This method will get Course Results by course results id.
	 * 
	 * @param int
	 *            id
	 * @return SemesterResults
	 */
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

	/**
	 * This method will delete an entity from database by providing the id of course
	 * results.
	 * 
	 * @param id
	 *            course results id.
	 * @return boolean whether deleted or not.
	 */
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

	/**
	 * This method will update an existing entity by providing the updated entity.
	 * 
	 * @param courseResults
	 *            updated course result
	 * @return boolean whether if the entity updated or not.
	 */
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

	/**
	 * This method will save entity of CourseResults and return whether the entity
	 * is saved or not.
	 * 
	 * @param courseResults
	 *            this entity will be saved in database.
	 * @return boolean if the entity is saved or not.
	 */
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

	/**
	 * This method will give the Course Results of a student by it's student id and
	 * semester id.
	 * 
	 * @param semesterId
	 * @param studentId
	 * 
	 * @return List<CourseResults> list of course results against above parameters
	 */
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

	/**
	 * This will return the all Course results in a readable entity for showing on
	 * web.
	 * 
	 * @return List<CourseResultsPOJO>
	 */
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

	/**
	 * This method will calculate the CourseResult for saving the course results in
	 * database.
	 * 
	 * @param studentId
	 * @param semesterId
	 * @param courseId
	 * @param percentage
	 *            It is the percentage obtain in the course
	 * 
	 * @return CourseResults
	 */
	@Override
	public CourseResults calculateCourseResult(int studentId, int semesterId, int courseId, double percentage) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);

		StringBuilder grade = new StringBuilder();
		double gpa = 0.0;

		List<Lookup> listOfLookup = lookupService.getAllLookup();
		for (Lookup lookup : listOfLookup) {
			if (percentage >= lookup.getStartParcentage() && percentage <= lookup.getEndPercentage()) {
				gpa = lookup.getGpa();
				grade.append(lookup.getGrade());
			}
		}

		// GPA Multiply by 3 (ie 3 is credit hours)
		double totalPoints = gpa * 3;
		CourseResults courseResults = new CourseResults(0, course, student, percentage, gpa, grade.toString(),
				totalPoints);
		grade.replace(0, grade.length(), "");

		return courseResults;
	}
}
