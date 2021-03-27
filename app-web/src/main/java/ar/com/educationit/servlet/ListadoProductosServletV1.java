package ar.com.educationit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import ar.com.educationit.enums.ViewsEnums;
import ar.com.educationit.wssoap.Producto;
import ar.com.educationit.wssoap.ProductoWsSoapService;
import ar.com.educationit.wssoap.impl.ProductoWsSoapServiceImplServiceLocator;

@WebServlet("/ListadoProductosServletV1")
public class ListadoProductosServletV1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductoWsSoapServiceImplServiceLocator locator = new ProductoWsSoapServiceImplServiceLocator();
		
		ProductoWsSoapService service;
		
		String target = "/jsp/listadoProductos.jsp";
		
		try {
			service = locator.getProductoWsSoapServiceImplPort();
			
			Producto[] productos = service.obtenerProductos();
			
			req.setAttribute(ViewsEnums.LISTADO_PRODUCTOS.name(), productos);
			
			ServletContext sc =  getServletContext();
			
			RequestDispatcher rd = sc.getRequestDispatcher(target);
			
			rd.forward(req, resp);
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(),e);
		}		
	}
}
