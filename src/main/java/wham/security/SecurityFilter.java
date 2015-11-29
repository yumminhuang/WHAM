package wham.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import wham.model.User;
import wham.operation.UserOperation;

@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTH_TOKEN_PREFIX = "Bearer ";
	@Override
	public void filter(ContainerRequestContext requestCtx) throws IOException {
		User user = authenticate(requestCtx.getHeaders());
		requestCtx.setSecurityContext(new Authorizer(user));
	}
	
	private User authenticate (MultivaluedMap<String, String> headers) throws WebApplicationException {
		User user = null;
		
		String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
		
		if(token == null || token.isEmpty()) {
			//may be a request public end point
			System.err.println("No Token Found");
		}
		else if (!token.startsWith(AUTH_TOKEN_PREFIX)) {
			System.err.println("Unrecognized Auth Header Found=" + token);
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		else {
			String tokenValue = token.substring(AUTH_TOKEN_PREFIX.length());
			byte[] decodedValue = DatatypeConverter.parseBase64Binary(tokenValue);
			try {
				String usernamePassword = new String (decodedValue, "UTF-8");
				String [] tokenizer = usernamePassword.split(":");
				if(tokenizer.length == 2) {
					UserOperation operation = new UserOperation();
					user = operation.getUser(tokenizer[0], tokenizer[1]);
					if(user == null) {
						System.err.println("User Unauthorized");
						throw new WebApplicationException(Status.UNAUTHORIZED);
					}
				}
				else {
					throw new WebApplicationException(Status.UNAUTHORIZED);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		return user;
	}
}
