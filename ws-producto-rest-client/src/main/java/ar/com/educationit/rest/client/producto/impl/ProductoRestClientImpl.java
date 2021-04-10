package ar.com.educationit.rest.client.producto.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.rest.client.producto.ProductoRestClient;

public class ProductoRestClientImpl implements ProductoRestClient {

	public Producto findProductoByCodigo(String codigo) {
			
		Client client = ClientBuilder.newClient( );
		
		WebTarget webTarget = client.target("http://localhost:8080/ws-rest-server/api/").path("productos/"+codigo);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		return response.readEntity(Producto.class);
	}
}
