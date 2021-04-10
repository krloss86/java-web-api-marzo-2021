package ar.com.educacionit.jsf.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SaludoManagedBean { //saludoManagedBean

	public String saludo( ) {
		return "hola: clase5 jsf";
	}
}
