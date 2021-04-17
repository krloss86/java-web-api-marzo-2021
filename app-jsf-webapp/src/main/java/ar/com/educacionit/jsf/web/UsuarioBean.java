package ar.com.educacionit.jsf.web;

import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educacionit.jsf.web.enums.UsuarioEnum;
import ar.com.educationit.domain.User;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	public boolean logueado() {
		FacesContext fc = FacesContext.getCurrentInstance();
		boolean logueado = false;
		if(fc != null) {
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			logueado = sessionMap.containsKey(UsuarioEnum.KEY_USUARIO.name());
		}
		return logueado;
	}
	
	public String logout() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove(UsuarioEnum.KEY_USUARIO.name());
		
		return PagesEnums.LOGIN.getPage();
	}
	
	public String[] getUserRoles() {
		
		User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(UsuarioEnum.KEY_USUARIO.name());
		
		return user.getRoles()
				.stream()
				.map(role -> role.getRole())
				.collect(Collectors.toSet())
				.toArray(new String[]{});
	}
}
