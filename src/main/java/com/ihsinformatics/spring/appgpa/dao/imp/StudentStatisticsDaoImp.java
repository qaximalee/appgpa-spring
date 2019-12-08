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
import com.ihsinformatics.spring.appgpa.metamodel.CourseResults_;
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

		Session session = sessionFactory.openSession();
		List<TopCgpaPOJO> topCgpaList = new ArrayList<>();
		try {

			topCgpaList = session.createSQLQuery("CALL getTopCgpa()").addEntity(TopCgpaPOJO.class).list();

			for (TopCgpaPOJO topCgpa : topCgpaList) {
				System.out.println("Semester Results ID: " + topCgpa.getSemesterResultId());
				System.out.println("Student Name: " + topCgpa.getStudent().getFirstName() + " "
						+ topCgpa.getStudent().getLastName());
				System.out.println("Semester: " + topCgpa.getSemester().getSemesterNo());
				System.out.println("Semester GPA : " + topCgpa.getSemesterGPA());
				System.out.println("C- GPA : " + topCgpa.getcGpa());
			}
			// create the outer query
			// CriteriaBuilder cb = session.getCriteriaBuilder();
			// CriteriaQuery<SemesterResults> cq = cb.createQuery(SemesterResults.class);
			// Root<SemesterResults> root = cq.from(SemesterResults.class);
			//
			// // count books written by an author
			// Subquery<Long> sub = cq.subquery(Long.class);
			// Root<Student> subRoot = sub.from(Student.class);
			//
			// Join<Student, SemesterResults> subAuthors =
			// subRoot.join(Student_.STUDENT_ID);
			// sub.select(cb.count(subRoot.get("studentId")));
			// sub.where(cb.equal(root.get(SemesterResults_.semesterResultId),
			// subAuthors.get(SemesterResults_.semesterResultId)));
			//
			// // check the result of the subquery
			// cq.where(cb.greaterThanOrEqualTo(sub, 3L));
			//
			// TypedQuery<SemesterResults> query = session.createQuery(cq);
			// List<SemesterResults> authors = query.getResultList();
			//
			// System.out.println(authors.get(0));

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
		Session session = sessionFactory.openSession();
		List<CourseDto> courseData = new ArrayList<>();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<CourseResults> root = criteriaQuery.from(CourseResults.class);
			Join<CourseResults, Student> p = root.join(CourseResults_.STUDENT, JoinType.INNER);

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

		return studentCurrentSemester;
	}

}