package ar.com.educationit.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.logging.LoggingFeature;

import ar.com.educationit.domain.Producto;

public class ProductoRestClient {

	public static void main(String[] args) {
		
		String codigo = "000001";
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("http://localhost:8080/ws-rest-server/api/").path("productos/"+codigo);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		Producto producto = response.readEntity(Producto.class);
		
		System.out.println(producto);
		
	}
}
