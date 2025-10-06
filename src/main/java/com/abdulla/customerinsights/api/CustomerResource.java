package com.abdulla.customerinsights.api;

import com.abdulla.customerinsights.core.Customer;
import com.abdulla.customerinsights.core.CustomerService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GET
    public List<Customer> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Customer get(@PathParam("id") String id) {
        return service.get(id);
    }

    @POST
    public Response create(@Valid Customer body) {
        Customer created = service.create(body);
        return Response.created(URI.create("/customers/" + created.getId()))
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Customer update(@PathParam("id") String id, @Valid Customer body) {
        return service.update(id, body);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
