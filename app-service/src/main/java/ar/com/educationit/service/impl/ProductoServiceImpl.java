package ar.com.educationit.service.impl;

import java.util.List;

import ar.com.educationit.domain.Producto;
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
	
	public ProductoServiceImpl() {
		// IOC, DI
		this.pr = new ProductoRepositoryHibImpl();
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
}
