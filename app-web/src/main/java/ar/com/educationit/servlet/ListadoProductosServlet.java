package ar.com.educationit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import ar.com.educationit.wssoap.Producto;
import ar.com.educationit.wssoap.ProductoWsSoapService;
import ar.com.educationit.wssoap.impl.ProductoWsSoapServiceImplServiceLocator;

@WebServlet("/ListadoProductosServlet")
public class ListadoProductosServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductoWsSoapServiceImplServiceLocator locator = new ProductoWsSoapServiceImplServiceLocator();
		
		ProductoWsSoapService service;
		try {
			service = locator.getProductoWsSoapServiceImplPort();
			Producto[] productos = service.obtenerProductos();
			
			for(Producto producto : productos ) {
				resp.getWriter().print(producto);
			}
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(),e);
		}
	}
}
