package com.abdulla.customerinsights;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.abdulla.customerinsights.api.StatusResource;
import com.abdulla.customerinsights.api.CustomerResource;
import com.abdulla.customerinsights.core.CustomerService;
import com.abdulla.customerinsights.api.InteractionResource;
import com.abdulla.customerinsights.core.InteractionService;
import com.abdulla.customerinsights.api.AnalyticsResource;
import com.abdulla.customerinsights.core.AnalyticsService;
public class CustomerInsightsServiceApplication extends Application<CustomerInsightsServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CustomerInsightsServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "CustomerInsightsService";
    }

    @Override
    public void initialize(final Bootstrap<CustomerInsightsServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CustomerInsightsServiceConfiguration configuration,
                    final io.dropwizard.core.setup.Environment environment) {

        // keep your StatusResource registration
        environment.jersey().register(new com.abdulla.customerinsights.api.StatusResource());

        // register customer API
        CustomerService customerService = new CustomerService();
        environment.jersey().register(new CustomerResource(customerService));

        InteractionService interactionService = new InteractionService(customerService);
        environment.jersey().register(new InteractionResource(interactionService));

        AnalyticsService analyticsService = new AnalyticsService(customerService, interactionService);
        environment.jersey().register(new AnalyticsResource(analyticsService));

        environment.jersey().register(new com.abdulla.customerinsights.api.mappers.ConstraintViolationExceptionMapper());
        environment.jersey().register(new com.abdulla.customerinsights.api.mappers.WebApplicationExceptionMapper());

    }

}
