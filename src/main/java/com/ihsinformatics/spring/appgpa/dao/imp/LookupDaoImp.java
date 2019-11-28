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
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.LookupDao;
import com.ihsinformatics.spring.appgpa.model.Lookup;

public class LookupDaoImp implements LookupDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Lookup> getAllLookup() {
		// TODO Auto-generated method stub
		List<Lookup> lookups = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Lookup> criteriaQuery = criteriaBuilder.createQuery(Lookup.class);
			Root<Lookup> root = criteriaQuery.from(Lookup.class);
			criteriaQuery.select(root);

			lookups = session.createQuery(criteriaQuery).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lookups;
	}

	@Override
	public Lookup getLookupById(int id) {
		// TODO Auto-generated method stub
		Lookup lookup = new Lookup();
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Lookup> criteriaQuery = criteriaBuilder.createQuery(Lookup.class);
			Root<Lookup> root = criteriaQuery.from(Lookup.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("lookupId"), id));

			lookup = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lookup;
	}

	@Override
	public boolean save(Lookup lookup) {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			// save the lookup objects
			session.save(lookup);
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public boolean update(Lookup lookup) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Lookup> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Lookup.class);
			Root<Lookup> root = criteriaUpdate.from(Lookup.class);
			criteriaUpdate.set("lookupId", lookup.getLookupId());
			criteriaUpdate.set("startPercentage", lookup.getStartParcentage());
			criteriaUpdate.set("endPercentage", lookup.getEndPercentage());
			criteriaUpdate.set("gpa", lookup.getGpa());
			criteriaUpdate.set("grade", lookup.getGrade());
			criteriaUpdate.where(criteriaBuilder.equal(root.get("lookupId"), lookup.getLookupId()));
			int result = session.createQuery(criteriaUpdate).executeUpdate();
			if (result > 0)
				updated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean deleteLookupById(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Lookup> criteriaDelete = criteriaBuilder.createCriteriaDelete(Lookup.class);
			Root<Lookup> root = criteriaDelete.from(Lookup.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("lookupId"), id));

			int result = session.createQuery(criteriaDelete).executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

}
