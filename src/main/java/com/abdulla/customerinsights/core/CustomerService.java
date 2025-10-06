package com.abdulla.customerinsights.core;

import jakarta.ws.rs.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService {
    private final Map<String, Customer> store = new ConcurrentHashMap<>();

    public List<Customer> list() {
        return new ArrayList<>(store.values());
    }

    public Customer get(String id) {
        Customer c = store.get(id);
        if (c == null) throw new NotFoundException("Customer " + id + " not found");
        return c;
    }

    public Customer create(Customer incoming) {
        Customer c = new Customer(null, incoming.getName(), incoming.getCompany(),
                incoming.getEmail(), incoming.getPhone());
        store.put(c.getId(), c);
        return c;
    }

    public Customer update(String id, Customer incoming) {
        // Ensure it exists
        get(id);
        Customer updated = new Customer(id, incoming.getName(), incoming.getCompany(),
                incoming.getEmail(), incoming.getPhone());
        store.put(id, updated);
        return updated;
    }

    public void delete(String id) {
        if (store.remove(id) == null) throw new NotFoundException("Customer " + id + " not found");
    }
}
