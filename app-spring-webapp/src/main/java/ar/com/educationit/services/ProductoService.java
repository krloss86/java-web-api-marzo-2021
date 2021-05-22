package ar.com.educationit.services;

import java.util.List;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;

public interface ProductoService {

	public Producto grabarProducto(Producto producto);

	public List<Producto> obtenerTodos();

	public Producto obtenerPorCodigo(String codigo);
	
	public Producto obtenerPorId(Long id);

	public Producto actualizarProducto(Producto producto);

	public Producto eliminarProducto(String codigo);

	public List<TipoProducto> findTipoProductos();
/*	
	public List<Producto> findProductosByDescripcion(String desripcion);
*/
}
