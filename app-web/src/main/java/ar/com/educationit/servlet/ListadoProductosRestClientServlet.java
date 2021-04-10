package ar.com.educationit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.educationit.rest.client.meli.MeliRestClient;
import ar.com.educationit.rest.client.meli.Site;
import ar.com.educationit.rest.client.meli.impl.MeliRestClientImpl;

@WebServlet("/ListadoProductosRestClientServlet")
public class ListadoProductosRestClientServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MeliRestClient meliRestClient = new MeliRestClientImpl();
		
		List<Site> sites = meliRestClient.findSites();
		
		response.getWriter().print(sites);
	}
}
