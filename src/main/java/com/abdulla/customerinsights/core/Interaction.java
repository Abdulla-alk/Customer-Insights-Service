package com.abdulla.customerinsights.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Interaction {
    private final String id;
    @NotBlank private final String customerId;
    @NotBlank private final String type;   // e.g. "call", "email", "meeting", "note"
    @NotBlank private final String summary;
    private final Instant timestamp;

    @JsonCreator
    public Interaction(
            @JsonProperty("id") String id,
            @JsonProperty("customerId") String customerId,
            @JsonProperty("type") String type,
            @JsonProperty("summary") String summary,
            @JsonProperty("timestamp") Instant timestamp
    ) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.customerId = customerId;
        this.type = type;
        this.summary = summary;
        this.timestamp = (timestamp == null) ? Instant.now() : timestamp;
    }

    @JsonProperty public String getId() { return id; }
    @JsonProperty public String getCustomerId() { return customerId; }
    @JsonProperty public String getType() { return type; }
    @JsonProperty public String getSummary() { return summary; }
    @JsonProperty public Instant getTimestamp() { return timestamp; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interaction)) return false;
        Interaction that = (Interaction) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
