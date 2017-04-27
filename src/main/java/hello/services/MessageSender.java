package hello.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import hello.models.Email;

@Component
public class MessageSender {
 
    @Autowired 
    JmsTemplate jmsTemplate;
 
    public void sendEmail(final Email email) {
        jmsTemplate.send(new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ObjectMessage objectMessage = session.createObjectMessage(email);
                    return objectMessage;
                }
            });
    }
 
}