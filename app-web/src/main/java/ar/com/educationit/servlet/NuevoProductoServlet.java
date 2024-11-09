package ar.com.educationit.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.service.ProductoService;
import ar.com.educationit.service.impl.ProductoServiceImpl;
@WebServlet("/NuevoProductoServlet")
public class NuevoProductoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//sin validaciones!
		String titulo = req.getParameter("titulo");		
		Float precio = Float.parseFloat(req.getParameter("precio"));
		String codigo = req.getParameter("codigo");
		Long tipoProducto = Long.parseLong(req.getParameter("tipoProducto"));
		
		TipoProducto tp = new TipoProducto();
		tp.setId(tipoProducto);
		
		//producto
		Producto producto = new Producto(titulo, precio, codigo, tp);
		
		//service 
		ProductoService	ps = new ProductoServiceImpl();
		try {
			producto = ps.grabarProducto(producto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//escribir 
		resp.getWriter().write(producto.toString());
	}
}
