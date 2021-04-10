package ar.com.educationit.rest.client.producto;

import ar.com.educationit.domain.Producto;

public interface ProductoRestClient {

	public Producto findProductoByCodigo(String codigo);
}
