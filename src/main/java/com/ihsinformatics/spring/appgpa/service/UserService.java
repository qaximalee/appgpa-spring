package com.ihsinformatics.spring.appgpa.service;

import com.ihsinformatics.spring.appgpa.exception.UserAlreadyExist;
import com.ihsinformatics.spring.appgpa.model.User;

public interface UserService {
	boolean createUser(User user) throws UserAlreadyExist;
}
