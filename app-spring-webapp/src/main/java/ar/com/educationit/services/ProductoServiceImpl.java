package ar.com.educationit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.repository.ProductoRepository;
import ar.com.educationit.repository.TipoProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository pr;
	
	@Autowired
	private TipoProductoRepository tpr;	
	
	@Override
	public Producto grabarProducto(Producto producto) {		
		return this.pr.save(producto);
	}
	
	@Override
	public List<Producto> obtenerTodos()  {		
		return this.pr.findAll();
	}
	
	@Override
	public Producto obtenerPorCodigo(String codigo)  {
		return this.pr.findByCodigo(codigo);
	}
	
	@Override
	public Producto actualizarProducto(Producto producto)  {
		return this.pr.save(producto);
	}
	
	@Override
	public Producto eliminarProducto(String codigo)  {
		Producto p = obtenerPorCodigo(codigo);
		this.pr.delete(p);
		return p;
	}
	/*
	@Override
	public List<TipoProducto> findTipoProductos()  {
		return this.pr.findTipoProductos();
	}
	
	@Override
	public List<Producto> findProductosByDescripcion(String desripcion)  {
		return this.pr.search(desripcion);
	}*/
	
	@Override
	public Producto obtenerPorId(Long id) {
		return this.pr.findById(id).get();
	}
	
	@Override
	public List<TipoProducto> findTipoProductos() {
		return this.tpr.findAll();
	}
}
