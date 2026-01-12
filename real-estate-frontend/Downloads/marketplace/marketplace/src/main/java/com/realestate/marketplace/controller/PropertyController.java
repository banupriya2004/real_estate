package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.Property;
import com.realestate.marketplace.dto.PropertyRequest;
import com.realestate.marketplace.repository.AgentRepository;
import com.realestate.marketplace.repository.PropertyRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;

    // Constructor injection
    public PropertyController(PropertyRepository propertyRepository, AgentRepository agentRepository) {
        this.propertyRepository = propertyRepository;
        this.agentRepository = agentRepository;
    }

    // Add a property
    @PostMapping("/add")
    public Property addProperty(@RequestBody PropertyRequest request) {
        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        Property property = new Property();
        property.setTitle(request.getTitle());
        property.setLocation(request.getLocation());
        property.setPrice(request.getPrice());
        property.setType(request.getType());
        property.setImageUrls(request.getImageUrls());
        property.setAgent(agent);

        return propertyRepository.save(property);
    }

    // Get all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Search properties by location
    @GetMapping("/search")
    public List<Property> searchProperties(@RequestParam String location) {
        return propertyRepository.findByLocationContainingIgnoreCase(location);
    }
}
