package ar.com.educationit.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String target = "/jsp/loginSuccess.jsp";
		if("carlos".equals(username) && "1234".equals(password)) {			
			req.getSession().setAttribute("user", username);
		}else {
			target = "/jsp/loginFail.jsp";
		}
		
		//rediect
		getServletContext().getRequestDispatcher(target).forward(req, resp);
	}
}
