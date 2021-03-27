package ar.com.educationit.wssoap.main;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ar.com.educationit.wssoap.CreateProductoDTO;
import ar.com.educationit.wssoap.impl.ProductoWsSoapServiceImplServiceLocator;

public class TestProductoWSSoap {

	public static void main(String[] args) {
		ProductoWsSoapServiceImplServiceLocator ps = new ProductoWsSoapServiceImplServiceLocator();
		try {
			CreateProductoDTO p = new CreateProductoDTO();
			p.setCodigo("100");
			p.setPrecio(100f);
			p.setTipoProducto(1l);
			p.setTitulo("t");
			ps.getProductoWsSoapServiceImplPort().crearProducto(p);
		} catch (RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
