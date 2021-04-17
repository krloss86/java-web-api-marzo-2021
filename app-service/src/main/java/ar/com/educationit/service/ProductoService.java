package ar.com.educationit.service;

import java.util.List;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.exceptions.ServiceException;

public interface ProductoService {

	public Producto grabarProducto(Producto producto) throws ServiceException;

	public List<Producto> obtenerTodos() throws ServiceException;

	public Producto obtenerPorCodigo(String codigo) throws ServiceException;

	public Producto actualizarProducto(Producto producto) throws ServiceException;

	public Producto eliminarProducto(String codigo) throws ServiceException;

	public List<TipoProducto> findTipoProductos() throws ServiceException;
}
