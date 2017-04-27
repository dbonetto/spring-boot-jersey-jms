package hello.services;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import hello.config.MessagingConfiguration;
import hello.models.Email;
  
@Component
public class MessageReceiver {
	
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
 
    @JmsListener(destination = MessagingConfiguration.EMAIL_QUEUE)
    public void receiveMessage(final Message<Email> message) throws JMSException {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);         
        Email email = message.getPayload();
        LOG.info("Application : response received : {}", email);         
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    
}