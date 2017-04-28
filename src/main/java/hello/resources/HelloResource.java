package hello.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import hello.NameBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/hello")
@Path("hello")
public class HelloResource {

	@Autowired NameBean tb;
	
	@GET
	@ApiOperation(
		value = "Hello!",
		nickname = "hello"
	)
    @ApiResponses(value = {
       	@ApiResponse(code = 200, message = "Hello response", response = String.class)
    })
	public Response hello(@ApiParam @QueryParam("name") @DefaultValue("World") String name) {
		String msg = "";
		if (tb.getName() != null && !tb.getName().equals(name)) {
			msg = MessageFormatter.arrayFormat("Hello {}! How's {}?", new Object[] { name, tb.getName() }).getMessage();
		} else {
			msg = MessageFormatter.arrayFormat("Hello {}!", new Object[] { name }).getMessage();
		}
		tb.setName(name);
		return Response.ok(msg).build();
	}

}