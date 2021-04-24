package ar.com.educacionit.jsf.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

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
	
	private List<Producto> productos; 
	
	@PostConstruct
	private void loadProductos() {
		this.productos = this.findProductos();
	}
	
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

	public void onRowEdit(RowEditEvent<Producto> event) {
		FacesMessage msg;
		Producto productoSeleccionado = event.getObject();
		try {
			if(!productoSeleccionado.getTipoProducto().getId().equals(this.tipoProducto.getId())) {
				productoSeleccionado.getTipoProducto().setId(this.tipoProducto.getId());
			}
			this.ps.actualizarProducto(productoSeleccionado);
			msg = new FacesMessage("Producto editado ", productoSeleccionado.getId().toString());
			this.productos = this.findProductos();
		} catch (ServiceException e) {
			msg = new FacesMessage(e.getMessage(), productoSeleccionado.getId().toString());
		}
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCandel(RowEditEvent<Producto> event) {
		Producto productoSeleccionado = event.getObject();
        FacesMessage msg = new FacesMessage("Edit Cancelled", productoSeleccionado.getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void eliminarProducto() {
		FacesMessage msg;
		try {
			this.ps.eliminarProducto(producto.getCodigo());
			msg = new FacesMessage("Producto eliminado ", producto.getId().toString());
			this.productos.remove(producto);
			this.producto = null;
		} catch (Exception e) {
			msg = new FacesMessage("Error eliminando producto", e.getMessage());
		}
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowSelect(SelectEvent<Producto> event) {
		this.producto = event.getObject();
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
}
