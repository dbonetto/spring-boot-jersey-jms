package hello.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import hello.NameBean;

@Path("/hello")
@Produces("application/json")
public class HelloResource {

	@Autowired NameBean tb;
	
	@GET	
	public Response message(@QueryParam("name") @DefaultValue("World") String name) {
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