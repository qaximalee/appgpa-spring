package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.StudentStatisticsDao;
import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;
import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.model.TopCgpaPOJO;

public class StudentStatisticsDaoImp implements StudentStatisticsDao {

	Logger logger = Logger.getLogger(StudentStatisticsDaoImp.class.getName());

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will return top CGPA of student. CGPA will be taken from semester
	 * result entity. StudentDto is a Data transfer object for the queried values.
	 * 
	 * @return List<StudentDto>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TopCgpaPOJO> getTopCgpaHolders() {
		// TODO Auto-generated method stub
		logger.info("getTopCgpaHolders is started");
		Session session = sessionFactory.openSession();
		List<TopCgpaPOJO> topCgpaList = new ArrayList<>();
		try {

			// topCgpaList = session.createSQLQuery("SELECT * FROM top_cgpa_holder").list();

			topCgpaList = session.createSQLQuery("CALL getTopCgpa()").addEntity(TopCgpaPOJO.class).list();

			/*
			 * for (TopCgpaPOJO topCgpa : topCgpaList) {
			 * System.out.println("Semester Results ID: " + topCgpa.getSemesterResultId());
			 * System.out.println("Student Name: " + topCgpa.getStudent().getFirstName() +
			 * " " + topCgpa.getStudent().getLastName()); System.out.println("Semester: " +
			 * topCgpa.getSemester().getSemesterNo()); System.out.println("Semester GPA : "
			 * + topCgpa.getSemesterGPA()); System.out.println("C- GPA : " +
			 * topCgpa.getcGpa()); }
			 */
			// create the outer query
			/*
			 * CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaQuery<Object[]> cq
			 * = cb.createQuery(Object[].class); Root<SemesterResults> root =
			 * cq.from(SemesterResults.class); Root<Student> rootStudent =
			 * cq.from(Student.class); Join<SemesterResults, Student> subAuthors =
			 * root.join("student", JoinType.INNER); Predicate predicate1 =
			 * cb.equal(root.get("student"), rootStudent.get("student"));
			 */
			/*
			 * (Select max(semester_id) from semester_results WHERE student_id =
			 * st.student_id)
			 */

			/*
			 * Subquery<Long> sub = cq.subquery(Long.class); Root<Student> subRoot =
			 * sub.from(Student.class); sub.select(cb.max(subRoot.get("semester")));
			 * sub.where(cb.equal(subRoot.get("student"), subAuthors.get("student")));
			 * 
			 * Predicate predicate2 = root.get("semester").in(sub); Predicate finalPredicate
			 * = cb.and(predicate1, predicate2); cq.multiselect(subAuthors);
			 * cq.where(finalPredicate);
			 * 
			 * // check the result of the subquery // //
			 * cq.where(cb.greaterThanOrEqualTo(sub,3L));
			 * 
			 * TypedQuery<Object[]> query = session.createQuery(cq); List<Object[]> results
			 * = query.getResultList(); // for (Object[] obj : results) {
			 * System.out.println("Name: " + obj[0].toString()); }
			 */
			// System.out.println("Name: " + results.get(0) + " " +
			// results.get(0).getStudent().getLastName() + "\nGPA: "
			// + results.get(0).getSemesterGPA() + "\nCGPA: " + results.get(0).getcGPA());

			// ******************************************************************

			// CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			// CriteriaQuery<SemesterResults> query =
			// criteriaBuilder.createQuery(SemesterResults.class);
			// Root<SemesterResults> semesterResultsRoot =
			// query.from(SemesterResults.class);
			//
			// query.select(semesterResultsRoot);
			//
			// Subquery<Long> sub = query.subquery(Long.class);
			// Root<SemesterResults> subRoot = sub.from(SemesterResults.class);
			// sub.select(subRoot.get("semester.semesterId"));

			// query.where(criteriaBuilder.greaterThan(arg0, 2))
			// List<SemesterResults> semResultList = session.createQuery(query).list();
			// System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// for (SemesterResults semResult : semResultList) {
			// System.out.println("Semester Result Id: " + semResult.getSemesterResultId());
			// System.out.println("Student: " + semResult.getStudent().getFirstName() + " "
			// + semResult.getStudent().getLastName());
			// System.out.println("Semester No: " +
			// semResult.getSemester().getSemesterNo());
			// System.out.println("Semester GPA: " + semResult.getSemesterGPA());
			// System.out.println("C-GPA: " + semResult.getcGPA());
			// System.out.println("----------------------------------------");
			// }

		} catch (Exception e) {
			e.printStackTrace();
			topCgpaList = null;
		} finally {
			session.clear();
			session.close();
		}
		logger.info("getTopCgpaHolders is ended");
		return topCgpaList;
	}

	/**
	 * It will generate students' current semester.
	 * 
	 * @return Map<Integer, Integer>
	 */
	@Override
	public Map<Integer, Integer> getTotalStudentsBySemester() {
		// TODO Auto-generated method stub
		logger.info("getTotalStudentsBySemester is started");
		Session session = sessionFactory.openSession();
		Map<Integer, Integer> studentBySemester = new HashMap<>();
		try {

			@SuppressWarnings("unchecked")
			List<Object[]> result = session.createCriteria(SemesterResults.class).setProjection(Projections
					.projectionList().add(Projections.groupProperty("semester")).add(Projections.count("student")))
					.list();

			for (Object[] integer : result) {
				Semester semester = (Semester) integer[0];
				Long students = (Long) integer[1];
				studentBySemester.put(semester.getSemesterNo(), students.intValue());
			}
			return studentBySemester;
		} catch (Exception e) {
			studentBySemester.put(0, 0);
			e.printStackTrace();
		}
		logger.info("getTotalStudentsBySemester is ended");
		return studentBySemester;
	}

	/**
	 * This will return List of CourseDto which will have Highest Marks Courses. By
	 * which we can defined the Students have more interest in these courses. It
	 * will Return top 10 Courses.
	 * 
	 * @return List<CourseDto>
	 */
	@Override
	public List<CourseDto> getTopCoursesByHigherMarks() {
		// TODO Auto-generated method stub
		logger.info("getTopCoursesByHigherMarks is started");
		Session session = sessionFactory.openSession();
		List<CourseDto> courseData = new ArrayList<>();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<CourseResults> root = criteriaQuery.from(CourseResults.class);
			Join<CourseResults, Student> p = root.join("student", JoinType.INNER);

			criteriaQuery.multiselect(criteriaBuilder.max(root.get("percentage")).alias("percent"), p,
					root.get("course"));
			criteriaQuery.groupBy(root.get("course"));
			criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.max(root.get("percentage"))));
			List<Object[]> objArrayList = session.createQuery(criteriaQuery).list();

			for (Object[] obj : objArrayList) {
				Student student = (Student) obj[1];
				Course course = (Course) obj[2];
				double topPercentage = (double) obj[0];
				CourseDto courseDto = new CourseDto(course, topPercentage, student);
				// System.out.println("CourseName: " + course.getName() + "Values: " + obj[0] +
				// "\nValues of Students: "+ student.getFirstName() + " " +
				// student.getLastName());

				courseData.add(courseDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.clear();
			session.close();
		}
		logger.info("getTopCoursesByHigherMarks is ended");
		return courseData;
	}

	/**
	 * It will return the no of courses in a semester.
	 * 
	 * @return List<CoursesBySemesterDto>
	 */
	@Override
	public List<CoursesBySemesterDto> getTotalCoursesBySemester() {
		// TODO Auto-generated method stub
		logger.info("getTotalCoursesBySemester is started");
		Session session = sessionFactory.openSession();
		List<CoursesBySemesterDto> coursesBySemesterData = new ArrayList<>();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Course> root = criteriaQuery.from(Course.class);

			criteriaQuery.multiselect(root.get("semester"), criteriaBuilder.count(root.get("courseId")));
			criteriaQuery.groupBy(root.get("semester"));

			List<Object[]> coursesBySemesterList = session.createQuery(criteriaQuery).list();

			for (Object[] coursesBySemester : coursesBySemesterList) {
				Semester semester = (Semester) coursesBySemester[0];
				long courses = (long) coursesBySemester[1];
				// System.out.println("CourseName: " + coursesBySemester[0] + "\t\t" +
				// coursesBySemester[1]);

				coursesBySemesterData.add(new CoursesBySemesterDto(semester, courses));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.clear();
			session.close();
		}
		logger.info("getTotalCoursesBySemester is ended");
		return coursesBySemesterData;
	}

	/**
	 * Get Students current Semesters
	 * 
	 * @return List<StudentSemesterDto>
	 */
	@Override
	public List<StudentSemesterDto> getStudentsBySemesterCompletion() {
		// TODO Auto-generated method stub
		logger.info("getStudentsBySemesterCompletion is started");
		Session session = sessionFactory.openSession();
		List<StudentSemesterDto> studentCurrentSemester = new ArrayList<>();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<SemesterResults> root = criteriaQuery.from(SemesterResults.class);

			criteriaQuery.multiselect(criteriaBuilder.count(root.get("semester")), root.get("student"));
			criteriaQuery.groupBy(root.get("student"));

			List<Object[]> studentCurrentSemesterList = session.createQuery(criteriaQuery).list();

			for (Object[] stdCurrentSemester : studentCurrentSemesterList) {
				Student student = (Student) stdCurrentSemester[1];
				long courseId = (long) stdCurrentSemester[0];

				studentCurrentSemester.add(new StudentSemesterDto(student, courseId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.clear();
			session.close();
		}
		logger.info("getStudentsBySemesterCompletion is ended");
		return studentCurrentSemester;
	}

}
