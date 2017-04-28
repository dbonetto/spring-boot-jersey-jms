package hello.resources;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import hello.dao.CustomerRepository;
import hello.entities.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/customers")
@Path("customers")
public class CustomerResource {

	@Autowired CustomerRepository cRepo;
	@Context UriInfo uriInfo;
	
	@PUT
	@ApiOperation(
		value = "Save or update customer",
		nickname = "saveCustomer"
	)
    @ApiResponses(value = {
    	@ApiResponse(code = 201, message = "Customer created", response = Void.class)
    })
	public Response saveCustomer(@ApiParam Customer customer) {
		customer = cRepo.save(customer);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(customer.getId());
        return Response.created(builder.build()).build();
	}
	
	@GET
	@Path("{id}")
	@ApiOperation(
		value = "Get customer",
		nickname = "getCustomer"
	)
    @ApiResponses(value = {
    	@ApiResponse(code = 200, message = "Customer detail", response = Customer.class),
    	@ApiResponse(code = 404, message = "Customer not found", response = Void.class)
    })	
	public Response getCustomer(@ApiParam @PathParam("id") String id) {
		Customer customer = cRepo.findOne(id);
		return (customer != null) ? Response.ok(customer).build() : Response.status(Status.NOT_FOUND).build();
	}
		
	@DELETE
	@Path("{id}")
	@ApiOperation(
		value = "Delete customer",
		nickname = "deleteCustomer"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 204, message = "Customer deleted", response = Void.class),
    	@ApiResponse(code = 404, message = "Customer not found", response = Void.class)
    })		
	public Response deleteCustomer(@ApiParam @PathParam("id") String id) {
		Customer customer = cRepo.findOne(id);
		if (customer != null) {
			cRepo.delete(customer);
			return Response.noContent().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("clear")
	@ApiOperation(
		value = "Delete all customers",
		nickname = "deleteAllCustomers"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 204, message = "Customers deleted", response = Void.class)
    })	
	public Response deleteAllCustomers() {
		cRepo.deleteAll();
		return Response.noContent().build();
	}

	@GET
	@Path("list")
	@ApiOperation(
		value = "Get customer list",
		nickname = "getCustomerList"
	)	
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "Customer list", response = Customer.class, responseContainer = "List")
    })	
	public Response getCustomerList() {
		return Response.ok(cRepo.findAll()).build();
	}

}