package ar.com.educacionit.jsf.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educationit.rest.client.meli.MeliRestClient;
import ar.com.educationit.rest.client.meli.Site;
import ar.com.educationit.rest.client.meli.impl.MeliRestClientImpl;

@Named
@RequestScoped
public class ListadoProductosRestClientBean {

	private List<Site> sites;
	
	public String findSites() {
		
		MeliRestClient meliRestClient = new MeliRestClientImpl();
		
		sites = meliRestClient.findSites();
		
		return PagesEnums.LISTADO_SITES.getPage();
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
}
