package com.abdulla.customerinsights.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;

public class AnalyticsSummary {

    public static class CustomerActivity {
        @JsonProperty public String customerId;
        @JsonProperty public String name;
        @JsonProperty public int interactions;
        @JsonProperty public Instant lastInteraction;

        public CustomerActivity(String customerId, String name, int interactions, Instant lastInteraction) {
            this.customerId = customerId;
            this.name = name;
            this.interactions = interactions;
            this.lastInteraction = lastInteraction;
        }
    }

    @JsonProperty public int totalCustomers;
    @JsonProperty public int totalInteractions;
    @JsonProperty public List<CustomerActivity> mostActive; // top N
    @JsonProperty public List<CustomerActivity> inactive;   // no interactions in X days

    public AnalyticsSummary(int totalCustomers, int totalInteractions,
                            List<CustomerActivity> mostActive,
                            List<CustomerActivity> inactive) {
        this.totalCustomers = totalCustomers;
        this.totalInteractions = totalInteractions;
        this.mostActive = mostActive;
        this.inactive = inactive;
    }
}
