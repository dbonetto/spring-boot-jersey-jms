package hello.config;
import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import hello.services.MessageReceiver;
 
@Configuration
public class MessagingConfiguration {
 
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
     
    public static final String EMAIL_QUEUE = "email-queue";
    public static final String MESSAGE_TOPIC = "message-topic";
 
    @Autowired
    MessageReceiver messageReceiver;
    
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("hello"));
        return connectionFactory;
    }
     
    @Bean(name = "EmailQueue")
    public JmsTemplate jmsTemplateQueue() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(EMAIL_QUEUE);
        return template;
    }

    @Bean(name = "MessageTopic")
    public JmsTemplate jmsTemplateTopic() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestination(new ActiveMQTopic(MESSAGE_TOPIC));
        return template;
    }
    
    @Bean
    public MessageListenerContainer getContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestination(new ActiveMQTopic(MESSAGE_TOPIC));
        container.setMessageListener(messageReceiver);
        return container;
    }
    
    @Bean
    MessageConverter converter() {
        return new SimpleMessageConverter();
    }
    
}