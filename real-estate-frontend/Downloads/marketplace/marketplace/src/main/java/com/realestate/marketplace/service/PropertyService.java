package com.realestate.marketplace.service;

import com.realestate.marketplace.dto.PropertyRequest;
import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.Property;
import com.realestate.marketplace.repository.AgentRepository;
import com.realestate.marketplace.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;

    public PropertyService(PropertyRepository propertyRepository, AgentRepository agentRepository) {
        this.propertyRepository = propertyRepository;
        this.agentRepository = agentRepository;
    }

    public Property addProperty(PropertyRequest request) {
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


    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> searchByLocation(String location) {
        return propertyRepository.findByLocation(location);
    }
}
