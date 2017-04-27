package hello.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import hello.models.Email;
import hello.services.MessageSender;

@Path("/email")
@Produces("application/json")
public class EmailResource {

	@Autowired MessageSender sender;
	
	@POST
	@Path("/send")
	public Response send(@DefaultValue("info@example.com") @QueryParam("address") String address, @QueryParam("message") @NotNull String message) {
		sender.sendEmail(new Email(address, message));
		return Response.accepted().build();
	}
	
}