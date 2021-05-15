package ar.com.educationit.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.impl.ProductoServiceImpl;

@Path("tipoproducto")
public class TipoProductoResoruce {

	private ProductoService productoService = new ProductoServiceImpl();
	
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		//ctrl+shit+o
		try {
			List<TipoProducto> productos = productoService.findTipoProductos();
			return Response.ok(productos).build();
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
