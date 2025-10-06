package com.abdulla.customerinsights;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

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
                    final Environment environment) {
        // TODO: implement application
    }

}
