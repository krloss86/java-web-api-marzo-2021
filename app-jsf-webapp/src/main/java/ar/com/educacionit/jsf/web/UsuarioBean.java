package ar.com.educacionit.jsf.web;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educacionit.jsf.web.enums.UsuarioEnum;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	public boolean logueado() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap.containsKey(UsuarioEnum.KEY_USUARIO.name()); 
	}
	
	public String logout() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove(UsuarioEnum.KEY_USUARIO.name());
		
		return PagesEnums.LOGIN.getPage();
	}
}
