package com.ihsinformatics.spring.appgpa.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.ihsinformatics.spring.appgpa.model.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (authorizedUser(userName, password)) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(() -> {
				return "AUTH_USER";
			});
			Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
			System.out.println(auth.getAuthorities());
			return auth;
		} else {
			throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
		}
	}

	private boolean authorizedUser(String userName, String password) {
		System.out.println("username is :" + userName + " and password is " + password);
		List<User> users = new ArrayList<>();
		users.add(new User(1L, "Qasim", "qaximalee"));
		users.add(new User(2L, "Baltistani", "baltistani"));
		users.add(new User(3L, "Afghani", "afghani"));
		// String bCrypt = new BCryptPasswordEncoder().encode("afghani");
		// System.out.println("BCRYPT PASS: " + bCrypt);
		if (users.stream()
				.anyMatch(user -> user.getUsername().equals(userName) && user.getPassword().equals(password))) {
			System.out.println("Credentials are correct.");
			return true;
		}
		return false;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}