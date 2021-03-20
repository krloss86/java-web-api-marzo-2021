package ar.com.educationit.repository;

import ar.com.educationit.domain.Producto;

public interface ProductoRepository {

	public Producto save(Producto producto) throws DuplicatedException, GenericException;
}
