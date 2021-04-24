package ar.com.educacionit.jsf.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ar.com.educacionit.jsf.web.enums.PagesEnums;
import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.impl.ProductoServiceImpl;

@Named
@RequestScoped
public class ProductoBean {

	private ProductoService ps = new ProductoServiceImpl();
	
	private Producto producto = new Producto();
	
	private String mensajeError;
	
	private TipoProducto tipoProducto = new TipoProducto();			
	
	public List<Producto> findProductos() {
		
		List<Producto> productos = new ArrayList<Producto>();
		try {
			productos = ps.obtenerTodos();
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		
		return productos;
	}
	
	/**
	 * Cargar producto para editar
	 * @param codigo
	 */
	public String editarProducto(String codigo) {
		PagesEnums target = PagesEnums.EDITAR_PRODUCTOS;
		try {
			this.producto = this.ps.obtenerPorCodigo(codigo);
		} catch (ServiceException e) {			
			target = PagesEnums.LISTADO_PRODUCTOS;
		}
		return target.getPage();
	}
	
	public String updateProducto() {
		
		PagesEnums target = PagesEnums.LISTADO_PRODUCTOS;
		
		try {
			this.producto.setTipoProducto(this.tipoProducto);
			this.ps.actualizarProducto(producto);
		}catch (Exception e) {
			e.printStackTrace();
			this.mensajeError = e.getCause().getMessage();
			target = PagesEnums.EDITAR_PRODUCTOS;
		}
		return target.getPage();		
	}
	
	public String eliminarProducto(String codigo) {
		
		PagesEnums target = PagesEnums.LISTADO_PRODUCTOS;
		
		if(codigo != null) {
		
			try {
				this.ps.eliminarProducto(codigo);
			} catch (ServiceException e) {
				this.mensajeError = e.getMessage();				
			}
		}
		
		return target.getPage();
	}
	
	public TipoProducto[] getTipoProductos() {
		TipoProducto[] aux;
		try {
			List<TipoProducto> tipos = this.ps.findTipoProductos();
			aux = tipos.toArray(new TipoProducto[] {});
		} catch (ServiceException e) {
			e.printStackTrace();
			aux = new TipoProducto[0];
		}
		return aux;
		/*
		TipoProducto[] aux = tipos.stream()
			.collect(Collectors.toList())
			.toArray(new TipoProducto[] {});
	
		TipoProducto[] aux2 = tipos.toArray(new TipoProducto[] {});*/
	}
	
	public String crearNuevoProducto() {
		
		PagesEnums target = PagesEnums.LISTADO_PRODUCTOS;
		
		try {
			this.producto.setTipoProducto(this.tipoProducto);		
			this.ps.grabarProducto(this.producto);
		}catch (Exception e) {
			this.mensajeError = e.getMessage() + " - " +e.getCause().getMessage();
			target = PagesEnums.NUEVO_PRODUCTO;
		}
		
		return target.getPage();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
}
