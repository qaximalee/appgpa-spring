package com.ihsinformatics.spring.appgpa.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Semester;

@Controller
@RequestMapping("/practice")
public class CriteriaPracticeController {

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(method = RequestMethod.GET)
	public void useCriteria() {
		Session session = sessionFactory.openSession();
		// Transaction t = session.beginTransaction();
		// Student std = new Student(0, "EP-1423432", "Qasim", "Ali");
		// session.save(std);
		// t.commit();
		//
		// System.out.println("DONE EXECUTION:");

		// CriteriaBuilder cb = session.getCriteriaBuilder();
		// CriteriaQuery<Student> q = cb.createQuery(Student.class);
		// Root<Student> c = q.from(Student.class);
		// q.select(c.get("firstName")).distinct(true);
		// List<Student> stds = session.createQuery(q).getResultList();
		//
		// stds.forEach((v) -> System.out.println("NAME: " + v.getFirstName()));

		// CriteriaBuilder cb = session.getCriteriaBuilder();
		// CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		// Root<Course> c = q.from(Course.class);
		// q.select(cb.array(c.get("name"), c.get("semester").get("semesterNo")));
		// List<Object[]> results = session.createQuery(q).getResultList();
		//
		// for (Object[] obj : results)
		// System.out.println("Course Name: " + obj[0] + ", Semester: " + obj[1]);

		// CriteriaBuilder cb = session.getCriteriaBuilder();
		// CriteriaQuery<Tuple> q = cb.createTupleQuery();
		// Root<Course> c = q.from(Course.class);
		// q.select(cb.tuple(c.get("name"), c.get("semester").get("semesterNo")));
		// List<Tuple> results = session.createQuery(q).getResultList();
		//
		// for (Tuple t : results) {
		// System.out.println("Country: " + t.get(0) + ", Capital: " + t.get(1));
		// }

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Course> q = cb.createQuery(Course.class);
		Root<Course> c = q.from(Course.class);
		Join<Course, Semester> p = c.join("semester", JoinType.LEFT);
		q.multiselect(c, p.get("semesterNo"));

		session.createQuery(q).getResultList()
				.forEach((v) -> System.out.println("NAME: " + v.getName() + " " + v.getSemester().getSemesterNo()));

	}
}
