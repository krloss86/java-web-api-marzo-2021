package ar.com.educationit.wssoap.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.faults.WSSoapException;
import ar.com.educationit.service.impl.ProductoServiceImpl;
import ar.com.educationit.wssoap.ProductoWsSoapService;
import ar.com.educationit.wssoap.dto.CreateProductoDTO;
import jakarta.jws.WebService;

@WebService(endpointInterface = "ar.com.educationit.wssoap.ProductoWsSoapService")
public class ProductoWsSoapServiceImpl implements ProductoWsSoapService {

	@Override
	public Producto crearProducto(CreateProductoDTO request) throws WSSoapException {
		
		//acceder a nuestra capa de servicios!
		TipoProducto tp = new TipoProducto();
		tp.setId(request.getTipoProducto());
		
		Producto nuevoProducto = new Producto(request.getTitulo(), request.getPrecio(), request.getCodigo(), tp);
		
		ProductoService ps = new ProductoServiceImpl();
		
		try {
			return ps.grabarProducto(nuevoProducto);
		} catch (Exception e) {
			throw new WSSoapException(e.getMessage());
		}
	}

	@Override
	public List<Producto> obtenerProductos() throws WSSoapException {
		
		ProductoService ps = new ProductoServiceImpl();
		
		List<Producto> productos = new ArrayList<Producto>();
		try {
			productos = ps.obtenerTodos();
		}catch (Exception e) {
			throw new WSSoapException(e.getMessage());
		}
		
		return productos;
	}
}
