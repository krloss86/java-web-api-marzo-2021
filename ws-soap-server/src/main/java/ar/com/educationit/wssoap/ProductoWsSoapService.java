package ar.com.educationit.wssoap;

import java.util.List;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.service.faults.WSSoapException;
import ar.com.educationit.wssoap.dto.CreateProductoDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ProductoWsSoapService {

	@WebMethod
	public Producto crearProducto(CreateProductoDTO request) throws WSSoapException;
	
	@WebMethod
	public List<Producto> obtenerProductos() throws WSSoapException;
}
