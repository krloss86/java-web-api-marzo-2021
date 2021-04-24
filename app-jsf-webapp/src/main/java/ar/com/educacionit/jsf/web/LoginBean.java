package ar.com.educacionit.jsf.web;

import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.UserService;
import ar.com.educationit.service.impl.UserServiceImpl;

@Named
@RequestScoped
public class LoginBean {

	private String username;
	private String password;
	private String error;
		
	private UserService userService = new UserServiceImpl();
	
	@Inject
	private UsuarioBean usuarioBean;
	
	public String login() {
		
		PagesEnums target = PagesEnums.LOGIN;
		
		//logica!
		try {
			User user = this.userService.getUserByUsername(this.username);
			
			if(user != null && user.getPassword().equals(this.username)) {
				
				this.usuarioBean.setUsuario(user);
				
				String[] roles = this.userService.findRoles()
						.stream()
						.map(r -> r.getRole())
						.collect(Collectors.toList())
						.toArray(new String[] {});
				this.usuarioBean.setRoles(roles);
				
				target = PagesEnums.LOGIN_SUCCESS;
			}else {
				setError("Usuario/Password invalido");	
			}
		} catch (ServiceException e) {
			setError(e.getMessage());
		}
		
		return target.getPage();
	}
	
	public String irALogin() {	
		return "login-success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
