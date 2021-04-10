package ar.com.educacionit.jsf.web;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educacionit.jsf.web.enums.UsuarioEnum;
import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.UserService;
import ar.com.educationit.service.impl.UserServiceImpl;

@ManagedBean
@RequestScoped
public class LoginBean {

	private String username;
	private String password;
	private String error;
		
	private UserService userService = new UserServiceImpl();
	
	public String login() {
		
		PagesEnums target = PagesEnums.LOGIN;
		
		//logica!
		try {
			User user = this.userService.getUserByUsername(this.username);
			
			if(user != null && user.getPassword().equals(this.username)) {
				//session
				Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.put(UsuarioEnum.KEY_USUARIO.name(), user);
				
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
