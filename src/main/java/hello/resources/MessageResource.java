package hello.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import hello.models.EmailModel;
import hello.services.EmailSender;
import hello.services.MessageProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/messages")
@Path("messages")
public class MessageResource {

	@Autowired EmailSender senderEmail;
	@Autowired MessageProducer senderMessage;
	
	@POST
	@Path("sendEmail")
	@ApiOperation(
		value = "Send email to queue",
		nickname = "sendEmail"
	)
    @ApiResponses(value = {
       	@ApiResponse(code = 202, message = "Email sent", response = Void.class)
    })	
	public Response sendEmail(
		@ApiParam @DefaultValue("info@example.com") @QueryParam("address") String address,
		@ApiParam @QueryParam("message") @NotNull String message
	) {
		senderEmail.sendEmail(new EmailModel(address, message));
		return Response.accepted().build();
	}

	@POST
	@Path("sendMessage")
	@ApiOperation(
		value = "Send message to topic",
		nickname = "sendMessage"
	)
    @ApiResponses(value = {
       	@ApiResponse(code = 202, message = "Message sent", response = Void.class)
    })	
	public Response sendMessage(@ApiParam @QueryParam("text") @NotNull String text) {
		senderMessage.sendMessage(text);
		return Response.accepted().build();
	}

}