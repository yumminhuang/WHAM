package wham;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import wham.model.User;

public class ResourceBase {

	@Context
	private SecurityContext context;
	
	protected User getCurrentUser () {
		return (User) context.getUserPrincipal();
	}
}
