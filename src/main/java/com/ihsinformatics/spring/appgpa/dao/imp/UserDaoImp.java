package com.ihsinformatics.spring.appgpa.dao.imp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.UserDao;
import com.ihsinformatics.spring.appgpa.exception.UserAlreadyExist;
import com.ihsinformatics.spring.appgpa.model.User;

public class UserDaoImp implements UserDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createUser(User user) throws UserAlreadyExist {
		// TODO Auto-generated method stub
		boolean saved = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			if (isUserExist(user.getUsername()))
				throw new UserAlreadyExist("Username: " + user.getUsername() + " is already Exists");
			int a = (int) session.save(user);
			if (a > 0)
				saved = true;
			else
				saved = false;
			session.flush();
		} catch (Exception e) {
			saved = false;
			e.printStackTrace();
		}
		return saved;
	}

	private boolean isUserExist(String username) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));

			user = session.createQuery(criteriaQuery).getSingleResult();
			if (user == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
