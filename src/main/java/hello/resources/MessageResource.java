package hello.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import hello.models.Email;

@Path("/message")
@Produces("application/json")
public class MessageResource {

	@Autowired JmsTemplate jmsTemplate;
	
	@POST
	@Path("/send")
	public Response send(@DefaultValue("info@example.com") @QueryParam("address") String address, @QueryParam("message") @NotNull String message) {
		jmsTemplate.convertAndSend("mailbox", new Email(address, message));
		return Response.accepted().build();
	}
	
}