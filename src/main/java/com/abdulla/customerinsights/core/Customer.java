package com.abdulla.customerinsights.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final String id;

    @NotBlank private final String name;
    private final String company;
    @Email private final String email;
    private final String phone;

    @JsonCreator
    public Customer(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("company") String company,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.phone = phone;
    }

    @JsonProperty public String getId() { return id; }
    @JsonProperty public String getName() { return name; }
    @JsonProperty public String getCompany() { return company; }
    @JsonProperty public String getEmail() { return email; }
    @JsonProperty public String getPhone() { return phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return Objects.equals(id, c.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
}
