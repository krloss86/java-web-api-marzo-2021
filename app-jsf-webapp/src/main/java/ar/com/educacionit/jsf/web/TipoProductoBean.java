package ar.com.educacionit.jsf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.impl.ProductoServiceImpl;

@Named
@ViewScoped
public class TipoProductoBean implements Serializable{

	private static final long serialVersionUID = -4361370965622954788L;

	private ProductoService ps = new ProductoServiceImpl();
	
	private List<TipoProducto> tipoProductos;
	
	@PostConstruct
	private void loadTipoProductos() {
		try {
			this.tipoProductos = ps.findTipoProductos();
		} catch (ServiceException e) {			
			e.printStackTrace();
			this.tipoProductos = new ArrayList<>();
		}
	}
	
	public TipoProducto[] getTipoProductos() {
		return this.tipoProductos.toArray(new TipoProducto[] {});
	}
}
