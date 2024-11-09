package ar.com.educationit.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ar.com.educationit.enums.ViewsEnums;

@WebServlet("/api/home")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//logica 
		
		//ir a otro jsp/servlet
		
		//cargar un valor en la request
		req.setAttribute(ViewsEnums.NOMBRE_REQUEST.name(), "carlos");
		
		HttpSession session =  req.getSession();
		session.setAttribute(ViewsEnums.NOMBRE_SESSION.name(), "carlos");
		
		ServletContext sc =  getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/home.jsp");
		
		/*if(true) {
			throw new ServletException("error interno");
		}*/
		
		rd.forward(req, resp);
	}
	
}
