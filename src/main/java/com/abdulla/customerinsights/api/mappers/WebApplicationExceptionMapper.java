package com.abdulla.customerinsights.api.mappers;

import com.abdulla.customerinsights.api.dto.ErrorResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(WebApplicationException e) {
        Response.Status status = Response.Status.fromStatusCode(e.getResponse().getStatus());
        String reason = status != null ? status.getReasonPhrase() : "Error";

        ErrorResponse body = new ErrorResponse(
                e.getResponse().getStatus(),
                reason,
                e.getMessage(),
                uriInfo != null ? uriInfo.getPath() : null,
                null
        );

        return Response.status(e.getResponse().getStatus())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(body)
                .build();
    }
}
