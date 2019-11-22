package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.LookupDAO;
import com.ihsinformatics.spring.appgpa.model.Lookup;

public class LookupDAOImp implements LookupDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Lookup> getAll() {
		// TODO Auto-generated method stub
		List<Lookup> lookups = new ArrayList<>();
		try (Session session = sessionFactory.getCurrentSession()) {
			lookups = session.createQuery("from Lookup", Lookup.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lookups;
	}

	@Override
	public Lookup getSingle(int id) {
		// TODO Auto-generated method stub
		Lookup lookup = new Lookup();
		try (Session session = sessionFactory.getCurrentSession()) {
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
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the lookup objects
			session.save(lookup);
			// commit transaction
			transaction.commit();

			saved = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return saved;
	}

	@Override
	public boolean update(Lookup lookup) {
		// TODO Auto-generated method stub
		boolean updated = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			transaction = session.beginTransaction();
			session.update(lookup);
			transaction.commit();
			updated = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		Transaction transaction = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			transaction = session.beginTransaction();
			String hql = "DELETE FROM Lookup Lkup " + "WHERE Lkup.lookupId = :lookup_id";
			Query query = session.createQuery(hql);
			query.setParameter("lookup_id", id);
			int result = query.executeUpdate();
			transaction.commit();
			if (result == 1)
				deleted = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return deleted;
	}

}
