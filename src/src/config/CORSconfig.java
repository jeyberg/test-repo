package src.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSconfig implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext arg1) throws IOException {
		// TODO Auto-generated method stub
		arg1.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
		arg1.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		arg1.getHeaders().add("Access-Control-Allow-Credentials", "true");
		arg1.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		arg1.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}
