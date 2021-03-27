package ar.com.educationit.repository;

import java.util.List;

import ar.com.educationit.domain.Producto;

public interface ProductoRepository {

	//crud
	public Producto save(Producto producto) throws DuplicatedException, GenericException;
	
	public List<Producto> findAll() throws GenericException;
	
	public Producto getByCodigo(String codigoProducto) throws GenericException;
	
	public Producto getById(Long id) throws GenericException;
	
	public Producto update(Producto producto) throws GenericException;
	
	public Producto delete (String codigo) throws GenericException;
	
	public List<Producto> search(String titulo) throws GenericException;
}
