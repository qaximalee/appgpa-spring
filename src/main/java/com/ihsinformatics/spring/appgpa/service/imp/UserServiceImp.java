package com.ihsinformatics.spring.appgpa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.UserDao;
import com.ihsinformatics.spring.appgpa.exception.UserAlreadyExist;
import com.ihsinformatics.spring.appgpa.model.User;
import com.ihsinformatics.spring.appgpa.service.UserService;

public class UserServiceImp implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean createUser(User user) throws UserAlreadyExist {
		// TODO Auto-generated method stub
		return userDao.createUser(user);
	}
}
