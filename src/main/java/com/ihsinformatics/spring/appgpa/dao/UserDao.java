package com.ihsinformatics.spring.appgpa.dao;

import com.ihsinformatics.spring.appgpa.exception.UserAlreadyExist;
import com.ihsinformatics.spring.appgpa.model.User;

public interface UserDao {

	boolean createUser(User user) throws UserAlreadyExist;
}
