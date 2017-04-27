package hello.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
 
    @Autowired
    @Qualifier("MessageTopic")
    JmsTemplate jmsTemplate;
 
    public void sendMessage(final String message) {
        jmsTemplate.send(new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage objectMessage = session.createTextMessage(message);
                    return objectMessage;
                }
            });
    }
 
}