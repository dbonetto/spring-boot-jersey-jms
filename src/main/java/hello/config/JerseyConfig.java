package hello.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import hello.resources.HelloResource;
import hello.resources.MessageResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(HelloResource.class);
		register(MessageResource.class);
	}

}