package ar.com.educationit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.rest.client.producto.ProductoRestClient;
import ar.com.educationit.rest.client.producto.impl.ProductoRestClientImpl;

@WebServlet("/ListadoSiteRestClientServlet")
public class ListadoSiteRestClientServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigo = request.getParameter("2000"); 
		
		ProductoRestClient productoRestClient = new ProductoRestClientImpl();
		
		Producto producto = productoRestClient.findProductoByCodigo(codigo);
		
		response.getWriter().print(producto);
	}
}
