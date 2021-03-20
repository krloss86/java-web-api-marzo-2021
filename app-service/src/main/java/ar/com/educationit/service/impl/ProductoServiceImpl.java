package ar.com.educationit.service.impl;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.repository.ProductoRepository;
import ar.com.educationit.repository.impl.ProductoRepositoryHibImpl;
import ar.com.educationit.service.ProductoService;

public class ProductoServiceImpl implements ProductoService{

	private ProductoRepository pr;
	
	public ProductoServiceImpl() {
		// IOC, DI
		this.pr = new ProductoRepositoryHibImpl();
	}

	@Override
	public Producto grabarProducto(Producto producto) throws Exception {		
		return this.pr.save(producto);
	}
	
	
}
