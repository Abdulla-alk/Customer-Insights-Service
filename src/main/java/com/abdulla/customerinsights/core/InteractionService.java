package com.abdulla.customerinsights.core;

import jakarta.ws.rs.BadRequestException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Instant;
import java.time.Duration;

public class InteractionService {
    // customerId -> list of interactions (most recent first)
    private final Map<String, Deque<Interaction>> store = new ConcurrentHashMap<>();
    private final CustomerService customerService;

    public InteractionService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Interaction> listForCustomer(String customerId) {
        // ensure customer exists
        customerService.get(customerId);
        return new ArrayList<>(store.getOrDefault(customerId, new ArrayDeque<>()));
    }
    public int totalInteractions() {
        return store.values().stream().mapToInt(Deque::size).sum();
    }

    public int countForCustomer(String customerId) {
        return store.getOrDefault(customerId, new ArrayDeque<>()).size();
    }

    public Instant lastForCustomer(String customerId) {
        Deque<Interaction> q = store.get(customerId);
        return (q == null || q.isEmpty()) ? null : q.peekFirst().getTimestamp();
    }

    public boolean inactiveSince(String customerId, Duration threshold) {
        Instant last = lastForCustomer(customerId);
        if (last == null) return true; // never contacted
        return last.isBefore(Instant.now().minus(threshold));
    }

    public Interaction add(String customerId, Interaction incoming) {
        // ensure customer exists
        customerService.get(customerId);
        if (incoming.getCustomerId() == null || !incoming.getCustomerId().equals(customerId)) {
            throw new BadRequestException("customerId in body must match path id");
        }
        Interaction created = new Interaction(
                null, customerId, incoming.getType(), incoming.getSummary(), incoming.getTimestamp()
        );
        store.computeIfAbsent(customerId, k -> new ArrayDeque<>()).addFirst(created);
        return created;
    }
}
