package hello.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import hello.models.EmailModel;

@Component
public class EmailSender {
 
    @Autowired
    @Qualifier("EmailQueue")
    JmsTemplate jmsTemplate;
 
    public void sendEmail(final EmailModel email) {
        jmsTemplate.send(new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ObjectMessage objectMessage = session.createObjectMessage(email);
                    return objectMessage;
                }
            });
    }
 
}