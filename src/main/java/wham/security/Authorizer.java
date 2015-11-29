package wham.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import wham.model.User;

public class Authorizer implements SecurityContext {

	private User user = null;
	
	public Authorizer(User user) {
		this.user = user;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public boolean isUserInRole(String role) {
		if(user != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
