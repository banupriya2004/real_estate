package com.realestate.marketplace.dto;

import org.springframework.web.multipart.MultipartFile;

public class PropertyRequest {

    private String title;
    private String location;

    // ✅ FIXED: Use Double wrapper so it handles null/empty values safely
    private Double price;

    private String type;
    private String description;
    private Long agentId;
    private MultipartFile image;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getAgentId() { return agentId; }
    public void setAgentId(Long agentId) { this.agentId = agentId; }

    public MultipartFile getImage() { return image; }
    public void setImage(MultipartFile image) { this.image = image; }
}