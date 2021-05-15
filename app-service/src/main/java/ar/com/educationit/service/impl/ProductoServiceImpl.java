package ar.com.educationit.service.impl;

import java.util.List;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.repository.DuplicatedException;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.ProductoRepository;
import ar.com.educationit.repository.impl.ProductoRepositoryHibImpl;
import ar.com.educationit.service.ProductoService;

//log4j2
//logback
//slf4
public class ProductoServiceImpl implements ProductoService{

	private ProductoRepository pr;
	//private TipoProductoRepository tpr;
	
	public ProductoServiceImpl() {
		// IOC, DI
		this.pr = new ProductoRepositoryHibImpl();
		//this.tpr = new TipoProductoRepository();
	}

	@Override
	public Producto grabarProducto(Producto producto) throws ServiceException {		
		try {
			return this.pr.save(producto);
		} catch (DuplicatedException e) {
			//log
			throw new ServiceException("Producto duplicado", e);
		} catch (GenericException e) {
			//log
			throw new ServiceException("Error interno", e);
		}
	}
	
	@Override
	public List<Producto> obtenerTodos() throws ServiceException {		
		try {
			return this.pr.findAll();
		} catch (GenericException e) {
			//log
			throw new ServiceException("No se ha podido obtener la lista de productos", e);
		}
	}
	
	@Override
	public Producto obtenerPorCodigo(String codigo) throws ServiceException {
		try {
			return this.pr.getByCodigo(codigo);
		} catch (GenericException e) {
			//log
			throw new ServiceException("No se ha podido obtener producto codigo:" + codigo, e);
		}
	}
	
	@Override
	public Producto actualizarProducto(Producto producto) throws ServiceException {
		try {
			return this.pr.update(producto);
		} catch (GenericException e) {
			//log
			throw new ServiceException("No se ha podido actualizar el producto codigo:" + producto.getCodigo(), e);
		}
	}
	
	@Override
	public Producto eliminarProducto(String codigo) throws ServiceException {
		try {
			return this.pr.delete(codigo);
		} catch (GenericException e) {
			throw new ServiceException("No se ha podido eliminar el producto codigo:" + codigo, e);
		}
	}
	
	@Override
	public List<TipoProducto> findTipoProductos() throws ServiceException {
		try {
			return this.pr.findTipoProductos();
		} catch (GenericException e) {
			throw new ServiceException("No se ha podido obtener la lista de tipo de productos", e);
		}
	}
	
	@Override
	public List<Producto> findProductosByDescripcion(String desripcion) throws ServiceException {
		try {
			return this.pr.search(desripcion);
		} catch (GenericException e) {
			throw new ServiceException("No se pudo obtener resultados",e);
		}
	}
}
