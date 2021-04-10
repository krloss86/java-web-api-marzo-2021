package ar.com.educationit.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import ar.com.educationit.rest.client.meli.Site;

public class MeliSiteRestClient {

	public static void main(String[] args) {
			
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("https://api.mercadolibre.com/").path("sites");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		List<Site> sites = response.readEntity(new GenericType<List<Site>>() {});
		
		System.out.println(sites);
	}
}
