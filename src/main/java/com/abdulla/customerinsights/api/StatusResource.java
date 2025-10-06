package com.abdulla.customerinsights.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.Map;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusResource {
    @GET
    public Map<String, Object> get() {
        return Map.of(
                "ok", true,
                "service", "customer-insights-service",
                "time", Instant.now().toString()
        );
    }
}