package ar.com.educationit.rest.configuration;

import org.glassfish.jersey.server.ResourceConfig;

public class AppWSRestApplication extends ResourceConfig {

	public AppWSRestApplication() {
		System.out.println("AppWSRestApplication");
		
		//registrar dos filtros
		register(CORSFilter.class);
		register(AuthenticationFilter.class);
	
	}
}
