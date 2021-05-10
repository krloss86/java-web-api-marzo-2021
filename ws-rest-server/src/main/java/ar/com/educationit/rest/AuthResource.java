package ar.com.educationit.rest;

import java.util.Base64;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.UserService;
import ar.com.educationit.service.impl.UserServiceImpl;

@Path("auth")
public class AuthResource {

	private UserService userService = new UserServiceImpl();
	
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response login(
			@QueryParam("username") String username,
			@QueryParam("password") String password
			) {
		
		ResponseBuilder response = Response.ok();
		
		try {
			User user = this.userService.getUserByUsername(username);
			
			if(user != null && user.getPassword().equals(password)) {
				String basic = user.getUsername()+":"+user.getPassword();
				byte[] basicBytes = Base64.getEncoder().encode(basic.getBytes());
				String basicEncode = new String(basicBytes);
				response.header("Access-Token", basicEncode);
			}else {
				response = Response.status(Status.UNAUTHORIZED);
			}
		} catch (ServiceException e) {
			response = Response.status(Status.UNAUTHORIZED);
		}
		return response.build();
	}
}
