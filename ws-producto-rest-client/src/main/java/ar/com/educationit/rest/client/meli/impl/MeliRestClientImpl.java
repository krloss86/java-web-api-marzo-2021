package ar.com.educationit.rest.client.meli.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.educationit.rest.client.meli.MeliRestClient;
import ar.com.educationit.rest.client.meli.Site;

public class MeliRestClientImpl implements MeliRestClient {

	@Override
	public List<Site> findSites() {

		Client client = ClientBuilder.newClient(  );
		
		WebTarget webTarget = client.target("https://api.mercadolibre.com/").path("sites");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		return response.readEntity(new GenericType<List<Site>>() {});
	}

}
