package com.realestate.marketplace.service;

import com.realestate.marketplace.dto.PropertyRequest;
import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.Property;
import com.realestate.marketplace.repository.AgentRepository;
import com.realestate.marketplace.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;

    public PropertyService(PropertyRepository propertyRepository,
                           AgentRepository agentRepository) {
        this.propertyRepository = propertyRepository;
        this.agentRepository = agentRepository;
    }

    public Property addProperty(PropertyRequest request) {
        // ✅ FIXED: Only look for agent if an ID is provided
        Agent agent = null;
        if (request.getAgentId() != null) {
            agent = agentRepository.findById(request.getAgentId())
                    .orElse(null); // If ID is wrong, we just leave agent as null
        }

        MultipartFile image = request.getImage();
        String fileName = null;

        // Only process image if it exists
        if (image != null && !image.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            try {
                image.transferTo(new File(uploadDir + fileName));
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        Property property = new Property();
        property.setTitle(request.getTitle());
        property.setLocation(request.getLocation());
        property.setPrice(request.getPrice());
        property.setType(request.getType());
        property.setDescription(request.getDescription());

        if (fileName != null) {
            property.setImageUrls(fileName);
        }

        property.setAgent(agent); // Can be null now, which is safe

        return propertyRepository.save(property);
    }

    // ... (Keep the rest of your methods exactly the same) ...

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        existing.setTitle(updatedProperty.getTitle());
        existing.setLocation(updatedProperty.getLocation());
        existing.setPrice(updatedProperty.getPrice());
        existing.setType(updatedProperty.getType());
        existing.setDescription(updatedProperty.getDescription());

        return propertyRepository.save(existing);
    }

    public List<Property> searchByLocation(String location) {
        return propertyRepository.findByLocationContainingIgnoreCase(location);
    }

    public boolean deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) return false;
        propertyRepository.deleteById(id);
        return true;
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id " + id));
    }
}