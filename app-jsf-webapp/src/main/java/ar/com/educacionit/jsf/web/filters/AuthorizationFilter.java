package ar.com.educacionit.jsf.web.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.educacionit.jsf.web.UsuarioBean;
//import ar.com.educationit.domain.User;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter{

	//cdi
	@Inject
	private UsuarioBean usuarioBean;
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//logica
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		final List<String> resources = Arrays.asList("javax.faces.resource");
		
		final List<String> excludedPath = Arrays.asList(
				contextPath+ "/index.xhtml",
				contextPath+"/login.xhtml", 
				contextPath+"/notLogged.xhtml", 
				req.getContextPath() + "/" 
		);
		
		boolean isExcluded = excludedPath.stream()
				// .peek(x ->System.out.println(x))
				.filter(x -> uri.equals(x)).count() > 0;

		boolean isResources = resources.stream()
				// .peek(x ->System.out.println(x))
				.filter(x -> uri.contains(x)).count() > 0;

		if(isExcluded || isResources) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}else {
			//
			HttpSession session = req.getSession(false);
			
			if (session == null) {
				this.context.log("Unauthorized access request");
				res.sendRedirect(req.getContextPath()+"/notLogged.xhtml");
			}else {
				
				//User loggedUser = this.usuarioBean.getUsuario();
				boolean isLogged = this.usuarioBean.logueado();
				
				if(isLogged) {
					chain.doFilter(request, response);
				}else {
					this.context.log("Unauthorized access request");
					res.sendRedirect(req.getContextPath()+"/notLogged.xhtml");
				}
			}
		}
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
