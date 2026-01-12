package com.realestate.marketplace.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "buyer_agent_mapping")
public class BuyerAgentMapping {

    public enum Status {
        REQUESTED, ASSIGNED, IN_PROGRESS, COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buyerId; // Stored in DB

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String location;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime assignedAt;
    private boolean active = true;

    // ✅ PERMANENT FIX: Transient fields for Frontend Display
    @Transient
    private String buyerName;

    @Transient
    private String buyerEmail;

    @Transient
    private String buyerPhone;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBuyerId() { return buyerId; }
    public void setBuyerId(Long buyerId) { this.buyerId = buyerId; }
    public Agent getAgent() { return agent; }
    public void setAgent(Agent agent) { this.agent = agent; }
    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
    public String getBuyerEmail() { return buyerEmail; }
    public void setBuyerEmail(String buyerEmail) { this.buyerEmail = buyerEmail; }
    public String getBuyerPhone() { return buyerPhone; }
    public void setBuyerPhone(String buyerPhone) { this.buyerPhone = buyerPhone; }
}