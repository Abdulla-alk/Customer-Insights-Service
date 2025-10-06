package com.abdulla.customerinsights.api;

import com.abdulla.customerinsights.api.dto.AnalyticsSummary;
import com.abdulla.customerinsights.core.AnalyticsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/analytics")
@Produces(MediaType.APPLICATION_JSON)
public class AnalyticsResource {

    private final AnalyticsService analytics;

    public AnalyticsResource(AnalyticsService analytics) {
        this.analytics = analytics;
    }

    @GET
    public AnalyticsSummary get(@QueryParam("top") @DefaultValue("5") int top,
                                @QueryParam("inactiveDays") @DefaultValue("30") int inactiveDays) {
        return analytics.summarize(top, inactiveDays);
    }
}
