package com.abdulla.customerinsights.api;

import com.abdulla.customerinsights.core.Interaction;
import com.abdulla.customerinsights.core.InteractionService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/customers/{customerId}/interactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InteractionResource {

    private final InteractionService service;

    public InteractionResource(InteractionService service) {
        this.service = service;
    }

    @GET
    public List<Interaction> list(@PathParam("customerId") String customerId) {
        return service.listForCustomer(customerId);
    }

    @POST
    public Response create(@PathParam("customerId") String customerId, @Valid Interaction body) {
        Interaction created = service.add(customerId, body);
        return Response.created(URI.create("/customers/" + customerId + "/interactions/" + created.getId()))
                .entity(created)
                .build();
    }
}
