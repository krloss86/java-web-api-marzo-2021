package ar.com.educacionit.jsf.web;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educationit.domain.User;

@Named
@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = -5542155117157234481L;
	
	private User usuario;
	private String[] roles;
	
	public boolean logueado() {
		return this.usuario != null;
	}
	
	public String logout() {
		setUsuario(null);
		setRoles(new String[] {});
		return PagesEnums.LOGIN.getPage();
	}
	
	public String[] getUserRoles() {
		return this.usuario.getRoles()
				.stream()
				.map(role -> role.getRole())
				.collect(Collectors.toSet())
				.toArray(new String[]{});
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
