package ar.com.educationit.wssoap.main;

import ar.com.educationit.wssoap.impl.ProductoWsSoapServiceImpl;
import jakarta.xml.ws.Endpoint;

/*
 * Para ejecutar: java --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED -jar target/ws-soap-server-1.0.0-SNAPSHOT.jar
 */
public class ProductoWSSoapMain {

	public static void main(String[] args) {
		System.out.println("Publicando Servicio Web de producto...");
		
		Endpoint.publish("http://localhost:8000/", new ProductoWsSoapServiceImpl());
		
		System.out.println("Servicio Web de producto iniciado en http://localhost:8000/");
		
		System.out.println("La direccion del wsdl es: http://localhost:8000/?wsdl");
	}
}
