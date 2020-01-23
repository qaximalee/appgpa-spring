package com.ihsinformatics.spring.appgpa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.exception.UserAlreadyExist;
import com.ihsinformatics.spring.appgpa.model.User;
import com.ihsinformatics.spring.appgpa.service.UserService;

@Controller
public class CustomController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/welcome")
	public ModelAndView welcomeUser() {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/register-view")
	public ModelAndView registerView() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registerUser(@RequestParam("username") String username, @RequestParam("password") String password)
			throws UserAlreadyExist {

		User user = new User(0L, username, password);

		if (userService.createUser(user)) {
			System.out.println("User Created Successfully");
		}
	}

}
