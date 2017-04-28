package hello.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

import hello.resources.CustomerResource;
import hello.resources.HelloResource;
import hello.resources.MessageResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
		register(JacksonFeature.class);
		this.registerEndpoints();
	}
	
    @PostConstruct
    public void init() {
        // Register components where DI is needed
        this.configureSwagger();
    }

    private void registerEndpoints() {
		register(HelloResource.class);
		register(MessageResource.class);
		register(CustomerResource.class);
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("spring-boot-jersey-jms");
        beanConfig.setVersion("0.1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setResourcePackage("hello.resources");
        beanConfig.setScan(true);
    }

}