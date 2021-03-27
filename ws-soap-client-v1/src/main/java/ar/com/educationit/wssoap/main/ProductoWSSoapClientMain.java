package ar.com.educationit.wssoap.main;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ar.com.educationit.wssoap.Producto;
import ar.com.educationit.wssoap.ProductoWsSoapService;
import ar.com.educationit.wssoap.WSSoapException;
import ar.com.educationit.wssoap.impl.ProductoWsSoapServiceImplServiceLocator;

public class ProductoWSSoapClientMain {

	public static void main(String[] args) throws ServiceException, WSSoapException, RemoteException {
		
		ProductoWsSoapServiceImplServiceLocator locator = new ProductoWsSoapServiceImplServiceLocator();
		
		ProductoWsSoapService service = locator.getProductoWsSoapServiceImplPort();
		
		Producto[] productos = service.obtenerProductos();
		
		for(Producto producto : productos ) {
			System.out.println(producto);
		}
	}

}
