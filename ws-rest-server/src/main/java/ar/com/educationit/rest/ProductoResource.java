package ar.com.educationit.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.rest.enums.ProductoErrorsEnum;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.impl.ProductoServiceImpl;

@Path("productos")
@Singleton
public class ProductoResource {
	
	private ProductoService ps = new ProductoServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProductos() {
		
		ProductoService ps = new ProductoServiceImpl();
		
		try {
			List<Producto> productos = ps.obtenerTodos();
			//200
			//json
			return Response.ok(productos).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProducto(Producto producto ) {
		
		Map<String, String> errors = new HashMap<>();
		
		//validaciones
		if(producto == null) {
			errors.put(ProductoErrorsEnum.PRODUCTO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_VACIO.getError());
		}
		if(producto.getCodigo() == null || producto.getCodigo().isEmpty()) {
			errors.put(ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.getError());
		}
		//completar las que faltan!!!
		
		if(!errors.isEmpty()) {
			return Response.status(Status.BAD_REQUEST)
					.entity(errors)
					.build();
		}
		
		try {
			
			Producto productoDd = ps.obtenerPorCodigo(producto.getCodigo());
			
			if(productoDd == null) {
				productoDd = ps.grabarProducto(producto);
			}
			
			return Response.ok(productoDd).build();
		} catch (ServiceException e) {			
			errors = buildErrors(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errors)
					.build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateProducto(@PathParam("id") Long idProducto ,Producto producto ) {
		
		Map<String, String> errors = new HashMap<>();
		
		//validaciones
		if(producto == null) {
			errors.put(ProductoErrorsEnum.PRODUCTO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_VACIO.getError());
		}
		if(producto.getCodigo() == null || producto.getCodigo().isEmpty()) {
			errors.put(ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.getError());
		}
		
		if(!idProducto.equals(producto.getId())) {
			errors.put(ProductoErrorsEnum.PRODUCTO_ID_INVALIDO.name(), ProductoErrorsEnum.PRODUCTO_ID_INVALIDO.getError());
		}
		
		Producto productoDd;
		try {
			productoDd = ps.obtenerPorCodigo(producto.getCodigo());
		} catch (ServiceException e1) {
			productoDd = null;
		}
		
		if(productoDd == null) {
			errors.put(ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.getError());				
		}
		
		//completar las que faltan!!!
		
		if(!errors.isEmpty()) {
			return Response.status(Status.BAD_REQUEST)
					.entity(errors)
					.build();
		}
		
		try {
			
			productoDd = ps.actualizarProducto(producto);
			
			if(productoDd == null) {
				errors.put(ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.name(), ProductoErrorsEnum.PRODUCTO_CODIGO_VACIO.getError());				
			}
			
			return Response.ok(productoDd).build();
		} catch (ServiceException e) {			
			errors = buildErrors(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errors)
					.build();
		}
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductoByCodigo(@PathParam("codigo") String codigo)  {
		
		Producto producto;
		try {
			producto = this.ps.obtenerPorCodigo(codigo);
			
			if(producto == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			
			return Response.ok(producto).build();
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(buildErrors(e))
					.build();
		}			
	}
	
	@DELETE
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProducto(@PathParam("codigo") String codigo) {
		try {
			Producto producto = this.ps.eliminarProducto(codigo);
			return Response.ok(producto).build();
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(buildErrors(e))
					.build();
		}
	}

	private Map<String, String> buildErrors(ServiceException e) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", e.getMessage());
		if(e.getCause() != null) {
			errors.put("error2", e.getCause().getMessage());	
		}
		return errors;
	}
}
