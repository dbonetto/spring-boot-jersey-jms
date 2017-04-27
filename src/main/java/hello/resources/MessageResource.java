package hello.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import hello.models.EmailModel;
import hello.services.EmailSender;
import hello.services.MessageProducer;

@Path("/message")
@Produces("application/json")
public class MessageResource {

	@Autowired EmailSender senderEmail;
	@Autowired MessageProducer senderMessage;
	
	@POST
	@Path("/sendEmail")
	public Response sendEmail(@DefaultValue("info@example.com") @QueryParam("address") String address, @QueryParam("message") @NotNull String message) {
		senderEmail.sendEmail(new EmailModel(address, message));
		return Response.accepted().build();
	}

	@POST
	@Path("/sendMessage")
	public Response sendMessage(@QueryParam("text") @NotNull String text) {
		senderMessage.sendMessage(text);
		return Response.accepted().build();
	}

}