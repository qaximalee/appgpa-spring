package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
			lookups = session.createQuery("from Lookup", Lookup.class).list();
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
			String hql = "FROM Lookup Lkup WHERE Lkup.lookupId = :lookup_id";
			Query<Lookup> query = session.createQuery(hql);
			query.setParameter("lookup_id", id);
			lookup = query.getSingleResult();// query.list();
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
			session.update(lookup);
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
			String hql = "DELETE FROM Lookup Lkup " + "WHERE Lkup.lookupId = :lookup_id";
			Query query = session.createQuery(hql);
			query.setParameter("lookup_id", id);
			int result = query.executeUpdate();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

}
