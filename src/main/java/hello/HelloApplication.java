package hello;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import hello.models.EmailModel;
import hello.services.EmailSender;
import hello.services.MessageProducer;

@SpringBootApplication
@EnableJms
@EnableMongoRepositories
public class HelloApplication extends SpringBootServletInitializer {

	static final Logger LOG = LoggerFactory.getLogger(HelloApplication.class);
	
	public static void main(String[] args) {		
		ConfigurableApplicationContext context = new HelloApplication().configure(new SpringApplicationBuilder(HelloApplication.class)).run(args);
        // Send a message with a POJO - the template reuse the message converter
		EmailSender senderEmail = context.getBean(EmailSender.class);
		LOG.info("Sending an email to queue");        
        senderEmail.sendEmail(new EmailModel("info@example.com", "Hello, application is started!"));
        // Send a message with a POJO - the template reuse the message converter
		MessageProducer senderMessage = context.getBean(MessageProducer.class);
		LOG.info("Sending a message to topic");        
		senderMessage.sendMessage("Hello, application is started!");
	}

	@Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }
	
}