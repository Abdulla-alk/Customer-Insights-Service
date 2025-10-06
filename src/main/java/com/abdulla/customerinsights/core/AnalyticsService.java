package com.abdulla.customerinsights.core;

import com.abdulla.customerinsights.api.dto.AnalyticsSummary;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {
    private final CustomerService customers;
    private final InteractionService interactions;

    public AnalyticsService(CustomerService customers, InteractionService interactions) {
        this.customers = customers;
        this.interactions = interactions;
    }

    public AnalyticsSummary summarize(int topN, int inactiveDays) {
        List<Customer> all = customers.list();
        int totalCustomers = all.size();
        int totalInteractions = interactions.totalInteractions();

        // Build activity list
        List<AnalyticsSummary.CustomerActivity> activity = all.stream().map(c -> {
            int count = interactions.countForCustomer(c.getId());
            Instant last = interactions.lastForCustomer(c.getId());
            return new AnalyticsSummary.CustomerActivity(c.getId(), c.getName(), count, last);
        }).collect(Collectors.toList());

        // Top N by interactions
        List<AnalyticsSummary.CustomerActivity> mostActive = activity.stream()
                .sorted(Comparator.comparingInt((AnalyticsSummary.CustomerActivity a) -> a.interactions).reversed())
                .limit(topN)
                .collect(Collectors.toList());

        // Inactive since threshold
        Duration threshold = Duration.ofDays(inactiveDays);
        List<AnalyticsSummary.CustomerActivity> inactive = activity.stream()
                .filter(a -> {
                    if (a.lastInteraction == null) return true;
                    return a.lastInteraction.isBefore(Instant.now().minus(threshold));
                })
                .collect(Collectors.toList());

        return new AnalyticsSummary(totalCustomers, totalInteractions, mostActive, inactive);
    }
}
